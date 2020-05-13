package asd_morning_9.note;

public class Note
{
  private int id;
  private String title;
  private String content;
  private String tags;
  private boolean completed;
  private boolean pinned;

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

  public Note(int id, String title, String content, String tags, Boolean completed, Boolean pinned)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.completed = completed;
    this.pinned = pinned;
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

  public String getTags()
  {
    return tags;
  }

  public boolean getCompleted() { return completed; }

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

  public void setPinned(boolean pinned){this.pinned = pinned;}

  public boolean getPinned(){return this.pinned;}


}
