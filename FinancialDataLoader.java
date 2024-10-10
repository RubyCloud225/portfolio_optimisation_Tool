import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.util.ArrayList;

public class FinancialDataLoader {

    public Connection getConnection() {
        String url = "jsbc:sqlserver://localhost:1433;databaseName=mydatabase";
        String username = "myusername";
        String password = "mypassword";

        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBC driver " + e.getMessage());
        }

        return conn;

    }

    public void createTables() {
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Stocks (Symbol VARCHAR(255), Name VARCHAR(255), Price DOUBLE, Volume INTEGER)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Bonds (Symbol VARCHAR(255), Name VARCHAR(255), Price DOUBLE, Yield DOUBLE, Maturity DATE)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS FX (Currency VARCHAR(255), Rate DOUBLE, Date DATE)");
        } catch (SQLException e) {
                System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public void importStockData(String symbol) {
        try {
            List<Stock> stockData = YahooFinancialApi.getStockData(symbol);
            importFinancialData(stockData, new ArrayList<>(), new ArrayList<>());
        } catch (IOException e) {
            System.out.println("Error calling Yahoo Finance API: " + e.gotMessage());
        }
    }

    public void importBondData(String symbol) {
        try {
            List<Bond> bondData = YahooFinancialApi.getBondData(symbol);
            importFinancialData(bondData, new ArrayList<>(), new ArrayList<>());
        } catch (IOException e) {
            System.out.println("Error calling Yahoo Finance API: " + e.getMessage());
        }
    }

    public void importfxData(String symbol) {
        try {
            List<FX> fxData = YahooFinancialApi.getfxData(symbol);
            importFinancialData(fxData, new ArrayList<>(), new ArrayList<>());
        } catch (IOException e) {
            System.out.println("Error calling Yahoo Finance API: " + e.getMessage());
        }
    }

    public void importFinancialData(List<Stock> stocks, List<Bond> bonds, List<FX> fxRates) {
        // Import Stocks Data
        for (Stock stock : stocks) {
            String stockSql = "INSERT INTO Stocks (Symbol, Name, Price, Volume) VALUES ( ?, ?, ?, ?)";
            try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, stock.getSymbol());
                    pstmt.setString(2, stock.getName());
                    pstmt.setDouble(3, stock.getPrice());
                    pstmt.setInt(4, stock.getVolume());
                    pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error importing stock data" + e.getMessage());
            }
        }

        // Import bonds data
        for (Bond bond : bonds) {
            String bondSql = "INSERT INTO Bonds (Symbol, Name, Price, Yield) VALUES ( ?, ?, ?, ?, ?)";
            try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, bond.getSymbol());
                    pstmt.setString(2, bond.getName());
                    pstmt.setDouble(3, bond.getPrice());
                    pstmt.setDouble(4, bond.getYield());
                    pstmt.setDate(5, new java.sql.Date(bond.getMaturity().getTime()));
                    pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error importing bond data: " + e.getMessage());
            }
        }
        // Import FX Data
        for (FX fxRate : fxRates) {
            String fxSql = "INSERT INTO FX (Currency, Rate, Date) VALUES ( ?, ?, ?)";
            try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, fxRate.getCurrency());
                    pstmt.setDouble(2, fxRate.getRate());
                    pstmt.setDate(3, new java.sql.Date(fxRate.getDate().getTime()));
                    pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error importing FX data: " + e.getMessage());
            }
        }
    }

    // Retrieve Stock Data
    public List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();
        String query = "SELECT * FROM Stocks";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Stock stock = new Stock(rs.getString("Symbol"), rs.getString("Name"), rs.getDouble("Price"), rs.getInt("Volume"));
                stocks.add(stock);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving stock data: " + e.getMessage());
        }
        return stocks;
    }
    
    public List<Bond> getBonds() {
        List<Bond> bonds = new ArrayList<>();
        String query = "SELECT * FROM Bonds";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Bond bond = new Bond(rs.getString("Symbol"), rs.getString("Name"), rs.getDouble("Price"), rs.getDouble("Yield"), rs.getDate("Maturity"));
                bonds.add(bond);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving bond data: " + e.getMessage());
        }
        return bonds;
    }
    
    public List<FX> getFXRates() {
        List<FX> fxRates = new ArrayList<>();
        String query = "SELECT * FROM FX";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                FX fxRate = new FX(rs.getString("Currency"), rs.getDouble("Rate"), rs.getDate("Date"));
                fxRates.add(fxRate);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving FX data: " + e.getMessage());
        }
        return fxRates;
    }
}


///FinancialDataLoader loader = new FinancialDataLoader();
///List<Stock> stocks = loader.getStocks();
///List<Bond> bonds = loader.getBonds();
///List<FX> fxRates = loader.getFXRates();
