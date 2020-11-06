package View_Controllers;

import java.time.Instant;
import java.time.LocalTime;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * Lambda expression interface that retrieves values from DatePicker and ComboBox fields and  then converts them to an instant value
 */
public interface TimeConv {
    /**
     * @param date DatePicker field that allows input of LocalDate
     * @param time ComboBox field that allows input of LocalTime
     * @return value based on the DatePicker and ComboBox fields
     */
    Instant toInstant(DatePicker date, ComboBox<LocalTime> time);
}
