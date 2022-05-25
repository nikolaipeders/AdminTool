package UI.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class ActionBar
{
    String sender;
    UIButton uib = new UIButton();

    public ActionBar(String sender)
    {
        this.sender = sender;
    }

    public HBox getBottomBar()
    {
        // HBox settings
        HBox bottomBar = new HBox(30);
        bottomBar.setAlignment(Pos.CENTER_RIGHT);
        bottomBar.setPadding(new Insets(0, 20, 0, 0));
        bottomBar.setMaxHeight(50);
        bottomBar.setMinHeight(50);

        // Add all items and return parent HBox
        bottomBar.getChildren().addAll(uib.addButton(), uib.editButton(), uib.deleteButton());

        return bottomBar;
    }

    private void addFunction(String sender)
    {
        switch (sender)
        {
            case "Consultants":
                System.out.println("Consultants");
                break;
            case "Offices":
                System.out.println("Offices");
                break;
            case "Projects":
                System.out.println("Projects");
                break;
            case "Tasks":
                System.out.println("Tasks");
                break;
        }
    }
    private void editFunction(String sender)
    {
        switch (sender)
        {
            case "Consultants":
                System.out.println("Consultants");
                break;
            case "Offices":
                System.out.println("Offices");
                break;
            case "Projects":
                System.out.println("Projects");
                break;
            case "Tasks":
                System.out.println("Tasks");
                break;
        }
    }
    private void deleteFunction(String sender)
    {
        switch (sender)
        {
            case "Consultants":
                System.out.println("Consultants");
                break;
            case "Offices":
                System.out.println("Offices");
                break;
            case "Projects":
                System.out.println("Projects");
                break;
            case "Tasks":
                System.out.println("Tasks");
                break;
        }
    }
}
