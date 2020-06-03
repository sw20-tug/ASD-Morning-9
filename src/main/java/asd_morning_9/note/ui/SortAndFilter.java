package asd_morning_9.note.ui;
import asd_morning_9.note.JsonParser;
import asd_morning_9.note.Note;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "SortAndFilter", layout = MainLayout.class)
@CssImport("./styles/SortAndFilter.css")
public class SortAndFilter extends VerticalLayout {
  private JsonParser parser;
  public int language = 0; //0 = Englisch, 1 = Deutsch, 2 = Französisch
  public SortAndFilter() {

    parser = new JsonParser();
    parser.ReadNotes();
    parser.SaveNotes();
    if(language == 1)
    {
      H1 header = new H1("Sortieren und Filter Menü");
      add(header);

      HtmlComponent br = new HtmlComponent("br");

      UnorderedList ui = new UnorderedList();
      ui.setId("notesUI");
      for (Note item : parser.getNotesList())
      {
        Div cont = new Div();

        Header head = new Header();
        head.add(item.getTitle());
        cont.add(head);
        cont.add("Text: " + item.getContent());
        cont.add(br);
        cont.add("Tag: " + item.getTags());

        ListItem li = new ListItem(cont);
        ui.add(li);
      }

      //Sort by title
      add(new Button("Sortieren mit Titel", event -> {
        parser.SortNoteByTitel();
        remove(ui);

        UnorderedList ui_ = new UnorderedList();
        ui_.setId("notesUI");

        for (Note item : parser.getNotesList())
        {
          Div cont = new Div();
          Div footer = new Div();

          Header head = new Header();
          head.add(item.getTitle());
          cont.add(head);
          cont.add("Text: " + item.getContent());
          cont.add(br);
          cont.add("Tag: " + item.getTags());
          cont.add(footer);
          ListItem li = new ListItem(cont);
          ui_.add(li);
        }
        add(ui_);
        Notification notification = new Notification(
        "Sortiert!", 2000,
        Notification.Position.MIDDLE);
        notification.open();
      }));

      //Filter by tag
      TextField tag = new TextField();
      tag.setLabel("Tags");
      tag.setPlaceholder("Filtern mit tag ...");
      tag.setClassName("filterNoteByTag");

      Div filter_note_cont = new Div();

      filter_note_cont.add(tag);

      add(filter_note_cont);

      add(new Button("Filtern", event -> {

        parser.FilterNotesByTag(tag.getValue());
        remove(ui);

        UnorderedList ui_ = new UnorderedList();
        ui_.setId("notesUI");

        for (Note item : parser.getNotesList())
        {
          Div cont = new Div();
          Div footer = new Div();

          Header head = new Header();
          head.add(item.getTitle());
          cont.add(head);
          cont.add("Text: " + item.getContent());
          cont.add(br);
          cont.add("Tag: " + item.getTags());
          cont.add(footer);
          ListItem li = new ListItem(cont);
          ui_.add(li);
        }
        add(ui_);

        Notification notification = new Notification(
        "Gefiltert!", 2000,
        Notification.Position.MIDDLE);
        notification.open();
      }));
      add(ui);
    }
    else if(language == 2)
    {
      H1 header = new H1("Ceci est le menu de tri et de filtrage");
      add(header);

      HtmlComponent br = new HtmlComponent("br");

      UnorderedList ui = new UnorderedList();
      ui.setId("notesUI");
      for (Note item : parser.getNotesList())
      {
        Div cont = new Div();
        Header head = new Header();
        head.add(item.getTitle());
        cont.add(head);
        cont.add("contenu: " + item.getContent());
        cont.add(br);
        cont.add("Tag: " + item.getTags());

        ListItem li = new ListItem(cont);
        ui.add(li);
      }

      //Sort by title
      add(new Button("Trier les notes par titre", event -> {
        parser.SortNoteByTitel();
        remove(ui);

        UnorderedList ui_ = new UnorderedList();
        ui_.setId("notesUI");

        for (Note item : parser.getNotesList())
        {
          Div cont = new Div();
          Div footer = new Div();
          Header head = new Header();
          head.add(item.getTitle());
          cont.add(head);
          cont.add("contenu: " + item.getContent());
          cont.add(br);
          cont.add("Tag: " + item.getTags());
          cont.add(footer);
          ListItem li = new ListItem(cont);
          ui_.add(li);
        }
        add(ui_);
        Notification notification = new Notification(
        "Les notes ont été triées avec succès!", 2000,
        Notification.Position.MIDDLE);
        notification.open();
      }));

      //Filter by tag
      TextField tag = new TextField();
      tag.setLabel("Tags");
      tag.setPlaceholder("Filtrer les notes par tag ...");
      tag.setClassName("filterNoteByTag");

      Div filter_note_cont = new Div();

      filter_note_cont.add(tag);

      add(filter_note_cont);

      add(new Button("filtre", event -> {

        parser.FilterNotesByTag(tag.getValue());
        remove(ui);

        UnorderedList ui_ = new UnorderedList();
        ui_.setId("notesUI");

        for (Note item : parser.getNotesList())
        {
          Div cont = new Div();
          Div footer = new Div();

          Header head = new Header();
          head.add(item.getTitle());
          cont.add(head);
          cont.add("contenu: " + item.getContent());
          cont.add(br);
          cont.add("Tag: " + item.getTags());
          cont.add(footer);
          ListItem li = new ListItem(cont);
          ui_.add(li);
        }
        add(ui_);

        Notification notification = new Notification(
        "Les notes ont été filtrées avec succès!", 2000,
        Notification.Position.MIDDLE);
        notification.open();
      }));
      add(ui);
    }
    else
    {
      H1 header = new H1("This is the sort and filter menu");
      add(header);

      HtmlComponent br = new HtmlComponent("br");

      UnorderedList ui = new UnorderedList();
      ui.setId("notesUI");
      for (Note item : parser.getNotesList())
      {
        Div cont = new Div();

        Header head = new Header();
        head.add(item.getTitle());
        cont.add(head);
        cont.add("Content: " + item.getContent());
        cont.add(br);
        cont.add("Tag: " + item.getTags());

        ListItem li = new ListItem(cont);
        ui.add(li);
      }

      //Sort by title
      add(new Button("Sort notes by title", event -> {
        parser.SortNoteByTitel();
        remove(ui);

        UnorderedList ui_ = new UnorderedList();
        ui_.setId("notesUI");

        for (Note item : parser.getNotesList())
        {
          Div cont = new Div();
          Div footer = new Div();

          Header head = new Header();
          head.add(item.getTitle());
          cont.add(head);
          cont.add("Content: " + item.getContent());
          cont.add(br);
          cont.add("Tag: " + item.getTags());
          cont.add(footer);
          ListItem li = new ListItem(cont);
          ui_.add(li);
        }
        add(ui_);
        Notification notification = new Notification(
        "Notes were sorted successfully!", 2000,
        Notification.Position.MIDDLE);
        notification.open();
      }));

      //Filter by tag
      TextField tag = new TextField();
      tag.setLabel("Tags");
      tag.setPlaceholder("Filter notes by tag ...");
      tag.setClassName("filterNoteByTag");

      Div filter_note_cont = new Div();

      filter_note_cont.add(tag);

      add(filter_note_cont);

      add(new Button("Filter", event -> {

        parser.FilterNotesByTag(tag.getValue());
        remove(ui);

        UnorderedList ui_ = new UnorderedList();
        ui_.setId("notesUI");

        for (Note item : parser.getNotesList())
        {
          Div cont = new Div();
          Div footer = new Div();

          Header head = new Header();
          head.add(item.getTitle());
          cont.add(head);
          cont.add("Content: " + item.getContent());
          cont.add(br);
          cont.add("Tag: " + item.getTags());
          cont.add(footer);
          ListItem li = new ListItem(cont);
          ui_.add(li);
        }
        add(ui_);

        Notification notification = new Notification(
        "Notes were filtered successfully!", 2000,
        Notification.Position.MIDDLE);
        notification.open();
      }));
      add(ui);
    }
  }
}
