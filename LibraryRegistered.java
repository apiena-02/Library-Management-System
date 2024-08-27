import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryRegistered 
{
    //private static int firstUserAccountID = 2000;

    //public static int generateUserAccountId(ArrayList<User> current)
    //{
        //return firstUserAccountID + current.size();
    //}

    public static ArrayList<User> loadPreregisteredUsers(String filename) throws FileNotFoundException
    {
        // Create users ArrayList
        ArrayList<User> users = new ArrayList<>();
        // Create file 
        File userFile = new File(filename);
        // Scan through the file using a while loop 
        Scanner scanner = new Scanner(userFile);
        while (scanner.hasNextLine())
        {
            // Get name
            String name = scanner.nextLine();
            // Get date of birth
            String DOB = scanner.nextLine();
            // Get money for phone number
            String phoneNumber = scanner.nextLine();
            // Generate user account id 
            int id = 2000 + users.size();
            // Create new User
            User user = new User(id, name, DOB, phoneNumber);
            // Add new user to the users ArrayList
            users.add(user);
        }
        // Close scanner when done
        scanner.close();
        // Return users
        return users;
    }

    public static ArrayList<Book> loadPreregisteredBooks(String filename) throws FileNotFoundException
    {
        // Create drivers ArrayList
        ArrayList<Book> books = new ArrayList<>();
        // Create file
        File booksFile = new File(filename);
        // Scan through the file using a while loop
        Scanner scanner = new Scanner(booksFile);
        while (scanner.hasNextLine())
        {
            // Get title
            String title = scanner.nextLine();
            // Get car author
            String author = scanner.nextLine();
            // Get ISBN Number
            String isbnNum = scanner.nextLine();

            // Create new Book
            Book driver = new Book(title, author, isbnNum);
            // Add driver to the drivers ArrayList 
            books.add(driver);
        }
        // Close scanner when done
        scanner.close();
        // Return drivers
        return books;
    }
}
