package asd_morning_9.note.ui.Dashboard;

import asd_morning_9.note.JsonParser;
import asd_morning_9.note.Note;
import asd_morning_9.note.ui.MainLayout;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

//import org.graalvm.compiler.graph.Graph;


@Route(value = "Dashboard", layout = MainLayout.class)
@CssImport("./styles/Dashboard.css")
public class DashboardView extends VerticalLayout
{

  private JsonParser parser;

  public DashboardView() {

    parser = new JsonParser();

    parser.ReadNotes();
    //Note new_note = new Note(2, "new Title", "new Content");
    //parser.AddNote(new_note);
    parser.SaveNotes();

    H1 header = new H1("This is the Dashboard");
    add(header);
    HtmlComponent br = new HtmlComponent("br");
    /*TextField title = new TextField();
    title.setLabel("Title");
    title.setClassName("newNoteTitle");

    TextArea content = new TextArea("Content");
    content.getStyle().set("height", "150px");
    content.setPlaceholder("Write here ...");
    content.setClassName("newNoteContent");*/

    UnorderedList ui = new UnorderedList();
    ui.setId("notesUI");
    for (Note item : parser.getNotesList())
    {
      Div cont = new Div();
      Div footer = new Div();

      Header head = new Header();
      head.add(item.getTitle());


      Icon trash = new Icon(VaadinIcon.TRASH);

      footer.add(new Button(trash, event -> {

        parser.DeleteNote(item.getId());
        parser.SaveNotes();

        Notification notification = new Notification(
        "successfully deleted..", 2000,
        Notification.Position.MIDDLE);
        notification.open();
      }));
      String[] bufferTagArray = item.getTags().split(",");
      for(int i = 0; i< bufferTagArray.length; i++)
      {
        {
          cont.add(head);
          cont.add(bufferTagArray[i]); //new
          cont.add("  ");
          cont.add(footer);
        }
      }

      Icon star;
      if (item.getPinned())
      {
         star = new Icon(VaadinIcon.STAR);
      }
      else
      {
        star = new Icon(VaadinIcon.STAR_O);
      }

      footer.add(new Button(star, event -> {
        parser.PinNote(item.getId());
        parser.SaveNotes();

        Notification notification = new Notification(
        "successfully pinned..", 2000,
        Notification.Position.MIDDLE);
        notification.open();
      }));

      Icon edit = new Icon(VaadinIcon.PENCIL);
      footer.add(new Button(edit, event -> {
        Notification notification = new Notification(
                "Here should be a dialog to create a new note..", 100000,
                Notification.Position.MIDDLE);



        TextArea content = new TextArea("Content");
        content.getStyle().set("height", "150px");
        content.setPlaceholder("Rewrite Note ...");
        content.setClassName("newNoteedit");

        Div edit_note_cont = new Div();
        Button editcontent = new Button("Edit", start -> {parser.EditNote(item.getId(), item.getTitle(), content.getValue()); notification.close();});
        edit_note_cont.add(editcontent);
        edit_note_cont.add(content);

        notification.add(edit_note_cont);
        notification.open();

      }));


      cont.add(head);
      cont.add("Content: " + item.getContent());
      cont.add(br);
      cont.add("Tag: " + item.getTags());
      cont.add(footer);
      ListItem li = new ListItem(cont);
      li.addClassName(Integer.toString(item.getId()));
      ui.add(li);

      Div value = new Div();
      value.setText("Select a value");

      CheckboxGroup<String> checkbox1 = new CheckboxGroup<>();
      checkbox1.setLabel("Select Note");
      checkbox1.setItems(item.getTitle());
      checkbox1.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

      checkbox1.addValueChangeListener(event -> {
        if (event.getValue() == null) {
          value.setText("No option selected");
        } else {
          value.setText("Selected: " + event.getValue());
          parser.addId(item.getId());
          //System.out.println(item.getId());
        }
      });

      cont.add(checkbox1);
      cont.add(value);

    }




    Div share_note_cont = new Div();

    EmailField emailField_sender = new EmailField("Email");
    emailField_sender.setClearButtonVisible(true);
    emailField_sender.setErrorMessage("Please enter a valid email address");

    EmailField emailField_recipient = new EmailField("Email");
    emailField_recipient.setClearButtonVisible(true);
    emailField_recipient.setErrorMessage("Please enter a valid email address");

    TextField user_name = new TextField("User name");
    //title.setLabel("User name");

    PasswordField passwordField = new PasswordField();
    passwordField.setLabel("Password");
    passwordField.setPlaceholder("Enter password");
    passwordField.setValue(passwordField.getValue());

    share_note_cont.add(emailField_sender);
    share_note_cont.add(emailField_recipient);
    share_note_cont.add(user_name);
    share_note_cont.add(passwordField);

    add(share_note_cont);


    add(new Button("Share Note", event -> {

      parser.ShareNote(emailField_sender.getValue(),emailField_recipient.getValue(),user_name.getValue(),passwordField.getValue());
      //TextField id = new TextField("id");
      //parser.SaveNotes();
      Notification notification = new Notification(
              "Note was shared successfully!", 2000,
              Notification.Position.MIDDLE);
      notification.open();

    }));

    Div export_note_cont = new Div();

    TextField path = new TextField("Export path");
    export_note_cont.add(path);

    add(export_note_cont);

    add(new Button("Export", event -> {

      path.setValue(path.getValue() + ".json");
      parser.SaveNotes(path.getValue());
      //TextField id = new TextField("id");
      //parser.SaveNotes();
      Notification notification = new Notification(
              "Note was exported successfully!", 2000,
              Notification.Position.MIDDLE);
      notification.open();

    }));

    Div import_note_cont = new Div();

    TextField file = new TextField("Import path");
    import_note_cont.add(file);

    add(import_note_cont);

    add(new Button("Import", event -> {
      parser.ImportNotes(file.getValue());
      parser.SaveNotes();
      //TextField id = new TextField("id");
      //parser.SaveNotes();
      Notification notification = new Notification(
              "Note was imported successfully!", 2000,
              Notification.Position.MIDDLE);
      notification.open();

    }));

    add(ui);

  }
}
