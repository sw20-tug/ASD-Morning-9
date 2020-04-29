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

  @After
  public void tearDown()
  {

  }
}
