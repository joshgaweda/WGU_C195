package View_Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import Models.*;
import DB.DBQuery;
import java.io.IOException;

import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * An abstract class that contains variables and methods used by various other controllers
 */
public abstract class MultiController implements Initializable 
{

    @FXML
    protected TextField id;

    @FXML
    protected TextField title;

    @FXML
    protected TextField description;

    @FXML
    protected ComboBox<String> location;

    @FXML
    protected ComboBox<String> type;

    @FXML
    protected DatePicker date;

    @FXML
    protected ComboBox<LocalTime> startTime;

    @FXML
    protected ComboBox<LocalTime> endTime;

    @FXML
    protected ComboBox<Integer> customer;

    @FXML
    protected ComboBox<String> user;

    @FXML
    protected ComboBox<String> contact;

    @FXML
    protected TextField name;

    @FXML
    protected TextField address;

    @FXML
    protected TextField postalCode;

    @FXML
    protected TextField phone;

    @FXML
    protected ComboBox<String> division;

    @FXML
    protected ComboBox<String> country;

    @FXML
    protected Button loginButton;

    @FXML
    protected Button addButton;

    @FXML
    protected Button modifyButton;

    @FXML
    protected Button deleteButton;

    @FXML
    protected Button reportsButton;

    @FXML
    protected Button logoutButton;

    @FXML
    protected Button cancelButton;

    @FXML
    protected Button addSaveButton;

    @FXML
    protected Button modifySaveButton;

    @FXML
    protected Label errorLabel;

    @FXML
    protected ToggleGroup radio;

    @FXML
    protected RadioButton typeMonthRadio;

    @FXML
    protected RadioButton contactRadio;

    @FXML
    protected RadioButton locationRadio;

    protected DBQuery data;
    protected Appointment selectedAppointment;
    protected Customer selectedCustomer;
    
    /**
     * Loads the Appointments screen. Passes the address of the fxml file and a new instance of the controller class to the loadScreen() method. Sets a boolean value indicating whether this is the first time the screen is accessed after logging in
     * @param event mouse input when the user clicks the Log In button or Appointments button
     */
    @FXML
    protected void loadAppointments(MouseEvent event) 
    {
        try 
        {
            boolean alert = false;
            if (event.getSource() == loginButton)
                alert = true;
            String fxml = "/View_Controllers/Appointments.fxml";
            AppointmentsController controller = new AppointmentsController(data, alert);
            loadScreen(event, fxml, controller);
        }
        catch (Exception exception) 
        {
            System.out.println(exception);
        }
    }

    /**
     * Loads the Customers screen. Passes the address of the fxml file and a new instance of the controller class to the loadScreen() method
     * @param event mouse input when the user clicks the Customers button
     */
    @FXML
    protected void loadCustomers(MouseEvent event)
    {
        try 
        {
            String fxml = "/View_Controllers/Customers.fxml";
            CustomersController controller = new CustomersController(data);
            loadScreen(event, fxml, controller);
        }
        catch (Exception exception) 
        {
            System.out.println(exception);
        }
    }

    /**
     * Loads the TypeMonthReport screen. Passes the address of the fxml file and a new instance of the controller class to the loadScreen() method
     * @param event mouse input when the user clicks the Customers button
     */
    @FXML
    protected void loadReports(MouseEvent event) 
    {
        try 
        {
            String fxml = "/View_Controllers/TypeMonthReport.fxml";
            TypeMonthReportController controller = new TypeMonthReportController(data);
            loadScreen(event, fxml, controller);
        }
        catch (IOException exception) 
        {
            System.out.println(exception);
        }
    }

    /**
     * Loads the ContactReport screen. Passes the address of the fxml file and a new instance of the controller class to the loadScreen() method
     * @param event mouse input when the user clicks the Customers button
     */
    @FXML
    protected void loadContactReport(MouseEvent event)
    {
        try 
        {
            String fxml = "/View_Controllers/ContactReport.fxml";
            ContactReportController controller = new ContactReportController(data);
            loadScreen(event, fxml, controller);
        }
        catch (IOException exception) 
        {
            System.out.println(exception);
        }
    }

    /**
     * Loads the LocationReport screen. Passes the address of the fxml file and a new instance of the controller class to the loadScreen() method
     * @param event mouse input when the user clicks the Customers button
     */
    @FXML
    protected void loadLocationReport(MouseEvent event) 
    {
        try 
        {
            String fxml = "/View_Controllers/LocationReport.fxml";
            LocationReportController controller = new LocationReportController(data);
            loadScreen(event, fxml, controller);
        }
        catch (IOException exception) 
        {
            System.out.println(exception);
        }
    }

