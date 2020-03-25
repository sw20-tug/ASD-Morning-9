package asd_morning_9.note.ui.Dashboard;

import asd_morning_9.note.ui.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "Dashboard", layout = MainLayout.class)
public class DashboardView extends VerticalLayout
{

  public DashboardView() {
    H1 header = new H1("This is the Dashboard");
    add(header);
  }
}
