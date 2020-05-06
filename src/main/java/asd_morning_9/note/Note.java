package asd_morning_9.note;

public class Note
{
  private int id;
  private String title;
  private String content;
  private String tags;
  private boolean completed;

  public Note(int id, String title, String content, String tags)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.completed = false;
  }

  public Note(int id, String title, String content)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = "";
    this.completed = false;
  }

  public Note(int id, String title, String content, Boolean completed)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = "";
    this.completed = completed;
  }

  public Note(int id, String title, String content, String tags, Boolean completed)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.completed = completed;
  }

  /*public Note(String value, String value1) { //hab die erste Instanz entfernt
  }*/

  public int getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }

  public void setId(int id) { this.id = id; }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getContent()
  {
    return content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public String getTags()
  {
    return tags;
  }

  public void setTags(String tags)
  {
    this.tags = tags;
  }

  public boolean isCompleted()
  {
    return completed;
  }

  public void setCompleted(boolean completed)
  {
    this.completed = completed;
  }
}
