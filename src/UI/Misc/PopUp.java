package UI.Misc;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Nikolai P on 03-05-2022.
 */

public class PopUp
{
    public PopUp()
    {
    }

    /**
     * Prompts a small dialog which shows in x amount of seconds
     */
    public void popText(String text, Stage stage, double height, double width)
    {
        try
        {
            Popup popup = new Popup();
            popup.setAutoHide(true);
            popup.setAutoFix(true);
            Label popupLabel = new Label(text);
            popupLabel.setPrefSize(width, height);
            popupLabel.setStyle("-fx-background-color:#FFFFFF;"
                    + " -fx-alignment: center;"
                    + " -fx-text-fill: black;"
                    + " -fx-font-size: 18;"
                    + " -fx-padding: 10px;"
                    + " -fx-background-radius: 7px;"
                    + " -fx-border-color: black;"
                    + " -fx-border-radius: 7px");
            popup.getContent().add(popupLabel);

            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> popup.hide());

            popup.show(stage);
            delay.play();

        }
        catch (Exception e)
        {
            System.out.println("Error with popText");
        }
    }
}

