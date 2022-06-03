package Foundation.Modified;

import UI.TableViews.TWOffices;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFieldValidation extends TextField
{
    public boolean isWrongInput = false;
    String stdStyle = getStyle();
    Pattern pattern;

    public TextFieldValidation()
    {
    }

    public void validate(String regex)
    {
        if (regex.equalsIgnoreCase("time"))
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

        Matcher matcher = pattern.matcher(getText());
        boolean matchFound = matcher.find();
        if (matchFound)
        {
            isWrongInput = false;
            changeBackgroundColor(isWrongInput);
        }
        else // Show a tooltip explaining the mistake!
        {
            isWrongInput = true;
            changeBackgroundColor(isWrongInput);

            Tooltip tooltipWrong = new Tooltip();

            if (regex.equalsIgnoreCase("time"))
            {
                tooltipWrong.setText("Input must be of time format MM:SS");
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
