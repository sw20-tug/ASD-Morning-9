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

    public MainView() {

        parser = new JsonParser();
        parser.ReadNotes();

        H1 header = new H1("This is the MainView of our Application.");
        add(header);

        Div new_note_cont = new Div();

        TextField title = new TextField();
        title.setLabel("Title");
        title.setClassName("newNoteTitle");

        TextArea tags = new TextArea("Tags");
        tags.getStyle().set("height", "150px");
        tags.setPlaceholder("");
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
/*            String[] bufferTagArray = tags.getValue().split(",");
            //Problem: array kann im nachhinein nicht verändert werden
            //Lösung: zweites Array mit fixer größe wo tagArray reingespeichert wird
            String[] tagArray = {" "," "," "," "," "};

            if(bufferTagArray.length == 0)
            {

                tagArray[0] = "";
                tagArray[1] = "";
                tagArray[2] = "";
                tagArray[3] = "";
                tagArray[4] = "";
            }
            System.out.println("Teset1");
            if(bufferTagArray.length == 1)
            {
                tagArray[0] = bufferTagArray[0];
                tagArray[1] = "";
                tagArray[2] = "";
                tagArray[3] = "";
                tagArray[4] = "";

            }
            System.out.println("Teset2");
            if(bufferTagArray.length == 2)
            {
                tagArray[0] = bufferTagArray[0];
                tagArray[1] = bufferTagArray[1];
                tagArray[2] = "";
                tagArray[3] = "";
                tagArray[4] = "";
            }
            if(bufferTagArray.length == 3)
            {
                tagArray[0] = bufferTagArray[0];
                tagArray[1] = bufferTagArray[1];
                tagArray[2] = bufferTagArray[2];
                tagArray[3] = "";
                tagArray[4] = "";
            }
            if(bufferTagArray.length == 4)
            {
                tagArray[0] = bufferTagArray[0];
                tagArray[1] = bufferTagArray[1];
                tagArray[2] = bufferTagArray[2];
                tagArray[3] = bufferTagArray[3];
                tagArray[4] = "";
            }
            if(bufferTagArray.length == 5)
            {
                tagArray[0] = bufferTagArray[0];
                tagArray[1] = bufferTagArray[1];
                tagArray[2] = bufferTagArray[2];
                tagArray[3] = bufferTagArray[3];
                tagArray[4] = bufferTagArray[4];
            }
            if(bufferTagArray.length > 5)
            {
                Notification notification = new Notification(
                        "Too many Tags!", 2000,
                        Notification.Position.MIDDLE);
                        notification.open();
                
            }*/
            parser.AddNote(new Note(5, title.getValue(), content.getValue(), tags.getValue()));
            parser.SaveNotes();
            Notification notification = new Notification(
            "Note was saved successfully!", 2000,
            Notification.Position.MIDDLE);
            notification.open();
        }));

    }
}
