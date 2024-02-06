package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDB {
    public static void main(String[] args) throws SQLException {
        try {
            createDbProductTable();
        } catch (SQLException e) {
            throw new SQLException(e);
        }


    }

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String username = "postgres";
    private static String password = "root";

    private static Connection getDbConnection() throws SQLException {
        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dbConnection;
    }

    private static void createDbProductTable() throws SQLException {
        //String createTableProductSQL = "CREATE TABLE Product (Id INT PRIMARY KEY, ProductName Varchar(20), Price INT)";
        //String createTableOrderSQL = "CREATE TABLE ordersJava (Id INT PRIMARY KEY, ProductCount INT, Price INT)";
        //String selectProductsInSQL = "INSERT INTO ordersJava (Id, ProductCount, Price) VALUES ((SELECT (Id) FROM product WHERE ProductName = 'Audi'), 4, (SELECT (Price) FROM product WHERE ProductName = 'Audi'))";
        // String ProductsCountsSQL = "SELECT * FROM product JOIN ordersJava OJ on Product.Id = OJ.ID WHERE OJ.ProductCount = (SELECT SUM(ProductCount) FROM ordersJava)";
        //String ProductsAVGPriceSQL = "SELECT(AVG(Price))FROM Product";

        Connection connection = getDbConnection();
        Statement statement = connection.createStatement();
        //statement.execute(createTableProductSQL)
        //statement.execute(createTableOrderSQL);
        //statement.execute(selectProductsInSQL);
        //statement.execute(ProductsCountsSQL);
        //statement.execute((ProductsAVGPriceSQL));
        connection.close();
    }
}
