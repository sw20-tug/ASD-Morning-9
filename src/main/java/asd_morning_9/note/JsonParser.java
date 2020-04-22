package asd_morning_9.note;

import elemental.json.Json;

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

  private ArrayList<Note> notes_;

  public ArrayList<Note> getNotesList()
  {
    return notes_;
  }

  public JsonParser()
  {
    notes_ = new ArrayList<>();
  }

  public void AddNote(Note note)
  {
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
        notes_.set(x, note);
        break;
      }
    }
    //SaveNotes();
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
      FileWriter file = new FileWriter("config.json");
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
      Object obj = new JSONParser().parse(new FileReader("config.json"));

      // typecasting obj to JSONObject
      JSONObject jo = (JSONObject) obj;

      // getting notes
      JSONArray ja = (JSONArray) jo.get("Notes");

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
}
