package DBUtils;

import Models.*;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Handles db queries
 */
public class DBQuery 
{
    private final ObservableList<Appointment> appointment_list = FXCollections.observableArrayList();
    private final ObservableList<User> user_list = FXCollections.observableArrayList();
    private final ObservableList<Contact> contact_list = FXCollections.observableArrayList();
    private final ObservableList<Customer> customer_list = FXCollections.observableArrayList();
    private final ObservableList<Country> country_list = FXCollections.observableArrayList();
    private final ObservableList<Division> division_list = FXCollections.observableArrayList();
    private User user;

    /**
     * Reads all data from the database
     */
    public void readAllData() 
    {
        readUser_List();
        readCustomer_List();
        readDivision_List();
        readAppointment_List();
        readContact_List();
        readCountry_List();
    }

    /**
     * Gets list of appointment_list
     * @return the list of all appointment_list
     */
    public ObservableList<Appointment> getAppointment_List() 
    {
        return appointment_list;
    }

    /**
     * Reads data from appointment_list table, stores each record in an Appointment object and then in an ObservableList
     */
    private void readAppointment_List() 
    {
        try 
        {
            Statement statement = DBConnection.getDBConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM appointments INNER JOIN contacts ON appointments.contact_id = contacts.contact_id INNER JOIN users ON appointments.user_id = users.user_id");
            while (rs.next())
            {
                int appointmentID = Integer.parseInt(rs.getString("appointment_id").trim());
                String title = rs.getString("title").trim();
                String description = rs.getString("description").trim();
                String location = rs.getString("location").trim();
                String type = rs.getString("type").trim();
                Instant start = rs.getTimestamp("start").toLocalDateTime().toInstant(ZoneOffset.ofHours(0));
                Instant end = rs.getTimestamp("end").toLocalDateTime().toInstant(ZoneOffset.ofHours(0));
                String username = rs.getString("user_name").trim();
                String contact = rs.getString("contact_name").trim();
                int customerID = Integer.parseInt(rs.getString("customer_id").trim());
                int userID = Integer.parseInt(rs.getString("user_id").trim());
                int contactID = Integer.parseInt(rs.getString("contact_id").trim());
                appointment_list.add(new Appointment(appointmentID, title, description, location, type, start, end, username, contact, customerID, userID, contactID));
            }
        }
        catch (SQLException | NumberFormatException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Adds a record to the appointment_list table when a new Appointment object is created
     * @param appointment
     */
    public void addAppointment(Appointment appointment) 
    {
        if (appointment != null)
            appointment_list.add(appointment);
        try 
        {
            String sql = "INSERT INTO appointments (appointment_id, title, description, location, type, start, end, create_date, created_by, last_update, last_updated_by, customer_id, user_id, contact_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql);
            ps.setString(1, Integer.toString(appointment.getAppointmentID()));
            ps.setString(2, appointment.getTitle());
            ps.setString(3, appointment.getDescription());
            ps.setString(4, appointment.getLocation());
            ps.setString(5, appointment.getType());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.ofInstant(appointment.getStart(), ZoneOffset.ofHours(0))));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.ofInstant(appointment.getEnd(), ZoneOffset.ofHours(0))));
            ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(9, getUser().getUser_name());
            ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(11, getUser().getUser_name());
            ps.setString(12, Integer.toString(appointment.getCustomerID()));
            ps.setString(13, Integer.toString(appointment.getUserID()));
            ps.setString(14, Integer.toString(appointment.getContactID()));
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Updates the appointment_list table whenever an Appointment object is updated
     * @param appointment
     */
    public void updateAppointment(Appointment appointment) 
    {
        for (int i = 0; i < appointment_list.size(); i++) 
        {
            if (appointment_list.get(i).getAppointmentID() == appointment.getAppointmentID()) 
            {
                appointment_list.set(i, appointment);
                break;
            }
        }
        try 
        {
            String sql = "UPDATE appointments SET title = ?, description = ?, location = ?, type = ?, start = ?, end = ?, last_update = ?, last_updated_by = ?, customer_id = ?, user_id = ?, contact_id = ? WHERE appointment_id = ?";
            PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql);
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.ofInstant(appointment.getStart(), ZoneOffset.ofHours(0))));
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.ofInstant(appointment.getEnd(), ZoneOffset.ofHours(0))));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, getUser().getUser_name());
            ps.setString(9, Integer.toString(appointment.getCustomerID()));
            ps.setString(10, Integer.toString(appointment.getUserID()));
            ps.setString(11, Integer.toString(appointment.getContactID()));
            ps.setString(12, Integer.toString(appointment.getAppointmentID()));
            ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Deletes a record in the appointment_list table in the database whenever an Appointment object is deleted
     * @param appointment
     */
    public void deleteAppointment(Appointment appointment) 
    {
        appointment_list.remove(appointment);
        try 
        {
            String sql = "DELETE FROM appointments WHERE appointment_id = ? ";
            PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql);
            ps.setString(1, Integer.toString(appointment.getAppointmentID()));
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Gets list of all users
     * @return list of all users
     */
    public ObservableList<User> getUser_List() 
    {
        return user_list;
    }

    /**
     * Reads from the user_list table, stores each record in an User object, and stores the User objects in an ObservableList
     */
    public void readUser_List() 
    {
        try 
        {
            Statement statement = DBConnection.getDBConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next())
            {
                int id = Integer.parseInt(rs.getString("user_id").trim());
                String name = rs.getString("user_name").trim();
                String password = rs.getString("password").trim();
                user_list.add(new User(id, name, password));
            }
        }
        catch (SQLException | NumberFormatException e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Gets list of all contacts
     * @return list of all contacts
     */
    public ObservableList<Contact> getContact_List() 
    {
        return contact_list;
    }

    /**
     * Reads data from the contact_list table, stores each record in an Contact object, and stores the Contact objects in an ObservableList
     */
    public void readContact_List() 
    {
        try 
        {
            Statement statement = DBConnection.getDBConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM contacts");
            while (rs.next()) 
            {
                int id = Integer.parseInt(rs.getString("contact_id").trim());
                String name = rs.getString("contact_name").trim();
                String email = rs.getString("email").trim();
                contact_list.add(new Contact(id, name, email));
            }
        }
        catch (SQLException | NumberFormatException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Gets list of all customer_list
     * @return list of all customer_list
     */
    public ObservableList<Customer> getCustomer_List() 
    {
        return customer_list;
    }

    /**
     * Reads data from the customer_list table in the database, stores each record in an Customer object, and stores the Customer objects in an ObservableList
     */
    public void readCustomer_List() 
    {
        try 
        {
            Statement statement = DBConnection.getDBConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM customers INNER JOIN first_level_divisions ON customers.division_id = first_level_divisions.division_id INNER JOIN countries ON first_level_divisions.country_id = countries.country_id");
            while (rs.next()) 
            {
                int customerID = Integer.parseInt(rs.getString("customer_id").trim());
                String name = rs.getString("customer_name").trim();
                String address = rs.getString("address").trim();
                String postalCode = rs.getString("postal_code").trim();
                String phone = rs.getString("phone").trim();
                String division = rs.getString("division").trim();
                String country = rs.getString("country").trim();
                int divisionID = Integer.parseInt(rs.getString("division_id").trim());
                customer_list.add(new Customer(customerID, name, address, postalCode, phone, division, country, divisionID));
            }
        }
        catch (SQLException | NumberFormatException e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Adds an entry to the customer_list table when a new Customer object is created
     * @param customer
     */
    public void addCustomer(Customer customer) 
    {
        if (customer != null)
            customer_list.add(customer);
        try 
        {
            String sql = "INSERT INTO customers (customer_id, customer_name, address, postal_code, phone, create_date, created_by, last_update, last_updated_by, division_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql);
            ps.setString(1, Integer.toString(customer.getCustomerID()));
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getPostalCode());
            ps.setString(5, customer.getPhone());
            ps.setString(6, LocalDateTime.now().toString()); // ADD DATE
            ps.setString(7, getUser().getUser_name());
            ps.setString(8, LocalDateTime.now().toString()); // ADD DATE
            ps.setString(9, getUser().getUser_name());
            ps.setString(10, Integer.toString(customer.getDivisionID()));
            ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Updates the customer_list table whenever a Customer object is updated
     * @param customer 
     */
    public void updateCustomer(Customer customer) 
    {
        for (int i = 0; i < customer_list.size(); i++) 
        {
            if (customer_list.get(i).getCustomerID() == customer.getCustomerID()) 
            {
                customer_list.set(i, customer);
                break;
            }
        }
        try 
        {
            String sql = "UPDATE customers SET customer_name = ?, address = ?, postal_code = ?, phone = ?, last_update = ?, last_updated_by = ?, division_id = ? WHERE customer_id = ?";
            PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPostalCode());
            ps.setString(4, customer.getPhone());
            ps.setString(5, LocalDateTime.now().toString()); 
            ps.setString(6, getUser().getUser_name());
            ps.setString(7, Integer.toString(customer.getDivisionID()));
            ps.setString(8, Integer.toString(customer.getCustomerID()));
            ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Deletes entry in the customer_list table when a Customer object is deleted
     * @param customer 
     */
    public void deleteCustomer(Customer customer) 
    {
        customer_list.remove(customer);
        try 
        {
            String sql = "DELETE FROM customers WHERE customer_id = ? ";
            PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql);
            ps.setString(1, Integer.toString(customer.getCustomerID()));
            ps.executeUpdate();
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Gets list of all division_list
     * @return list of all division_list
     */
    public ObservableList<Division> getDivision_List() 
    {
        return division_list;
    }

    /**
     * Reads the division_list table, stores each record in an Division object, and stores the Division objects in an ObservableList
     */
    public void readDivision_List() 
    {
        try 
        {
            Statement statement = DBConnection.getDBConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM first_level_divisions INNER JOIN countries ON first_level_divisions.country_id = countries.country_id");
            while (rs.next()) 
            {
                int id = Integer.parseInt(rs.getString("division_id").trim());
                String name = rs.getString("division").trim();
                String country = rs.getString("country").trim();
                division_list.add(new Division(id, name, country));
            }
        }
        catch (SQLException | NumberFormatException e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Gets list of all country_list
     * @return list of all country_list
     */
    public ObservableList<Country> getCountry_List() 
    {
        return country_list;
    }

    /**
     * Reads the country_list table, stores each record in an Country object, and stores the Country objects in an ObservableList
     */
    public void readCountry_List() 
    {
        try 
        {
            Statement statement = DBConnection.getDBConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM countries");
            while (rs.next()) 
            {
                int id = Integer.parseInt(rs.getString("country_id").trim());
                String name = rs.getString("country").trim();
                country_list.add(new Country(id, name));
            }
        }
        catch (SQLException | NumberFormatException e) 
        {
            System.out.println(e);
        }
    }

    /**
     * Gets a User object representing the current user
     * @return a User object representing the current user
     */
    public User getUser() 
    {
        return user;
    }

    /**
     * Sets a User object representing the current user
     * @param user a User object representing the current user
     */
    public void setUser(User user) 
    {
        this.user = user;
    }
}