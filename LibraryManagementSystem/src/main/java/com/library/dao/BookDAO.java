package com.library.dao;

import com.library.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {

    public void viewBooks() {

        String sql = "SELECT * FROM books";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                System.out.println(
                    "Book ID: " + resultSet.getInt("book_id")
                    + " | Title: " + resultSet.getString("title")
                    + " | Author: " + resultSet.getString("author")
                    + " | Category: " + resultSet.getString("category")
                    + " | Available: " + resultSet.getBoolean("available")
                );
            }

        } catch (Exception e) {
            System.out.println("Error retrieving books.");
            e.printStackTrace();
        }
    }
    
    public void addBook(String title, String author, String category, boolean available) {

        String sql = "INSERT INTO books (title, author, category, available) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, category);
            stmt.setBoolean(4, available);

            stmt.executeUpdate();

            System.out.println("Book added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateBook(int bookId, String title, String author, String category, boolean available) {

        String sql = "UPDATE books SET title = ?, author = ?, category = ?, available = ? WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, category);
            stmt.setBoolean(4, available);
            stmt.setInt(5, bookId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteBook(int bookId) {

        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void findBook(int bookId) {

        String sql = "SELECT * FROM books WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                    "Book ID: " + resultSet.getInt("book_id")
                    + " | Title: " + resultSet.getString("title")
                    + " | Author: " + resultSet.getString("author")
                    + " | Category: " + resultSet.getString("category")
                    + " | Available: " + resultSet.getBoolean("available")
                );

            } else {
                System.out.println("Book not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}