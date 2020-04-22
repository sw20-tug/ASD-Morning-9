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

  private JsonParser parser;
  private ArrayList<Note> notes_;

  @Before
  public void setUp()
  {

  }

  @Test
  public void readNotes()
  {
    parser = new JsonParser(test_file);
    boolean result = parser.ReadNotes(test_file);
    notes_ = parser.getNotesList();
    assertTrue(result);
    assertEquals(expected_arr_size, notes_.size());
  }

  @Test
  public void addNewNoteTest()
  {
    parser = new JsonParser(test_file);
    parser.ReadNotes(test_file);
    parser.AddNote(new Note(0, "Title", "content", "this,are,some,tags"));
    parser.AddNote(new Note(0, "Title", "content", "this,are,some,tags"));
    notes_ = parser.getNotesList();
    assertEquals(expected_arr_size + 2, notes_.size());
  }

  @Test
  public void DeleteNoteTest()
  {
    parser = new JsonParser(test_file);
    parser.ReadNotes(test_file);
    parser.DeleteNote(0);
    notes_ = parser.getNotesList();
    assertEquals(expected_arr_size - 1, notes_.size());
  }

  @Test
  public void SaveNotesTest()
  {
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
