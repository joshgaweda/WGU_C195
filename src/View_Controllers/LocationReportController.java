package View_Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import Models.Appointment;
import DB.DBQuery;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the Appointments by Location Report screen
 */
public class LocationReportController extends MultiController {

    @FXML
    private TableView<Appointment> atlantaTable;

    @FXML
    private TableColumn<Appointment, Integer> atlantaIDColumn;

    @FXML
    private TableColumn<Appointment, String> atlantaTitleColumn;

    @FXML
    private TableColumn<Appointment, String> atlantaDescriptionColumn;

    @FXML
    private TableColumn<Appointment, String> atlantaTypeColumn;

    @FXML
    private TableColumn<Appointment, String> atlantaStartColumn;

    @FXML
    private TableColumn<Appointment, String> atlantaEndColumn;

    @FXML
    private TableColumn<Appointment, Integer> atlantaCustomerColumn;

    @FXML
    private TableView<Appointment> bostonTable;

    @FXML
    private TableColumn<Appointment, Integer> bostonIDColumn;

    @FXML
    private TableColumn<Appointment, String> bostonTitleColumn;

    @FXML
    private TableColumn<Appointment, String> bostonDescriptionColumn;

    @FXML
    private TableColumn<Appointment, String> bostonTypeColumn;

    @FXML
    private TableColumn<Appointment, String> bostonStartColumn;

    @FXML
    private TableColumn<Appointment, String> bostonEndColumn;

    @FXML
    private TableColumn<Appointment, Integer> bostonCustomerColumn;

    @FXML
    private TableView<Appointment> corvallisTable;

    @FXML
    private TableColumn<Appointment, Integer> corvallisIDColumn;

    @FXML
    private TableColumn<Appointment, String> corvallisTitleColumn;

    @FXML
    private TableColumn<Appointment, String> corvallisDescriptionColumn;

    @FXML
    private TableColumn<Appointment, String> corvallisTypeColumn;

    @FXML
    private TableColumn<Appointment, String> corvallisStartColumn;

    @FXML
    private TableColumn<Appointment, String> corvallisEndColumn;

    @FXML
    private TableColumn<Appointment, Integer> corvallisCustomerColumn;

    /**
     * Class constructor
     * @param data all data retrieved from the database by DBQuery
     */
    public LocationReportController(DBQuery data) {
        this.data = data;
    }

    /**
     * Initializes the MultiController class
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known
     * @param rb The resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateAtlantaTable();
        generateBostonTable();
        generateCorvallisTable();
    }

    /**
     * Generates the Atlanta table view. Filters the schedule of appointments to include only those with a Location set to Atlanta, populates the table view with that data, then refreshes the table view
     */
    private void generateAtlantaTable() {
        ObservableList<Appointment> filterAppointments = FXCollections.observableArrayList();
        for (Appointment a : data.get_Appointment_List())
            if (a.getLocation().equals("Atlanta"))
                filterAppointments.add(a);
        atlantaTable.setItems(filterAppointments);
        atlantaTable.refresh();
    }

    /**
     * Generates the Boston table view. Filters the schedule of appointments to include only those with a Location set to Boston, populates the table view with that data, then refreshes the table view
     */
    private void generateBostonTable() {
        ObservableList<Appointment> filterAppointments = FXCollections.observableArrayList();
        for (Appointment a : data.get_Appointment_List())
            if (a.getLocation().equals("Boston"))
                filterAppointments.add(a);
        bostonTable.setItems(filterAppointments);
        bostonTable.refresh();
    }

    /**
     * Generates the Corvallis table view. Filters the schedule of appointments to include only those with a Location set to Corvallis, populates the table view with that data, then refreshes the table view
     */
    private void generateCorvallisTable() {
        ObservableList<Appointment> filterAppointments = FXCollections.observableArrayList();
        for (Appointment a : data.get_Appointment_List())
            if (a.getLocation().equals("Corvallis"))
                filterAppointments.add(a);
        corvallisTable.setItems(filterAppointments);
        corvallisTable.refresh();
    }

}