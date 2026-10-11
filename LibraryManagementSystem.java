
import java.io.*;
import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean issued;

    Book(int id, String title, String author, boolean issued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = issued;
    }

    void display() {
        System.out.println("Book ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " + (issued ? "Issued" : "Available"));
        System.out.println("------------------------");
    }
}

public class LibraryManagementSystem {

    static final String FILE_NAME = "books.txt";
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Read book records from file
    static void loadBooks() {
        books.clear();

        try (BufferedReader br = new BufferedReader(
                new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|", -1);

                if (data.length == 4) {
                    books.add(new Book(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        Boolean.parseBoolean(data[3])
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            // File will be created when records are saved.
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Save book records to file
    static void saveBooks() {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE_NAME))) {

            for (Book b : books) {
                bw.write(b.id + "|" + b.title + "|" +
                         b.author + "|" + b.issued);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving records.");
        }
    }

    // Find a book using its ID
    static Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id) {
                return b;
            }
        }
        return null;
    }

    // Add a new book
    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (findBook(id) != null) {
            System.out.println("Book ID already exists.");
            return;
        }

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        if (title.contains("|") || author.contains("|")) {
            System.out.println("Title and author cannot contain |.");
            return;
        }

        books.add(new Book(id, title, author, false));
        saveBooks();

        System.out.println("Book added successfully!");
    }

    // View all books
    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book b : books) {
            b.display();
        }
    }

    // Search for a book
    static void searchBook() {
        System.out.print("Enter Book ID to search: ");
        int id = Integer.parseInt(sc.nextLine());

        Book b = findBook(id);

        if (b != null) {
            b.display();
        } else {
            System.out.println("Book not found.");
        }
    }

    // Issue a book
    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = Integer.parseInt(sc.nextLine());

        Book b = findBook(id);

        if (b == null) {
            System.out.println("Book not found.");
        } else if (b.issued) {
            System.out.println("Book is already issued.");
        } else {
            b.issued = true;
            saveBooks();
            System.out.println("Book issued successfully!");
        }
    }

    // Return a book
    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = Integer.parseInt(sc.nextLine());

        Book b = findBook(id);

        if (b == null) {
            System.out.println("Book not found.");
        } else if (!b.issued) {
            System.out.println("Book is not issued.");
        } else {
            b.issued = false;
            saveBooks();
            System.out.println("Book returned successfully!");
        }
    }

    public static void main(String[] args) {
        loadBooks();

        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        viewBooks();
                        break;
                    case 3:
                        searchBook();
                        break;
                    case 4:
                        issueBook();
                        break;
                    case 5:
                        returnBook();
                        break;
                    case 6:
                        saveBooks();
                        System.out.println("Thank you!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
