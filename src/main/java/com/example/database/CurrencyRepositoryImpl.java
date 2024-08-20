package com.example.database;

import com.example.model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CurrencyRepositoryImpl implements CurrencyRepository {
    @Override
    public Currency addCurrency(String name, String code, String sign){
        String sql = "insert into currencies(name,code,sign) values(?,?,?)";
        try (Connection conn = DBCreate.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setString(3, sign);
            pstmt.executeUpdate();
            System.out.println("added");
        } catch (SQLException e){
            throw new RuntimeException("ошибка при добавлении");
        }
        return null;
    }
    @Override
    public Currency getCurrencyById(int id){
        String sql = "select * from currencies where id=?";
        Currency currency = null;
        try (Connection conn = DBCreate.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                currency = new Currency(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Code"),
                        rs.getString("Sign")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("при получении валюты");
        }

        return currency;
    }
    @Override
    public List<Currency> getAllCurrencies() {
        String sql = "SELECT * FROM Currencies";
        List<Currency> currencies = new ArrayList<>();

        try (Connection conn = DBCreate.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                currencies.add(new Currency(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Code"),
                        rs.getString("Sign")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return currencies;
    }

    public static void clearAllCurrencies() {
        String sql = "DELETE FROM currencies";

        try (Connection conn = DBCreate.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("All records have been deleted.");
        } catch (SQLException e) {
            throw new RuntimeException("ошибка при удалении");
        }
    }
    @Override
    public Currency getCurrencyByCode(String code){
        String sql = "select * from currencies where code=?";
        Currency currency = null;
        try (Connection conn = DBCreate.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                currency = new Currency(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Code"),
                        rs.getString("Sign")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("при получении валюты");
        }

        return currency;
    }
}
