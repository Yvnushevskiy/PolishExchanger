package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreate {

    private static final String URL = "jdbc:sqlite:Currencies.db";

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createCurrenciesTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS currencies (
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                Name TEXT NOT NULL,
                Code TEXT NOT NULL UNIQUE,
                Sign TEXT NOT NULL
            );
        """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'currencies' has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createExchangeRatesTable() {
        String sql = """
        CREATE TABLE IF NOT EXISTS exchange_rates (
            ID INTEGER PRIMARY KEY AUTOINCREMENT,
            base_currency_code VARCHAR NOT NULL,
            target_currency_code VARCHAR NOT NULL,
            exchange_rate REAL NOT NULL,
            FOREIGN KEY (base_currency_code) REFERENCES currencies(Code),
            FOREIGN KEY (target_currency_code) REFERENCES currencies(Code)
        );
    """;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'exchange_rates' has been created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        // Код для выполнения SQL-запроса (например, через JDBC)



    public static void resetTable() {
        String dropSql = "DROP TABLE IF EXISTS currencies";
        String dropSql2 = "DROP TABLE IF EXISTS exchange_rates";

        try (Connection conn = DBCreate.connect();
             Statement stmt = conn.createStatement()) {
            // Удаление таблицы
            stmt.execute(dropSql);
            stmt.execute(dropSql2);
            System.out.println("Table 'currencies' has been dropped.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

