package jdbc.library;
import java.sql.*;
import javax.swing.table.AbstractTableModel;

/**
 * ResultSet table model
 * @author Tumi
 */
public class ResultSetTableModel extends AbstractTableModel{
    
    //declare database connections
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    
    //keep track of database connection
    private boolean connectedToDatabase = false;

    /**
     * Constructor initializes resultSet and obtain its 
     * meta data object; determine the number of rows;
     */

    public ResultSetTableModel(String url, String username,
            String password) throws SQLException{

        //connect to database
        connection = DriverManager.getConnection(url,username,
                password);
        //create statement to query the data
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //update database status
        connectedToDatabase = true;
        //set query and execute it
       // setQuery(query);
    }
    
    //get class that represent class type
    @Override
    public Class getColumnClass(int column) 
            throws IllegalStateException{
        //ensure database connection is available
        if(!connectedToDatabase) {
            throw new IllegalStateException(
                    "Not connected to Database");
        }
        //determine java class of column
        try{
            String className = metaData.getColumnClassName(
                    column + 1);
            //return Class object that represents className
            return Class.forName(className);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        //if problems occur above, assume type Object
        return Object.class;
    }
    
    //get number of columns in ResultSet
    @Override
    public int getColumnCount() throws 
            IllegalStateException{
        if(!connectedToDatabase){
            throw new IllegalStateException(
                    "Not connected to Database");
        }
        //determine number of columns
        try{
            return metaData.getColumnCount();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        //if problems occur above, return 0 for number 
        //of column
        return 0;
    }
    
    //get aparticular column in ResultSet
    @Override
    public String getColumnName(int column) throws
            IllegalStateException{
        if(!connectedToDatabase){
            throw new IllegalStateException(
                    "Not Connected to Database");
        }
        //determine column name
        try{
            return metaData.getColumnName(column + 1);
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        //if problems, return empty string for column name
        return "";
    }
    
    //return number of row in ResultSet
    @Override
    public int getRowCount() throws
            IllegalStateException{
        //ensure database connection is available
        if(!connectedToDatabase){
            throw new IllegalStateException(
                    "Not Connected to Database");
        }
        return numberOfRows;
    }
    
    //obtain value in particular row and column
    @Override
    public Object getValueAt(int row, int column)
            throws IllegalStateException{
        //ensure database connection is available
        if(!connectedToDatabase){
            throw new IllegalStateException(
                    "Not Connected to Database");
        }
        //obtained a value at specified ResultSet row and column
        try{
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        //if problems, return empty string object
        return "";
    }
    
    //set new database query string
    public void setQuery(String query) throws
            SQLException,IllegalStateException{
        
        //ensure database connection is available
        if(!connectedToDatabase){
            throw new IllegalStateException("Not connected to Database");
        }
        
        //specify query and execute it
        resultSet = statement.executeQuery(query);
        //obtain meta data for ResultSet
        metaData = resultSet.getMetaData();
        //determine number of rows in ResusltSet
        resultSet.last();
        //get row number
        numberOfRows = resultSet.getRow();
        //notify JTable that model has changed
        fireTableStructureChanged();
    }
    //close Statement connection
    public void disconnectFromDatabase(){
        if(connectedToDatabase){
            //close Statement and Connection
            try{
                resultSet.close();
                statement.close();
                connection.close();
            }catch(SQLException sqlException){
                sqlException.printStackTrace();
            }finally{
                connectedToDatabase = false;
            }
        }
    }
}