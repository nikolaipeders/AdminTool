package Foundation.Modified;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class TextFieldValidation extends TextField
{
    public boolean isWrongInput = false;
    Pattern pattern;

    public TextFieldValidation()
    {
    }

    /**
     * Validate a TextField for a given regex
     * @param regex selects what kind of regex to compile
     */
    public void validate(String regex)
    {
        if (regex.equalsIgnoreCase("mm:ss"))
        {
            pattern = Pattern.compile("^([0-5]?[0-9]|2[0-3]):[0-5][0-9]$");
        }
        else if (regex.equalsIgnoreCase("text"))
        {
            pattern = Pattern.compile("[a-z]");
        }
        else if (regex.equalsIgnoreCase("numbers"))
        {
            pattern = Pattern.compile("[0-9]");
        }
        else if (regex.equalsIgnoreCase("date"))
        {
            pattern = Pattern.compile("^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");
        }
        else if (regex.equalsIgnoreCase("mail"))
        {
            pattern = Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");
        }
        if (regex.equalsIgnoreCase("hh:mm:ss"))
        {
            pattern = Pattern.compile("^(?:(?:([01]?\\d|2[0-3]):)?([0-5]?\\d):)?([0-5]?\\d)$");
        }

        // Match TextField with pattern
        Matcher matcher = pattern.matcher(getText());
        boolean matchFound = matcher.find();

        if (matchFound)
        {
            isWrongInput = false;
            changeBackgroundColor(isWrongInput);
        }
        else
        {
            isWrongInput = true;
            changeBackgroundColor(isWrongInput);

            // Show a tooltip explaining the mistake
            Tooltip tooltipWrong = new Tooltip();

            if (regex.equalsIgnoreCase("mm:ss"))
            {
                tooltipWrong.setText("Input must be of time format MM:SS");
            }
            if (regex.equalsIgnoreCase("hh:mm:ss"))
            {
                tooltipWrong.setText("Input must be of time format HH:MM:SS");
            }
            else if (regex.equalsIgnoreCase("text"))
            {
                tooltipWrong.setText("Input must be letters from A to Z");
            }
            else if (regex.equalsIgnoreCase("numbers"))
            {
                tooltipWrong.setText("Input must be digits between 0 and 9");
            }
            else if (regex.equalsIgnoreCase("date"))
            {
                tooltipWrong.setText("Input a date of type DD-MM-YYYY");
            }
            else if (regex.equalsIgnoreCase("mail"))
            {
                tooltipWrong.setText("Input must be a valid mail-address");
            }
            setTooltip(tooltipWrong);
        }
    }

    /**
     * Show a red color if content of TextField is wrong input, else show a white color.
     */
    public void changeBackgroundColor(Boolean isWrongInput)
    {
        if (isWrongInput)
        {
            setStyle("-fx-background-color: #fde0e0");
        }
        else
        {
            setStyle("-fx-background-color: #FFFFFF");
        }
    }
}
