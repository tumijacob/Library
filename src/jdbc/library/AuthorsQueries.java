package jdbc.library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Tumi
 */
public class AuthorsQueries {
    //database connection
    private final String DATABASE_URL = "jdbc:mysql://localhost/books";
    private final String USERNAME = "deitel";
    private final String PASSWORD = "deitel";
    private Connection connection = null;
    
    //queries to query database
    private PreparedStatement selectAllAuthorsByNames;
    private PreparedStatement insertAuthors;
    private PreparedStatement insertISBN;
    private PreparedStatement insertTitles;

    public AuthorsQueries() {
        try{
            //connecting to database
            connection = DriverManager.getConnection(DATABASE_URL,USERNAME,
                    PASSWORD);
            
            // create query that select last name and fist name of the author
            // given last name or first name
            String searchQuery = "SELECT FirstName,LastName FROM Authors WHERE "
                    + "LastName = ? OR FirstName  = ?";
            selectAllAuthorsByNames = connection.prepareStatement(searchQuery);
            
            //create query that add new entry into the Authors table
            String insertIntoAthoursQuery = "INSERT INTO Authors(FirstName, "
                    + "LastName) VALUES(?, ?)";
            insertAuthors = connection.prepareStatement(insertIntoAthoursQuery);
            
            //create a query that add new entry into the AuthorsISBN table
            String insertIntoISBNQuery = "INSERT INTO AuthorISBN(AuthorID, "
                    + "ISBN) VALUES(?, ?)";
            insertISBN = connection.prepareStatement(insertIntoISBNQuery);
            
            //create a query that add new entry into the Titles table
            String insertIntoTitles = "INSERT INTO Titles(ISBN, Title, "
                    + "EditionNumber, Copyright) VALUES(?, ?, ?, ?)";
            insertTitles = connection.prepareStatement(insertIntoTitles);
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    /**
     * Select all authors by specific first name or last name
     * @return the list of selected authors
     */
    public List<Author> getAllAuthorsByNames(String fname, String lname){
        
       List<Author> results = null;
       ResultSet resultSet = null;
       try{
            //specify first name or first name
            selectAllAuthorsByNames.setString(1, fname);
            selectAllAuthorsByNames.setString(2, lname);
            resultSet = selectAllAuthorsByNames.executeQuery();
            results = new ArrayList<>();
            while(resultSet.next()){
                results.add(new Author(resultSet.getString("FirstName"),
                        resultSet.getString("LastName")));
            }
       }catch(SQLException sqlException){
           sqlException.printStackTrace();
           close();
       }finally{
            try{
                resultSet.close();
            }catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
       return results;
    }
    public int insertAuthors(List<String> authorFirstNames,
            List<String> authorLastNames ){
        
        int result = 0;
        ResultSet resultSet = null;
        try{
            Statement statement = connection.createStatement();
            for(int i = 0; i < authorFirstNames.size(); i++){
                resultSet = statement.executeQuery("SELECT AuthorID FROM Authors"
                        + " WHERE FirstName = '" + authorFirstNames.get(i) + 
                        "' AND LastName = '" + authorLastNames.get(i) + "'");
                resultSet.last();
                if(resultSet.getRow() == 0){
                    insertAuthors.setString(1, authorFirstNames.get(i));
                    insertAuthors.setString(2, authorLastNames.get(i));
                    result += insertAuthors.executeUpdate();
                }
            }
            if((result) != authorFirstNames.size()){
                statement.execute("ROLLBACK");
            }else{
                statement.execute("COMMIT");
            }
            statement.close();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            close();
        }
        return result;
    }
    
    //insert new entries into the database
    public int insertIntoTitles(String isbn, String title,int editionNumber,
            int copyright){
        
        int result = 0;
        
        try{
            insertTitles.setString(1, isbn);
            insertTitles.setString(2, title);
            insertTitles.setInt(3, editionNumber);
            insertTitles.setInt(4, copyright);
            result = insertTitles.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            close();
        }
        return result;
    }
    
    public int insertIntoAurthorISBN(String isbn,List<String> authorFirstNames,
            List<String> authorLastNames){
        
        int result = 0;
     
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = null; 
            //add new entry into AuthorISBN table
            for(int i = 0; i < authorFirstNames.size();i++){
                resultSet = statement.executeQuery("SELECT AuthorID FROM Authors"
                        + " WHERE FirstName = '" + authorFirstNames.get(i) + 
                        "' AND LastName = '" + authorLastNames.get(i) + "'");
                resultSet.last();
                if(resultSet.getRow() == 1){
                    insertISBN.setInt(1, resultSet.getInt("AuthorID"));
                    insertISBN.setString(2,isbn);
                    result += insertISBN.executeUpdate();
                }
            }
            if(result != authorFirstNames.size()){
                statement.execute("ROLLBACK");
            }else{
                statement.execute("COMMIT");
            }
            statement.close();
            
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
            close();
        }
        return result;
    }
    public boolean primaryKeyISBN(String isbn){
        boolean exit = true;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;
            
            resultSet = statement.executeQuery("SELECT ISBN FROM AuthorISBN"
                    + " WHERE ISBN = '"+ isbn +"'");
            resultSet.last();
            if(resultSet.getRow() == 0){
                exit = false;
            }
            statement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return exit;
    }
    
    public List<String> allUsers(int id){
        
        ResultSet resultSet = null;
        Statement statement = null;
        List<String> user = new ArrayList<>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE "
                    + "UserID = " + id);
            
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            while(resultSet.next()){
                for(int i = 2; i <= numberOfColumns; i++){
                    user.add(resultSet.getObject(i).toString());
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return user;
    }
    
    public void close(){
        
        try{
            connection.close();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
