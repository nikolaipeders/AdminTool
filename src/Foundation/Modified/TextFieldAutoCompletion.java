package Foundation.Modified;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by Nikolai P on 05-06-2022.
 */

public class TextFieldAutoCompletion extends TextFieldValidation
{
    private SortedSet<String> results;
    private ContextMenu resultMenu;

    public TextFieldAutoCompletion()
    {
        this.results = new TreeSet<>();
        this.resultMenu = new ContextMenu();

        search();
    }

    /**
     * Search content in TextField by adding a listener.
     */
    private void search()
    {
        // Add a listener to the TextField
        textProperty().addListener((observable, oldValue, newValue) ->
        {
            String enteredText = getText();

            if (enteredText == null || enteredText.isEmpty())
            {
                resultMenu.hide();
            }
            else // Disregard capital cases and see if entries contains the entered text
            {
                List<String> matchingResults = results.stream().filter(e -> e.toLowerCase().contains(enteredText.toLowerCase())).collect(Collectors.toList());

                if (!matchingResults.isEmpty()) // Then there is at least one result the menu can be filled with content
                {
                    if (!resultMenu.isShowing())
                    {
                        resultMenu.show(this, Side.BOTTOM, 0, 0); // dx and dy is off-set. We don't want that!
                    }
                    // Only fill menu if resultMenu is showing or else it won't have a place to fill data
                    fillMenu(matchingResults);
                }
            }
        });
        // If TextField is out of focus hide the menu
        focusedProperty().addListener(((observable, oldValue, newValue) -> resultMenu.hide()));
    }

    /**
     * Fill context menu with results from the search method.
     */
    private void fillMenu(List<String> searchResult)
    {
        // List of items we want to show
        List<CustomMenuItem> menuItems = new LinkedList<>();

        // Setting max entries to 5 for performance
        int maxEntries = 5;

        // The total count
        int count = Math.min(searchResult.size(), maxEntries);

        // Show a Label for each search result
        for (int i = 0; i < count; i++)
        {
            final String result = searchResult.get(i);

            Label entryLabel = new Label(result);

            // CustomMenuItem allows "setOnAction" which a label doesn't
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);

            menuItems.add(item);

            // Transfer result to TextField and close menu
            item.setOnAction(actionEvent -> {
                setText(result);
                positionCaret(result.length());
                resultMenu.hide();
            });
        }

        // Refresh menu
        resultMenu.getItems().clear();
        resultMenu.getItems().addAll(menuItems);
    }

    /**
     * Get possible results
     */
    public SortedSet<String> getResults()
    {
        return results;
    }
}
