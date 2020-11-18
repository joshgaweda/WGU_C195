package Models;

/**
 * Creates User objects that represent the users of the program
 */
public class User {

    private int id;
    private String username;
    private String password;

    /**
     * Class constructor
     * @param id the user ID
     * @param username the username
     * @param password the password
     */
    public User(int id, String username, String password)
    {
        this.id = id;
        this.username = username;
        this.password = password;
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
        return id;
    }

    /**
     * Sets the user ID
     * @param id the user ID
     */
    public void setUser_ID(int id) 
    {
        this.id = id;
    }

    /**
     * Gets the username
     * @return the username
     */
    public String getUsername() 
    {
        return username;
    }

    /**
     * Sets the users name
     * @param username the username
     */
    public void setUsername(String username) 
    {
        this.username = username;
    }

    /**
     * Gets the password
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the users password
     * @param password the user password
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }
}
