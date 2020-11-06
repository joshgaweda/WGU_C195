package Models;

/**
 * Creates User objects that represent the users of the program
 */
public class User {

    private int user_id;
    private String user_name;
    private String user_password;

    /**
     * Class constructor
     * @param id the user ID
     * @param username the user_name
     * @param password the user_password
     */
    public User(int id, String username, String password)
    {
        this.user_id = id;
        this.user_name = username;
        this.user_password = password;
    }

    /**
     * Default class constructor
     */
    public User() { }

    /**
     * Gets the user ID
     * @return the user ID
     */
    public int getUser_ID()
    {
        return user_id;
    }

    /**
     * Sets the user ID
     * @param id the user ID
     */
    public void setUser_ID(int id) 
    {
        this.user_id = id;
    }

    /**
     * Gets the user_name
     * @return the user_name
     */
    public String getUser_name() 
    {
        return user_name;
    }

    /**
     * Sets the users name
     * @param user_name the user_name
     */
    public void setUser_name(String user_name) 
    {
        this.user_name = user_name;
    }

    /**
     * Gets the user_password
     * @return the user_password
     */
    public String getUser_password()
    {
        return user_password;
    }

    /**
     * Sets the users password
     * @param user_password the user password
     */
    public void setUser_password(String user_password) 
    {
        this.user_password = user_password;
    }
}
