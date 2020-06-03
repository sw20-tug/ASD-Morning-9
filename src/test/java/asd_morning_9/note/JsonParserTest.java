package asd_morning_9.note;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class JsonParserTest
{
    private int expected_arr_size = 1;
    private String test_file = "src/test/java/asd_morning_9/note/config_test.json";

    @Before
    public void setUp()
    {

    }

    private boolean fileExists(String file)
    {
        File f = new File(file);
        return f.exists();
    }

    private void createTestFile()
    {
        if (fileExists(test_file))
        {
            deleteTestFile();
        }

        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();

        Note item = new Note(0, "This is a test title.", "This content is a very short test.", "few,tags,should,be,here", false, false);

        JSONObject item_obj = new JSONObject();
        item_obj.put("id", item.getId());
        item_obj.put("title", item.getTitle());
        item_obj.put("content", item.getContent());
        item_obj.put("tags", item.getTags());
        item_obj.put("completed", item.isCompleted());
        item_obj.put("date_when_created", item.getDate_when_created_str());
        if (item.getDate_when_completed() != null) {item_obj.put("date_when_completed", item.getDate_when_completed_str());}
        item_obj.put("pinned", item.getPinned());
        list.add(item_obj);

        obj.put("Notes", list);

        try
        {
            FileWriter file = new FileWriter(test_file);
            String string = obj.toJSONString();
            file.write(string);
            file.flush();
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void deleteTestFile()
    {
        if (!fileExists(test_file))
            return;

        File f= new File(test_file);
        f.delete();
    }

    @Test
    public void readNotes()
    {
        createTestFile();

        JsonParser parser;
        ArrayList<Note> notes_;
        parser = new JsonParser(test_file);
        boolean result = parser.ReadNotes(test_file);
        notes_ = parser.getNotesList();
        assertTrue(result);
        assertEquals(expected_arr_size, notes_.size());

        deleteTestFile();
    }

    @Test
    public void addNewNoteTest()
    {
        createTestFile();

        JsonParser parser;
        ArrayList<Note> notes_;
        parser = new JsonParser(test_file);
        parser.ReadNotes(test_file);

        parser.AddNote(new Note(1, "TitleTEST1", "content", "this,are,some,tags"));
        parser.AddNote(new Note(2, "TitleTEST2", "content", "this,are,some,tags"));

        parser.SaveNotes();

        notes_ = parser.getNotesList();

        assertEquals(expected_arr_size, notes_.size());

        System.out.print(notes_.get(0).getTitle());
        System.out.print(notes_.get(0).getDate_when_created());

        deleteTestFile();
    }


    @Test
    public void EditNoteTest()
    {
        createTestFile();

        JsonParser parser;
        ArrayList<Note> notes_;
        parser = new JsonParser(test_file);
        parser.ReadNotes(test_file);

        Note oldNote = parser.getNote(0);
        Note newNote = new Note(1, "new title", "new content", "new tags", true);

        parser.EditNote(oldNote, newNote);

        // Get the old one which should be changed to the new one
        oldNote = parser.getNote(0);

        assertNotEquals(oldNote.getId(), newNote.getId());
        assertEquals(oldNote.getTitle(), newNote.getTitle());
        assertEquals(oldNote.getContent(), newNote.getContent());
        assertEquals(oldNote.getTags(), newNote.getTags());
        assertEquals(oldNote.getCompleted(), newNote.getCompleted());

        deleteTestFile();
    }


    @Test
    public void DeleteNoteTest()
    {
        createTestFile();

        JsonParser parser;
        ArrayList<Note> notes_;
        parser = new JsonParser(test_file);
        parser.ReadNotes(test_file);
        parser.DeleteNote(0);
        notes_ = parser.getNotesList();
        assertEquals(expected_arr_size - 1, notes_.size());

        deleteTestFile();
    }

    @Test
    public void SaveNotesTest()
    {
        createTestFile();

        JsonParser parser;
        ArrayList<Note> notes_;
        parser = new JsonParser(test_file);
        parser.ReadNotes(test_file);
        parser.AddNote(new Note(0, "Title", "content", "this,are,some,tags"));
        parser.SaveNotes();
        parser.ReadNotes(test_file);
        notes_ = parser.getNotesList();
        assertEquals(expected_arr_size + 1, notes_.size());

        parser.DeleteNote(0);
        parser.SaveNotes();

        deleteTestFile();
    }

    @Test
    public void GetNewIdTest()
    {
        createTestFile();

        JsonParser parser;
        ArrayList<Note> notes_;
        parser = new JsonParser(test_file);
        parser.ReadNotes(test_file);
        notes_ = parser.getNotesList();

        int last_id = notes_.get(notes_.size() - 1).getId();
        parser.AddNote(new Note(0, "Title", "content", "this,are,some,tags"));
        notes_ = parser.getNotesList();
        int new_id = notes_.get(notes_.size() - 1).getId();
        assertEquals(last_id + 1, new_id);

        deleteTestFile();
    }

    @Test
    public void FilterNotesByTagTest()
    {
        createTestFile();

        JsonParser parser;
        ArrayList<Note> notes_;
        parser = new JsonParser(test_file);
        //parser.ReadNotes(test_file);

        parser.AddNote(new Note(1, "Title1", "content1", "none"));
        parser.AddNote(new Note(2, "Title2", "content2", "important"));
        parser.AddNote(new Note(3, "Title3", "content3", "none"));
        parser.AddNote(new Note(4, "Title4", "content4", "important"));
        parser.AddNote(new Note(5, "Title5", "content5", "important"));

        parser.FilterNotesByTag("important");
        notes_ = parser.getNotesList();
        assertEquals(expected_arr_size+2, notes_.size());
        assertEquals("important", notes_.get(0).getTags());

        deleteTestFile();
    }

    @Test
    public void SortNoteByTitel()
    {
        createTestFile();

        JsonParser parser = new JsonParser(test_file);
        ArrayList<Note> notes_;
        parser.ReadNotes(test_file);
        parser.DeleteNote(0);

        parser.AddNote(new Note(0, "ATitle", "content", "this,are,some,tags"));
        parser.AddNote(new Note(0, "GTitle", "content", "this,are,some,tags"));
        parser.AddNote(new Note(0, "ITitle", "content", "this,are,some,tags"));
        parser.AddNote(new Note(0, "LTitle", "content", "this,are,some,tags"));
        parser.AddNote(new Note(0, "ETitle", "content", "this,are,some,tags"));

        parser.SortNoteByTitel();
        notes_ = parser.getNotesList();

        assertEquals("ATitle",notes_.get(0).getTitle());
        assertEquals("ETitle",notes_.get(1).getTitle());
        assertEquals("GTitle",notes_.get(2).getTitle());
        assertEquals("ITitle",notes_.get(3).getTitle());
        assertEquals("LTitle",notes_.get(4).getTitle());
        deleteTestFile();
    }
    
    @Test
    public void ShareNotesTest()
    {
        createTestFile();

        JsonParser parser;
        ArrayList<Note> notes_;
        parser = new JsonParser(test_file);
        parser.ReadNotes(test_file);
        parser.AddNote(new Note(0, "Title", "content", "this,are,some,tags"));
        parser.AddNote(new Note(1, "Title1", "content1", "this,are,some,tags1"));

        parser.ShareNote("test.usertug12@gmail.com", "test.usertug1234@gmail.com", "test.usertug12", "test.user1234");
        notes_ = parser.getNotesList();
        assertEquals(expected_arr_size + 2, notes_.size());

        deleteTestFile();
    }

  
    @Test
    public void TagsTest()
    {
      createTestFile();

      JsonParser parser;
      ArrayList<Note> notes_;
      parser = new JsonParser(test_file);
      parser.ReadNotes(test_file);
      parser.AddNote(new Note(0, "Title", "content", "this,are,some,tags"));
      parser.SaveNotes();
      parser.ReadNotes(test_file);
      notes_ = parser.getNotesList();
      String check = "";
      String[] bufferTagArray = notes_.get(1).getTags().split(",");
      for(int i=0; i < bufferTagArray.length; i++)
      {
        check = check.concat(bufferTagArray[i]);
      }
      assertEquals("thisaresometags", check);

      parser.DeleteNote(0);
      parser.SaveNotes();

      deleteTestFile();
    }

  
  @Test
  public void MarkAsCompletedTest()
  {
    createTestFile();

    JsonParser parser;
    ArrayList<Note> notes_;
    parser = new JsonParser(test_file);
    parser.ReadNotes(test_file);

    parser.AddNote(new Note(1, "Title1", "content1", "none"));
    parser.AddNote(new Note(2, "Title2", "content2", "important", true));
    parser.AddNote(new Note(3, "Title3", "content3", "none", false));

    parser.markAsCompleted(1);
    parser.markAsCompleted(2);
    parser.markAsCompleted(3);

    notes_ = parser.getNotesList();
    assertEquals(expected_arr_size+3, notes_.size());
    assertEquals(true, notes_.get(1).getCompleted());
    assertEquals(true, notes_.get(2).getCompleted());
    assertEquals(true, notes_.get(3).getCompleted());

    String date_when_completed = notes_.get(1).getDate_when_completed().toString();
    String str_today_day = LocalDate.now().toString();

    assertEquals(str_today_day, date_when_completed);
    deleteTestFile();
  }
    
    @After
    public void tearDown()
    {

    }
}
