package utilities;

import dbModels.UserProfile;
import org.apache.commons.dbutils.BeanProcessor;
import org.junit.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtilsV2 {

    private static Connection connection;
    private static Statement statement;
    //private static final String JDBC_URL = ConfigReader.getProperty("jdbc_url");
    private static final String JDBC_URL = "jdbc:mysql://3.129.60.236:3306/digitalbank?user=digitaluser&password=Demo123!";
    private static BeanProcessor beanProcessor;



    // Opening connection to a DB, if connection is not yet opened.
    public static void open(){
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(JDBC_URL);
                statement = connection.createStatement();
                beanProcessor = new BeanProcessor();

            }
        }catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Can't establish connection to DB");
        }
    }

    public static void close() {
        try {
            if(statement != null) statement.close();
            if(connection != null) connection.close();
            connection = null;
            statement = null;
        }catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Can't close connection to DB");
        }

    }

    // Get Column names by using Result Set metadata
    public  static List<String> getColumnNames(ResultSet resultSet){
        List<String> columnNames = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++ ){
                columnNames.add(metaData.getColumnName(i));
            }
        }catch (SQLException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }

        return  columnNames;
    }

    //VarArgs =>
    //if you provide only 1 String it will go as a String(String query), 2nd String will go as an array of String (String, ["String"])
    //if you provide 3 => (String query, ["str1", "str2"])
    /*
    SELECT *
    FROM digitalbank.user_profile
    WHERE id = ?;

    PreparedStatement preparedStatement = connection.prepareStatement(query); => helps us to get parameters from '?';
     */
    public static ResultSet query(String query, Object... params) {

        if (connection == null) open();

        try {
            if(params.length == 0) return statement.executeQuery(query);
            else {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                //to replace our question mark with the actual params
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i+1,params[i]);
                }
                return preparedStatement.executeQuery();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Not able to execute query");
        }
        return null;
    }

    // T - Declares generic data type
    // In a way it postpones the Data type declaration
    public static <T> List<T> convertResultSetToBeans(ResultSet resultSet, Class<T> beanClass){
        try {
            return beanProcessor.toBeanList(resultSet, beanClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }




    //Key - column name, value - data in that column
    public static List<Map<String, Object>> convertResultSetToListOfMaps(ResultSet resultSet){
        List<Map<String, Object>> table = new ArrayList<>();
        List<String> columnNames = getColumnNames(resultSet);

        //Populate table From result set
        //Iterate through each row
        while (true) {
            //Map is created for each row
            Map<String, Object> row = new HashMap<>();
            try {
                if (!resultSet.next()) break;
                // Iterate through each column in order to populate the map
                for (String columnName : columnNames) {
                    row.put(columnName, resultSet.getObject(columnName));
                }

                table.add(row);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return table;
    }

    public static void main(String[] args) {
        UserProfile fromDB = UserProfile.getById(5);
        UserProfile fromAPI = new UserProfile();
        Assert.assertEquals(fromDB, fromAPI);
        System.out.println(UserProfile.getById(5));
    }
}
