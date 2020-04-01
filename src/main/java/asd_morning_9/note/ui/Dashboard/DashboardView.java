package asd_morning_9.note.ui.Dashboard;

import asd_morning_9.note.JsonParser;
import asd_morning_9.note.Note;
import asd_morning_9.note.ui.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

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


    UnorderedList ui = new UnorderedList();
    ui.setId("notesUI");
    for (Note item : parser.getNotesList())
    {
      Div cont = new Div();

      Header head = new Header();
      head.add(item.getTitle());

      cont.add(head);
      cont.add(item.getContent());
      ListItem li = new ListItem(cont);
      ui.add(li);
    }

    add(ui);

  }
}