    /**
     * Loads a new screen. Receives details about which screen to load from the classes above
     * @param event mouse input when the user clicks a button
     * @param fxml the address of the fxml document
     * @param controller a new instance of the controller class
     * @throws java.io.IOException .
     */
    protected void loadScreen(MouseEvent event, String fxml, MultiController controller) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        ResourceBundle rb = ResourceBundle.getBundle("Properties/language");
        loader.setController(controller);
        loader.setResources(rb);
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
    *Lambda expression to convert hours from int to LocalTime and provide them to a ComboBox field
    */
    interface TimePop 
    {
        void addAll(ComboBox<LocalTime> field, int t1, int t2, int t3, int t4, int t5, int t6, int t7, 
                int t8, int t9, int t10, int t11, int t12, int t13, int t14, int t15, int t16, int t17, int t18, int t19,
                int t20, int t21, int t22, int t23, int t24);
    }
    
    /**
     * Prefills the available options in the ComboBoxes on the Add Appointment and Modify Appointment screens
     * <p>Lambda Expression: Improves the code by avoiding repetition of the verbose syntax for converting an hour from an integer to a LocalTime</p>
     */
    protected void fillAppointmentOptions() 
    {
        location.getItems().addAll("Atlanta", "Boston", "Corvallis");
        type.getItems().addAll("Interview", "Meeting", "Planning", "Lunch");

        TimePop comboBox = (field, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24) -> {
            ObservableList<Integer> hours = FXCollections.observableArrayList();
            hours.addAll(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24);
            for (int h : hours)
                field.getItems().add(LocalDateTime.of(LocalDate.now(),LocalTime.of(h,0)).toInstant(ZoneOffset.ofHours(0)).atZone(ZoneId.of(TimeZone.getDefault().getID())).toLocalTime());
        };
        comboBox.addAll(startTime, 0, 1, 2, 3 ,4 ,5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 15, 17, 18, 19, 20, 21, 22, 23);
        comboBox.addAll(endTime, 0, 1, 2, 3 ,4 ,5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);

        for (Customer c : data.get_Customer_List())
            customer.getItems().add(c.getCustomerID());
        for (User u : data.get_User_List())
            user.getItems().add(u.getUser_name());
        for (Contact c : data.get_Contact_List())
            contact.getItems().add(c.getName());
    }

    /**
     * Prefills the available options in the ComboBoxes on the Add Customer and Modify Customer screens
     */
    protected void fillCustomerOptions() 
    {
        for (Division d : data.get_Division_List())
            division.getItems().add(d.getDiv_name());
        for (Country c : data.get_Country_List())
            country.getItems().add(c.getName());
    }

    /**
     * Automatically sets the start time value to 1 hour before the selected end time
     * @param event an action event when the user makes a selection from the endTime ComboBox
     */
    @FXML
    protected void updateStartTime(ActionEvent event) 
    {
        startTime.setValue(endTime.getValue().minusHours(1));
    }

    /**
     * Automatically sets the end time value to 1 hour after the selected start time
     * @param event an action event when the user makes a selection from the startTime ComboBox
     */
    @FXML
    protected void updateEndTime(ActionEvent event) 
    {
        endTime.setValue(startTime.getValue().plusHours(1));
    }

    /**
     * Automatically updates the Division ComboBox values to only those associated with the selected Country
     * @param event an action event when the user makes a selection from the Country ComboBox
     */
    @FXML
    protected void updateDivision(ActionEvent event) 
    {
        ObservableList<String> updateOptions = FXCollections.observableArrayList();
        for (Division d : data.get_Division_List())
            if (country.getValue().equals(d.getDiv_country()))
                updateOptions.add(d.getDiv_name());
        division.setItems(updateOptions);
    }

    /**
     * Automatically sets the Country value to the country associated with the selected First Level Division
     * @param event an action event when the user makes a selection from the Division ComboBox
     */
    @FXML
    protected void updateCountry(ActionEvent event) 
    {
        for (Division d : data.get_Division_List())
            if (d.getDiv_name().equals(division.getValue()))
                country.setValue(d.getDiv_country());
    }
    
    /**
    * Lambda expression interface that retrieves values from DatePicker and ComboBox fields and then converts them to an instant value
    */
    interface TimeConv 
    {
        Instant toInstant(DatePicker date, ComboBox<LocalTime> time);
    }
    
