package asd_morning_9.note;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Note
{
  private int id;
  private String title;
  private String content;
  private String tags;
  private boolean completed;
  private boolean pinned;
  private LocalDate date_when_created;
  private LocalDate date_when_completed;


  public Note(int id, String title, String content, String tags)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.date_when_created = LocalDate.now();
    this.completed = false;
  }

  public Note(int id, String title, String content)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = "";
    this.date_when_created = LocalDate.now();
    this.completed = false;
  }

  public Note(int id, String title, String content, Boolean completed)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = "";
    this.date_when_created = LocalDate.now();
    this.completed = completed;
    if (completed) this.date_when_completed = LocalDate.now();
  }

  public Note(int id, String title, String content, String tags, Boolean completed)
{
  this.id = id;
  this.title = title;
  this.content = content;
  this.tags = tags;
  this.date_when_created = LocalDate.now();
  this.completed = completed;
  if (completed) this.date_when_completed = LocalDate.now();
}


  public Note(int id, String title, String content, String tags, Boolean completed, Boolean pinned)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.completed = completed;
    this.date_when_created = LocalDate.now();
    if (completed) this.date_when_completed = LocalDate.now();
    this.pinned = pinned;
  }

  public Note(int id, String title, String content, String tags, Boolean completed, Boolean pinned, LocalDate date_when_created)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.completed = completed;
    this.date_when_created = date_when_created;
    if (completed) this.date_when_completed = LocalDate.now();
    this.pinned = pinned;
  }

  public Note(int id, String title, String content, String tags, Boolean completed, Boolean pinned, LocalDate date_when_created, LocalDate date_when_completed)
  {
    this.id = id;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.completed = completed;
    this.date_when_created = date_when_created;
    if (completed) this.date_when_completed = date_when_completed;
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

  public LocalDate getDate_when_created() {return date_when_created;}

  public String getDate_when_created_str()  {
      return date_when_created.toString();
  }

  public void setDate_when_created(LocalDate date_when_created) {this.date_when_created = date_when_created; }

  public LocalDate getDate_when_completed() {
    return date_when_completed;
  }

  public String getDate_when_completed_str()  {
  return date_when_completed.toString();
  }

  public void setDate_when_completed(LocalDate date_when_completed)
  {
    this.date_when_completed = date_when_completed;
  }
}
