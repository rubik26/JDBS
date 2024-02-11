package jdbc;

import java.sql.*;

public class ProductDB {
    public static void main(String[] args) throws SQLException {
        try {
            Product phone = new Product();
            phone.setId(1);
            phone.setName("Phone");
            phone.setPrice(35000);

            Product laptop = new Product();
            laptop.setId(2);
            laptop.setName("laptop");
            laptop.setPrice(50000);
            Connection connection = getDbConnection();
            createProduct(connection, phone);
            createProduct(connection, laptop);
            updatePrice(connection);
            deleteProduct(connection);
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }
    private static String url = "jdbc:postgresql://localhost:5432/postgres?serverTimezoneEurope/Yerevan";
    private static String username = "postgres";
    private static String password = "root";

    private static Connection getDbConnection() throws SQLException {
        Connection dbConnection = null;
        try{
            dbConnection = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dbConnection;
    }
    protected static int createProduct(Connection connection, Product product) throws SQLException{
        try {
            String createProduct = "INSERT INTO product(Id, productName, price) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(createProduct);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());

            preparedStatement.setInt(3, product.getPrice());
            return preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return 0;
    }
    protected static int updatePrice(Connection connection) throws SQLException{
        try{
            String updatePrice = "UPDATE product SET Price = Price + 5000 WHERE ProductName ='Apple Vision Pro'";
            PreparedStatement preparedStatement = connection.prepareStatement(updatePrice);
            preparedStatement.executeUpdate(updatePrice);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return 0;
    }
     protected static int deleteProduct(Connection connection) throws SQLException{
        try{
            String deleteProduct = "DELETE FROM product WHERE productName = 'Samsung Galaxy s23'";
            Statement statement = connection.createStatement();
            statement.execute(deleteProduct);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return 0;
    }
    private static void createDbProductTable() throws SQLException{
        String createTableSQL = "CREATE TABLE product (Id INT PRIMARY KEY, ProductName VARCHAR(20), Price INT)";


        Connection connection = getDbConnection();
        Statement statement = connection.createStatement();
        statement.execute(createTableSQL);
        connection.close();
    }
}