    /**
     * Checks user input for each of the Add Appointment or Modify Appointment screen fields, displays an error message if any of the fields is empty, if the selected date and time are in the past or are outside of business hours (8am-10pm EST), or if a customer has overlapping appointments, then calls a DBQuery method to save the input data to the database, and returns to the appointments screen
     * <p>Lambda Expression (Line 341): This lambda expression reduces code repetition and allows the verbose syntax needed to convert the values of the date and time fields to Instant values to be written only once</p>
     * @param event mouse input when the user clicks the Save button
     */
    @FXML
    protected void saveAppointment(MouseEvent event) 
    {
        if (title.getText().trim().isEmpty()) 
        {
            errorLabel.setText("Please enter a Title");
            return;
        }
        if (description.getText().trim().isEmpty()) 
        {
            errorLabel.setText("Please enter Description");
            return;
        }
        if (location.getSelectionModel().isEmpty()) 
        {
            errorLabel.setText("Please select Location");
            return;
        }
        if (type.getSelectionModel().isEmpty()) 
        {
            errorLabel.setText("Please select Type");
            return;
        }
        if (date.getValue() == null)
        {
            errorLabel.setText("Please enter Date");
            return;
        }
        if (startTime.getValue() == null) 
        {
            errorLabel.setText("Please enter Start Time");
            return;
        }
        if (endTime.getValue() == null) 
        {
            errorLabel.setText("Please enter End Time");
            return;
        }

        TimeConv dateTime = (date, time) -> ZonedDateTime.of(date.getValue(), time.getValue(), ZoneId.of(TimeZone.getDefault().getID())).toInstant();
        String tz = "America/New_York";
        Instant start = dateTime.toInstant(date, startTime);
        Instant end = dateTime.toInstant(date, endTime);

        if (start.isBefore(Instant.now())) 
        {
            errorLabel.setText("Please select future Date and Time");
            return;
        }
        if (start.atZone(ZoneId.of(tz)).toLocalTime().isBefore(LocalTime.of(8,0)) || end.atZone(ZoneId.of(tz)).toLocalTime().isAfter(LocalTime.of(21,0))) 
        {
            errorLabel.setText("Select a time during business hours");
            return;
        }

        if (customer.getSelectionModel().isEmpty()) 
        {
            errorLabel.setText("Please select a Customer ID");
            return;
        }
        for (Appointment a : data.get_Appointment_List()) 
        {
            if (customer.getValue() == a.getCustomerID() && start.equals(a.getStart())) 
            {
                errorLabel.setText("Overlapping appointment");
                return;
            }
        }
        if (contact.getSelectionModel().isEmpty()) 
        {
            errorLabel.setText("Please select a Contact");
            return;
        }
        if (user.getSelectionModel().isEmpty()) 
        {
            errorLabel.setText("Please select a User");
            return;
        }

        int userID = 0, contactID = 0;
        for (User u : data.get_User_List())
            if (u.getUser_name().equals(user.getValue()))
                userID = u.getUser_ID();
        for (Contact c : data.get_Contact_List())
            if (c.getName().equals(contact.getValue()))
                contactID = c.getID();

        Appointment appointment = new Appointment(Integer.parseInt(id.getText()), title.getText().trim(), description.getText().trim(), location.getValue(), type.getValue(), start, end, user.getValue(), contact.getValue(), customer.getValue(), userID, contactID);

        if (event.getSource() == addSaveButton)
            data.add_Appointment(appointment);
        if (event.getSource() == modifySaveButton)
            data.update_Appointment(appointment);

        loadAppointments(event);
    }

    /**
     * Checks user input for each of the Add Customer or Modify Customer screen fields, displays an error message if any of the fields is empty, calls a DBQuery method to save the input data to the database, and returns to the appointments screen
     * @param event mouse input when the user clicks the Save button
     */
    @FXML
    protected void saveCustomer(MouseEvent event)
    {
        if (name.getText().trim().isEmpty())
        {
            errorLabel.setText("Please enter a Name");
            return;
        }
        if (address.getText().trim().isEmpty()) 
        {
            errorLabel.setText("Please enter an Address");
            return;
        }
        if (postalCode.getText().trim().isEmpty()) 
        {
            errorLabel.setText("Please enter a Postal Code");
            return;
        }
        if (phone.getText().trim().isEmpty()) 
        {
            errorLabel.setText("Please enter a Phone Number");
            return;
        }
        if (division.getSelectionModel().isEmpty()) 
        {
            errorLabel.setText("Please select a Division");
            return;
        }
        if (country.getSelectionModel().isEmpty()) 
        {
            errorLabel.setText("Please select a Country");
            return;
        }

        int divisionID = 0;
        for (Division d : data.get_Division_List())
            if (d.getDiv_name().equals(division.getValue()))
                divisionID = d.getDiv_ID();

        Customer customer = new Customer(Integer.parseInt(id.getText().trim()), name.getText().trim(), address.getText().trim(), postalCode.getText().trim(), phone.getText().trim(), division.getValue(), country.getValue(), divisionID);

        if (event.getSource() == addSaveButton)
            data.add_Customer(customer);
        if (event.getSource() == modifySaveButton)
            data.update_Customer(customer);

        loadCustomers(event);
    }

    /**
     * Displays a confirmation prompt before deleting an item
     * @param id the ID of the appointment or customer to be deleted
     * @return a boolean value indicating whether the user wants to proceed
     */
    protected boolean confirmationWindow(String id) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setContentText("Are you sure you want to delete" + id + "?");
        alert.setHeaderText(null);
        alert.setGraphic(null);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) 
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    /**
     * Clears any error messages
     * @param event mouse input when the user clicks away from the item that caused the message
     */
    @FXML
    protected void clearError(MouseEvent event) 
    {
        errorLabel.setText("");
    }
}