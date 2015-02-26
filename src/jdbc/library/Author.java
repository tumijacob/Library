package jdbc.library;

/**
 *
 * @author Tumi
 */
public class Author {
    private String fname;
    private String lname;

    /**
     * Constructs author with given first name and last name 
     */
    public Author(String fname, String lname) {
        setFname(fname);
        setLname(lname);
    }
    
    /**
     * Getter for the first name of the author
     * @return the first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * Setter for the first name
     * @param fname the first name of the author
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Getter for the last name of the author
     * @return the last name
     */
    public String getLname() {
        return lname;
    }

    /**
     * Setter for the last name
     * @param lname the last name of the author
     */
    public void setLname(String lname) {
        this.lname = lname;
    }
}
