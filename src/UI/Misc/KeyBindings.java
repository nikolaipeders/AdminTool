package UI.Misc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class KeyBindings
{
    public KeyBindings()
    {
    }

    /**
     * Simply just returns a VBox with the different keybindings viable in this application.
     */
    public VBox getView()
    {
        VBox subRoot = new VBox(20);
        subRoot.setPadding(new Insets(100));
        subRoot.setAlignment(Pos.TOP_CENTER);
        subRoot.setStyle("-fx-background-color: #FFFFFF");

        Label titleLabel = new Label("Key Bindings");
        titleLabel.setStyle("-fx-font-size: 24");

        VBox tips = new VBox(20);
        tips.setPadding(new Insets(0, 100, 100, 100));
        tips.setAlignment(Pos.CENTER_LEFT);

        Label tip1 = new Label("[ENTER] to edit a selected item in a tableview");
        tip1.setStyle("-fx-font-size: 18;");

        Label tip2 = new Label("[+] to add a new item in a tableview");
        tip2.setStyle("-fx-font-size: 18;");

        Label tip3 = new Label("[DELETE] to delete an item in a tableview");
        tip3.setStyle("-fx-font-size: 18");

        Label tip4 = new Label("[CTRL] + [F] to search in a tableview");
        tip4.setStyle("-fx-font-size: 18");

        Label tip5 = new Label("[ESCAPE] in the search field to clear search results");
        tip5.setStyle("-fx-font-size: 18");

        Label tip6 = new Label("[CTRL] + [UP ARROW] to move item up in a tableview");
        tip6.setStyle("-fx-font-size: 18");

        Label tip7 = new Label("[CTRL] + [DOWN ARROW] to move item down in a tableview");
        tip7.setStyle("-fx-font-size: 18");

        tips.getChildren().addAll(tip1, tip2, tip3, tip4, tip5, tip6, tip7);

        subRoot.getChildren().addAll(titleLabel, tips);

        return subRoot;
    }
}
