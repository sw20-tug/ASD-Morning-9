package asd_morning_9.note;

import elemental.json.Json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    return notes_;
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

  public void AddNote(Note note)
  {
    notes_.add(note);
  }

  public void EditNote (int id, String content, String title)
  {
    for (Note item : notes_)
    {
      if (item.getId() == id)
      {
        item.setContent(content);
        //item.setCompleted(completed);
        //item.setTags(tags);
        item.setTitle(title);
        break;
      }
    }
    SaveNotes();
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
        boolean completed = Boolean.parseBoolean(item.get("completed").toString());

        notes_.add(new Note(id, title, content, completed));
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
}
