package View_Controllers;

import java.time.LocalTime;

import javafx.scene.control.ComboBox;

/**
 * Lambda expression to convert hours from int to LocalTime and provide them to a ComboBox field
 */
public interface Populate {
    /**
     * @param field the ComboBox that will contain the hours
     * @param h1 the first hour value
     * @param h2 the second hour value
     * @param h3 the third hour value
     * @param h4 the fourth hour value
     * @param h5 the fifth hour value
     * @param h6 the sixth hour value
     * @param h7 the seventh hour value
     */
    void addAll(ComboBox<LocalTime> field, int h1, int h2, int h3, int h4, int h5, int h6, int h7);
}
