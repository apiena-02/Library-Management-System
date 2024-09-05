import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryRegistered 
{
    // Method to load pre-registered users from a file
    public static ArrayList<User> loadPreregisteredUsers(String filename) throws FileNotFoundException
    {
        // Create an ArrayList to store users
        ArrayList<User> users = new ArrayList<>();
        // Create a File object for the user data
        File userFile = new File(filename);
        // Create a Scanner to read through the file
        Scanner scanner = new Scanner(userFile);
        while (scanner.hasNextLine())
        {
            // Read the name of the user
            String name = scanner.nextLine();
            // Read the date of birth of the user
            String DOB = scanner.nextLine();
            // Read the phone number of the user
            String phoneNumber = scanner.nextLine();
            // Generate a user account ID (starts from 2000)
            int id = 2000 + users.size();
            // Create a new User object
            User user = new User(id, name, DOB, phoneNumber);
            // Add the user to the ArrayList of users
            users.add(user);
        }
        // Close the scanner after reading the file
        scanner.close();
        // Return the list of users
        return users;
    }

    // Method to load pre-registered books from a file
    public static ArrayList<Book> loadPreregisteredBooks(String filename) throws FileNotFoundException
    {
        // Create an ArrayList to store books
        ArrayList<Book> books = new ArrayList<>();
        // Create a File object for the book data
        File booksFile = new File(filename);
        // Create a Scanner to read through the file
        Scanner scanner = new Scanner(booksFile);
        while (scanner.hasNextLine())
        {
            // Read the title of the book
            String title = scanner.nextLine();
            // Read the author of the book
            String author = scanner.nextLine();
            // Read the ISBN number of the book
            String isbnNum = scanner.nextLine();
            // Create a new Book object
            Book book = new Book(title, author, isbnNum);
            // Add the book to the ArrayList of books
            books.add(book);
        }
        // Close the scanner after reading the file
        scanner.close();
        // Return the list of books
        return books;
    }
}