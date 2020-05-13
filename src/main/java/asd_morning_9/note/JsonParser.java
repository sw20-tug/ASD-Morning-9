package asd_morning_9.note;

import elemental.json.Json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;

public class JsonParser
{
  private String conf_file;
  private ArrayList<Note> notes_;

  public ArrayList<Note> getNotesList()
  {
    ArrayList<Note> list = new ArrayList<>();
    for (Note item : notes_)
    {
      if (item.getPinned())
      {
        list.add(item);
      }
    }

    for (Note item : notes_)
    {
      if (!item.getPinned())
      {
        list.add(item);
      }
    }

    return list;
  }

  // Instantiate default
  public JsonParser()
  {
    notes_ = new ArrayList<>();
    this.conf_file = "config.json";
  }

  // Instantiate with specific file path
  public JsonParser(String conf_file)
  {
    notes_ = new ArrayList<>();
    this.conf_file = conf_file;
  }

  public void PinNote (int id)
  {
    try
    {
      for(Note item : notes_)
      {
        if (item.getId() == id)
        {
          if (item.getPinned())
          {
            item.setPinned(false);
          }
          else
          {
            item.setPinned(true);
          }
          return;
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("[ERROR IN PINNING NOTE] " + e.getMessage());
    }
  }

  public void AddNote(Note note)
  {
    note.setId(getNewId());
    notes_.add(note);
  }

  public void EditNote (int id, String title, Note note)
  {
    boolean check;
    for (Note item : notes_)
    {
      if (item.getTitle().equals(note.getTitle()))
      {
        //item.setContent(content);
        //item.setCompleted(completed);
        //item.setTags(tags);
        int x;
        x = item.getId();
        //notes_.set(x, note);
        item.setContent(note.getContent());
        break;
      }
    }
    //SaveNotes();
  }

  public void EditNote(Note oldNote, Note newNote)
  {
    for(Note item : notes_)
    {
      if (item.getId() == oldNote.getId())
      {
        item.setTitle(newNote.getTitle());
        item.setContent(newNote.getContent());
        item.setTags(newNote.getTags());
        item.setCompleted(newNote.getCompleted());
        break;
      }
    }
  }

  public void SaveNotes()
  {
    JSONObject obj = new JSONObject();

    JSONArray list = new JSONArray();

    for (Note item : notes_)
    {
      JSONObject item_obj = new JSONObject();
      item_obj.put("id", item.getId());
      item_obj.put("title", item.getTitle());
      item_obj.put("content", item.getContent());
      item_obj.put("tags", item.getTags());
      item_obj.put("completed", item.isCompleted());
      item_obj.put("pinned", item.getPinned());
      list.add(item_obj);
    }

    obj.put("Notes", list);

    try
    {
      FileWriter file = new FileWriter(conf_file);
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

  public void ReadNotes()
  {
    try
    {
      // parsing file "JSONExample.json"
      Object obj = new JSONParser().parse(new FileReader(conf_file));

      // typecasting obj to JSONObject
      JSONObject jo = (JSONObject) obj;

      // getting notes
      JSONArray ja = (JSONArray) jo.get("Notes");

      if (notes_ != null)
        notes_.clear();

      Iterator itr = ja.iterator();
      while (itr.hasNext())
      {
        JSONObject item = (JSONObject) itr.next();

        String id_string = JSONValue.toJSONString(item.get("id"));
        int id = Integer.parseInt(id_string);

        String title = item.get("title").toString();
        String content = item.get("content").toString();
        String tags = item.get("tags").toString();
        Boolean pinned = Boolean.parseBoolean(item.get("pinned").toString());
        boolean completed = Boolean.parseBoolean(item.get("completed").toString());

        notes_.add(new Note(id, title, content, tags, completed, pinned));
      }
    }
    catch (Exception e)
    {
      System.out.println("[ERROR IN READ NOTES] " + e.getMessage());
    }
  }

  // Read from specific file
  public boolean ReadNotes(String file)
  {
    try
    {
      File data = new File(file);
      boolean exists = data.exists();
      if (!exists)
      {
        if (!data.createNewFile())
        {
          System.out.println("File already exists.");
          return false;
        }
      }

      this.conf_file = file;
      ReadNotes();
      return true;
    }
    catch (Exception e)
    {
      System.out.println("[ERROR IN READ NOTES] " + e.getMessage());
      return false;
    }
  }

  // Delete note with specific id
  public void DeleteNote(int id)
  {
    try
    {
      int it;
      for(it = 0; it < notes_.size(); it++)
      {
        Note item = notes_.get(it);

        if (item.getId() == id)
        {
          notes_.remove(it);
          return;
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("[ERROR IN DELETE NOTE] " + e.getMessage());
    }
  }
  
  //Sort notes by Titel
   public static Comparator<Note> NoteTitelSort = new Comparator<Note>() {

    public int compare(Note n1, Note n2) {

      String note1 = n1.getTitle();
      String note2 = n2.getTitle();

      /*For ascending order*/
      return note1.compareTo(note2);

    }
  };

  public  void SortNoteByTitel()
  {
    Collections.sort(notes_, NoteTitelSort);
  }

  public Note getNote(int id)
  {
    for(Note item : notes_)
    {
      if (item.getId() == id)
      {
        return item;
      }
    }

    return null;
  }

  //Delete given note
  public void DeleteNote(Note note)
  {
    try
    {
      // parsing file "JSONExample.json"
      Object obj = new JSONParser().parse(new FileReader(conf_file));

      // typecasting obj to JSONObject
      JSONObject jo = (JSONObject) obj;

      // getting notes
      JSONArray ja = (JSONArray) jo.get("Notes");

      notes_.clear();

      int id = note.getId();
      String title = note.getTitle();
      String content = note.getContent();

      for (Object o : ja)
      {
        JSONObject item = (JSONObject) o;
        String id_string = JSONValue.toJSONString(item.get("id"));
        int id_ = Integer.parseInt(id_string);
        String title_ = item.get("title").toString();
        String content_ = item.get("content").toString();

        if ((id == id_) && title_.equals(title) && content_.equals(content))
        {

          /// TODO: DO DELETION

        }
      }
    }
    catch (Exception e)
    {
      System.out.println("[ERROR IN DELETE NOTE] " + e.getMessage());
    }
  }
  
  //Filter notes
  public void FilterNotesByTag(String tag)
  {
    try
    {
      notes_.removeIf(item -> !item.getTags().equals(tag));
    }
    catch (Exception e)
    {
      System.out.println("[ERROR IN FILTER NOTES] " + e.getMessage());
    }
  }
    
  private int getNewId()
  {
    if (notes_.size() > 0)
    {
      return notes_.get(notes_.size() - 1).getId() + 1;
    }

    return 0;
  }
}
