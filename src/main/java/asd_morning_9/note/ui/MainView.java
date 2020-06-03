package asd_morning_9.note.ui;
import asd_morning_9.note.JsonParser;
import asd_morning_9.note.Note;

import asd_morning_9.note.Application;
import asd_morning_9.note.JsonParser;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Homepage")
@CssImport("./styles/MainView.css")
public class MainView extends VerticalLayout
{
    private JsonParser parser;
    public int language = 0; //0 = Englisch, 1 = Deutsch, 2 = Französisch
    public MainView() {
        if(language == 1)
        {
            parser = new JsonParser();
            parser.ReadNotes();

            H1 header = new H1("Das ist die Hauptseite.");
            add(header);

            Div new_note_cont = new Div();

            TextField title = new TextField();
            title.setLabel("Titel");
            title.setClassName("newNoteTitle");


            TextField tags = new TextField();
            tags.setLabel("Tags");

            tags.setClassName("newNoteTags");

            TextArea content = new TextArea("Text");
            content.getStyle().set("height", "150px");
            content.setPlaceholder("Hier schreiben...");
            content.setClassName("newNoteContent");

            new_note_cont.add(title);
            new_note_cont.add(tags);
            new_note_cont.add(content);

            add(new_note_cont);

            add(new Button("Speichern", event -> {

                parser.AddNote(new Note(5, title.getValue(), content.getValue(), tags.getValue()));
                parser.SaveNotes();
                Notification notification = new Notification(
                        "Gespeichert!", 2000,
                        Notification.Position.MIDDLE);
                notification.open();
            }));
        }
        else if(language == 2)
        {
            parser = new JsonParser();
            parser.ReadNotes();

            H1 header = new H1("Page d'accueil.");
            add(header);

            Div new_note_cont = new Div();

            TextField title = new TextField();
            title.setLabel("Titre");
            title.setClassName("newNoteTitle");

            TextField tags = new TextField();
            tags.setLabel("Tags");

            tags.setClassName("newNoteTags");

            TextArea content = new TextArea("Texte");
            content.getStyle().set("height", "150px");
            content.setPlaceholder("Ecrire ici ...");
            content.setClassName("newNoteContent");

            new_note_cont.add(title);
            new_note_cont.add(tags);
            new_note_cont.add(content);

            add(new_note_cont);

            add(new Button("Sauver", event -> {

                parser.AddNote(new Note(5, title.getValue(), content.getValue(), tags.getValue()));
                parser.SaveNotes();
                Notification notification = new Notification(
                        "Enregistré!", 2000,
                        Notification.Position.MIDDLE);
                notification.open();
            }));
        }
        else
        {
            parser = new JsonParser();
            parser.ReadNotes();

            H1 header = new H1("This is the MainView of our Application.");
            add(header);

            Div new_note_cont = new Div();

            TextField title = new TextField();
            title.setLabel("Title");
            title.setClassName("newNoteTitle");

            TextField tags = new TextField();
            tags.setLabel("Tags");

            tags.setClassName("newNoteTags");

            TextArea content = new TextArea("Content");
            content.getStyle().set("height", "150px");
            content.setPlaceholder("Write here ...");
            content.setClassName("newNoteContent");

            new_note_cont.add(title);
            new_note_cont.add(tags);
            new_note_cont.add(content);

            add(new_note_cont);

            add(new Button("Save Note", event -> {

                parser.AddNote(new Note(5, title.getValue(), content.getValue(), tags.getValue()));
                parser.SaveNotes();
                Notification notification = new Notification(
                "Note was saved successfully!", 2000,
                Notification.Position.MIDDLE);
                notification.open();
            }));
        }
    }
}
