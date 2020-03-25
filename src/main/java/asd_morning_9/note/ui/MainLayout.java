package asd_morning_9.note.ui;

import asd_morning_9.note.ui.Dashboard.DashboardView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;

import javax.swing.text.html.ListView;

@CssImport("./styles/MainLayout.css")
public class MainLayout extends AppLayout
{
  public MainLayout() {
    createHeader();
    createDrawer();
  }

  private void createHeader() {
    H1 logo = new H1("Notepad");
    logo.addClassName("logo");

    Icon icon = new Icon(VaadinIcon.PLUS);
    Button addNoteButton = new Button(icon);

    Div placeholder = new Div();
    placeholder.setWidth("100%");

    addNoteButton.addClickListener(this::addNewNote);

    HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, placeholder, addNoteButton); //

    header.setDefaultVerticalComponentAlignment(
    FlexComponent.Alignment.CENTER); //
    header.setWidth("100%");
    header.addClassName("header");


    addToNavbar(header); //

  }

  private void createDrawer() {
    RouterLink homeLink = new RouterLink("Home", MainView.class); //
    RouterLink dashboardLink = new RouterLink("Dashboard", DashboardView.class); //

    homeLink.setHighlightCondition(HighlightConditions.sameLocation()); //
    dashboardLink.setHighlightCondition(HighlightConditions.sameLocation()); //

    VerticalLayout menu = new VerticalLayout(homeLink, dashboardLink);
    menu.setId("menu");


    addToDrawer(menu);
  }

  private void addNewNote(ClickEvent event)
  {
    Notification notification = new Notification(
    "Here should be a dialog to create a new note..", 3000,
    Notification.Position.MIDDLE);
    notification.open();
  }
}
