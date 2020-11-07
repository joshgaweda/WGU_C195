package Models;

/**
 * Creates Division objects that represent first level divisions where the customers live
 */
public class Division {

    private int div_id;
    private String div_name;
    private String div_country;

    /**
     * Class constructor
     * @param id the division ID
     * @param name the division name
     * @param country the country name
     */
    public Division(int id, String name, String country) 
    {
        this.div_id = id;
        this.div_name = name;
        this.div_country = country;
    }

    /**
     * Gets the division ID
     * @return the division ID
     */
    public int getDiv_ID() 
    {
        return div_id;
    }

    /**
     * Sets the division ID
     * @param id the division ID
     */
    public void setDiv_ID(int id) 
    {
        this.div_id = id;
    }

    /**
     * Gets the division name
     * @return the division name
     */
    public String getDiv_name() 
    {
        return div_name;
    }

    /**
     * Sets the division division name
     * @param div_name the division name
     */
    public void setDiv_name(String div_name) 
    {
        this.div_name = div_name;
    }

    /**
     * Gets the associated country name
     * @return the associated country name
     */
    public String getDiv_country() 
    {
        return div_country;
    }

    /**
     * Sets the associated country name
     * @param div_country the associated country name
     */
    public void setDiv_country(String div_country) 
    {
        this.div_country = div_country;
    }

     /**
     * Default class constructor
     */
    public Division() { }
}
