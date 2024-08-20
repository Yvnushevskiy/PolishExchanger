package com.example.database;

import com.example.dto.ExchangeRateDTO;
import com.example.model.ExchangeRate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

    @Override
    public void addExchangeRate(String baseCurrency, String targetCurrency, double exchangeRate) {
        String sql = "INSERT INTO exchange_rates (base_currency_code, target_currency_code, exchange_rate) VALUES (?, ?, ?)";
        try (Connection conn = DBCreate.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, baseCurrency);
            pstmt.setString(2, targetCurrency);
            pstmt.setDouble(3, exchangeRate);
            pstmt.executeUpdate();
            System.out.println("Exchange rate added.");
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding exchange rate.", e);
        }
    }

    @Override
    public ExchangeRateDTO getExchangeRate(String baseCurrencyCode, String targetCurrencyName) {
        return null;
    }
    @Override
    public double getExchangeRateByCode(String baseCurrencyCode, String targetCurrencyCode) {
        String sql = "SELECT exchange_rate FROM exchange_rates WHERE base_currency_code = ? AND target_currency_code = ?";
        double rate = 0.0;

        try (Connection conn = DBCreate.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, baseCurrencyCode);
            pstmt.setString(2, targetCurrencyCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rate = rs.getDouble("exchange_rate");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving exchange rate.", e);
        }

        return rate;
    }

    @Override
    public List<ExchangeRate> getAllExchangeRates() {
        String sql = "SELECT * FROM exchange_rates";
        List<ExchangeRate> exchangeRates = new ArrayList<>();

        try (Connection conn = DBCreate.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                exchangeRates.add(new ExchangeRate(
                        rs.getInt("ID"),
                        rs.getString("base_currency_code"),
                        rs.getString("target_currency_code"),
                        rs.getDouble("exchange_rate")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving list of exchange rates.", e);
        }

        return exchangeRates;
    }

    @Override
    public void changeExchangeRate(String baseCurrencyName, String targetCurrencyName, double exchangeRate) {
        String updateQuery = "UPDATE exchange_rates SET exchange_rate = ? WHERE base_currency = ? AND target_currency = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:your_database.db");
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setDouble(1, exchangeRate); // Устанавливаем новый курс
            pstmt.setString(2, baseCurrencyName); // Устанавливаем базовую валюту
            pstmt.setString(3, targetCurrencyName); // Устанавливаем целевую валюту

            int affectedRows = pstmt.executeUpdate(); // Выполняем обновление

            if (affectedRows > 0) {
                System.out.println("Exchange rate updated successfully.");
            } else {
                System.out.println("Exchange rate not found for the specified currencies.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void clearAllExchangeRates() {
        String sql = "DELETE FROM exchange_rates";

        try (Connection conn = DBCreate.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("All exchange rate records have been deleted.");
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting exchange rates.", e);
        }
    }

}
