package asd_morning_9.note;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonParserTest
{
  private int expected_arr_size = 1;
  private String test_file = ".\\src\\test\\java\\asd_morning_9\\note\\config_test.json";

  @Before
  public void setUp()
  {

  }

  @Test
  public void readNotes()
  {
    JsonParser parser;
    ArrayList<Note> notes_;
    parser = new JsonParser(test_file);
    boolean result = parser.ReadNotes(test_file);
    notes_ = parser.getNotesList();
    assertTrue(result);
    assertEquals(expected_arr_size, notes_.size());
  }

  @Test
  public void addNewNoteTest()
  {
    JsonParser parser;
    ArrayList<Note> notes_;
    parser = new JsonParser(test_file);
    parser.ReadNotes(test_file);
    parser.AddNote(new Note(0, "Title", "content", "this,are,some,tags"));
    parser.AddNote(new Note(0, "Title", "content", "this,are,some,tags"));
    notes_ = parser.getNotesList();
    assertEquals(expected_arr_size + 2, notes_.size());
  }


  @Test
  public void EditNoteTest()
  {
    JsonParser parser;
    ArrayList<Note> notes_;
    parser = new JsonParser(test_file);
    parser.ReadNotes(test_file);
    Note note = new Note(0, "Title", "content", "this is a tag");
    parser.EditNote(0, "Title", note);
    notes_ = parser.getNotesList();
    assertEquals(expected_arr_size , notes_.size());
    parser.DeleteNote(0);
    parser.SaveNotes();
  }


  @Test
  public void DeleteNoteTest()
  {
    JsonParser parser;
    ArrayList<Note> notes_;
    parser = new JsonParser(test_file);
    parser.ReadNotes(test_file);
    parser.DeleteNote(0);
    notes_ = parser.getNotesList();
    assertEquals(expected_arr_size - 1, notes_.size());
  }

  @Test
  public void SaveNotesTest()
  {
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
  }

  @Test
  public void GetNewIdTest()
  {
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
  }
  
 @Test
    public void FilterNotesByTagTest()
    {
      JsonParser parser;
      ArrayList<Note> notes_;
      parser = new JsonParser(test_file);
      parser.ReadNotes(test_file);

      parser.AddNote(new Note(1, "Title1", "content1", "none"));
      parser.AddNote(new Note(2, "Title2", "content2", "important"));
      parser.AddNote(new Note(3, "Title3", "content3", "none"));
      parser.AddNote(new Note(4, "Title4", "content4", "important"));
      parser.AddNote(new Note(5, "Title5", "content5", "important"));

      parser.FilterNotesByTag("important");
      notes_ = parser.getNotesList();
      assertEquals(expected_arr_size+2, notes_.size());
      assertEquals("important", notes_.get(0).getTags());
    }
  
  @Test
    public void SortNoteByTitel()
    {
      JsonParser parser;
      ArrayList<Note> notes_;
  
      parser = new JsonParser(test_file);
      parser.ReadNotes(test_file);

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
      assertEquals("Title",notes_.get(5).getTitle());
    }
  
  @After
  public void tearDown()
  {

  }
}
