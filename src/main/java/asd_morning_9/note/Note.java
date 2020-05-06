package asd_morning_9.note;

public class Note
{
  private int id;
  private String title;
  private String content;
  private String tags;
  private String tags2;
  private String tags3;
  private String tags4;
  private String tags5;
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

  public Note(int id, String title, String content, String tags, String tags2, String tags3, String tags4, String tags5, Boolean completed)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.tags2 = tags2;
    this.tags3 = tags3;
    this.tags4 = tags4;
    this.tags5 = tags5;
    this.completed = completed;
  }

  public Note(int id, String title, String content, String tags, String tags2, String tags3, String tags4, String tags5)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.tags2 = tags2;
    this.tags3 = tags3;
    this.tags4 = tags4;
    this.tags5 = tags5;
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

  public String getTags2()
  {
    return tags2;
  }

  public String getTags3()
  {
    return tags3;
  }

  public String getTags4()
  {
    return tags4;
  }

  public String getTags5()
  {
    return tags5;
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
