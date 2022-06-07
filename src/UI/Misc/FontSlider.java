package UI.Misc;

import javafx.geometry.Insets;
import javafx.scene.control.Slider;

/**
 * Created by Nikolai P on 07-06-2022.
 */

public class FontSlider
{
    public static Slider sliderFont;
    public FontSlider()
    {
    }

    public Slider getSliderFont()
    {
        // Slider font size
        sliderFont = new Slider(14, 34, 16);
        sliderFont.setPadding(new Insets(0, 10, 0, 10));
        sliderFont.setMajorTickUnit(2);
        sliderFont.setMinorTickCount(1);
        sliderFont.setShowTickMarks(false);
        sliderFont.setShowTickLabels(false);

        sliderFont.valueProperty().addListener((obs, oldVal, newVal) ->
                sliderFont.setValue(newVal.intValue()));

        return sliderFont;
    }
}
