package asd_morning_9.note;

import org.junit.Test;

import static org.junit.Assert.*;

public class NoteTest
{

  private int id = 0;
  private String title = "title";
  private String content = "content";
  private String tags = "this,are,some,tags";
  private boolean completed = false;

  @Test
  public void NoteTest_1()
  {
    Note note = new Note(id, title, content, tags);
    assertEquals(id, note.getId());
    assertEquals(title, note.getTitle());
    assertEquals(content, note.getContent());
    assertEquals(tags, note.getTags());
  }

  @Test
  public void NoteTest_2()
  {
    Note note = new Note(id, title, content);
    assertEquals(id, note.getId());
    assertEquals(title, note.getTitle());
    assertEquals(content, note.getContent());
  }

  @Test
  public void NoteTest_3()
  {
    Note note = new Note(id, title, content, completed);
    assertEquals(id, note.getId());
    assertEquals(title, note.getTitle());
    assertEquals(content, note.getContent());
  }

  @Test
  public void NoteTest_4()
  {
    Note note = new Note(id, title, content, tags, completed);
    assertEquals(id, note.getId());
    assertEquals(title, note.getTitle());
    assertEquals(content, note.getContent());
  }
}