package com.library;

import com.library.dao.BookDAO;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BookDAO bookDAO = new BookDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    System.out.print("Is the book available? (true/false): ");
                    boolean available = sc.nextBoolean();

                    bookDAO.addBook(title, author, category, available);
                    break;

                case 2:
                    bookDAO.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    int searchId = sc.nextInt();

                    bookDAO.findBook(searchId);
                    break;

                case 4:
                    System.out.print("Enter Book ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("Enter new author: ");
                    String newAuthor = sc.nextLine();

                    System.out.print("Enter new category: ");
                    String newCategory = sc.nextLine();

                    System.out.print("Is the book available? (true/false): ");
                    boolean newAvailable = sc.nextBoolean();

                    bookDAO.updateBook(
                        updateId,
                        newTitle,
                        newAuthor,
                        newCategory,
                        newAvailable
                    );
                    break;

                case 5:
                    System.out.print("Enter Book ID to delete: ");
                    int deleteId = sc.nextInt();

                    bookDAO.deleteBook(deleteId);
                    break;

                case 6:
                    System.out.println("Thank you for using the Library Management System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}