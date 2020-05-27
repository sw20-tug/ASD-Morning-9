package asd_morning_9.note;

import elemental.json.Json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class JsonParser
{
  private String conf_file;
  private ArrayList<Note> notes_;
  private ArrayList<Integer> selected_notes = new ArrayList<>();

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
  /*public ArrayList<Note> getCheckedNotesList()
  {
    return selected_notes;
  }
  */

  public void addId (int id) {

    selected_notes.add(id);
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
      item_obj.put("date_when_created", item.getDate_when_created());
      if (item.getDate_when_completed() != null)  item_obj.put("date_when_completed", item.getDate_when_completed());

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

  // Save to a specific file
  public void SaveNotes(String path)
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
      item_obj.put("date_when_created", item.getDate_when_created_str());
      if (item.getDate_when_completed() != null)
        item_obj.put("date_when_completed", item.getDate_when_completed_str());

      list.add(item_obj);
    }

    obj.put("Notes", list);

    try
    {
      FileWriter file = new FileWriter(path);
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

        String date_when_created_str = (String)item.get("date_when_created");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-M-dd", Locale.ENGLISH);
        LocalDate date_when_created = LocalDate.parse(date_when_created_str, formatter);

       if (item.get("date_when_completed") != null) {
          String date_when_completed_str = (String)item.get("date_when_created");
          LocalDate date_when_completed = LocalDate.parse(date_when_completed_str, formatter);
          notes_.add(new Note(id, title, content, tags, completed, pinned, date_when_created, date_when_completed));
        }
        else
          notes_.add(new Note(id, title, content, tags, completed, pinned, date_when_created));



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
      notes_.removeIf(item -> !item.getTags().contains(tag));
      //notes_.removeIf(item -> !item.getTags().equals(tag));

    }
    catch (Exception e)
    {
      System.out.println("[ERROR IN FILTER NOTES] " + e.getMessage());
    }
  }


  public void ShareNote(String sender_email, String recipient_email,final String username,
          final String password)
  {
    ArrayList<Note> inverse = new ArrayList<>();
    String text_mes = new String();
  try {
    for (int i : selected_notes)
    {
      System.out.println(selected_notes.size());
      for(Note item : notes_)
      {
        //System.out.println(item.getId());
        System.out.println("notes_"+i);

        if (item.getId() == i)
        {
          text_mes = text_mes.concat(item.getTitle()).concat("\n\n").concat(item.getContent()).concat("\n\n").concat("-------------------").concat("\n");

        }
      }
    }
  }
    catch (Exception e)
    {
      System.out.println("[ERROR NO NOTE SELECTED TO SHARE] " + e.getMessage());
    }
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    // Get a Properties object
    Properties props = System.getProperties();
    props.setProperty("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.port", "465");
    props.setProperty("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.auth", "true");
    props.put("mail.debug", "true");
    props.put("mail.store.protocol", "pop3");
    props.put("mail.transport.protocol", "smtp");
    // creating session object to get properties

    try{
        Session session = Session.getDefaultInstance(props,
                new Authenticator(){
                  protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                  }});

        // -- Create a new message --
        Message msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(sender_email));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient_email,false));

        msg.setSubject("Shared Notes");
        msg.setText(text_mes);
        msg.setSentDate(new Date());
        Transport.send(msg);

        System.out.println("Message sent.");


      }catch (MessagingException e){ System.out.println("ERROR: " + e);}

  }
    
  private int getNewId()
  {
    if (notes_.size() > 0)
    {
      return notes_.get(notes_.size() - 1).getId() + 1;
    }

    return 0;
  }

   public void markAsCompleted(int id)
  {
    try
    {
      int it;
      for(it = 0; it < notes_.size(); it++)
      {
        Note item = notes_.get(it);

        if (item.getId() == id)
        {
          item.setCompleted(true);
          item.setDate_when_completed(LocalDate.now());
          return;
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("[ERROR IN MARK AS COMPLETED] " + e.getMessage());
    }
  }
  

}

