import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryUI
{
    public static void main(String[] args) {
        
        // Create an instance of the library system manager
        LibrarySystemManager libMS = new LibrarySystemManager ();

        Scanner scanner = new Scanner(System.in);
        System.out.print(">");
        while (scanner.hasNextLine())
        {
            String action = scanner.nextLine();

            try
            {
                if (action == null || action.equals("")) 
                {
                  // Skip empty input
                  System.out.print("\n>");
                  continue;
                }  

                // Exit program
                else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
                    return;
                
                else if (action.equalsIgnoreCase("USERS"))
                {
                    // List all users in the system
                    libMS.listAllUsers();
                }
                else if (action.equalsIgnoreCase("BOOKS"))
                {
                    // List all books in the system
                    libMS.listAllBooks();
                }
                else if (action.equalsIgnoreCase("CHECKOUT"))
                {
                    // Checkout a book by user ID and ISBN
                    int accountId = 0;
                    System.out.print("User Account Id: ");
                    if (scanner.hasNextInt()) 
                    {
                        accountId = scanner.nextInt();
                    }
                    scanner.nextLine();
                    String isbnNumber = "";
                    System.out.print("ISBN Number: ");
                    if (scanner.hasNextLine())
                    {
                        isbnNumber = scanner.nextLine();
                    }
                    libMS.checkoutBook(accountId, isbnNumber);
                }
                else if (action.equalsIgnoreCase("SEARCH"))
                {
                    // Search for a book by title
                    String bookTitle = "";
                    System.out.print("Title of Book: ");
                    if (scanner.hasNextLine())
                    {
                        bookTitle = scanner.nextLine();
                    }
                    libMS.searchBook(bookTitle);

                }
                else if (action.equalsIgnoreCase("RETURN"))
                {
                    // Return a book by user ID and ISBN
                    int accountId = 0;
                    System.out.print("User Account Id: ");
                    if (scanner.hasNextInt())
                    {
                        accountId = scanner.nextInt();
                    }
                    scanner.nextLine();
                    String isbnNumber = "";
                    System.out.print("ISBN Number: ");
                    if (scanner.hasNextLine())
                    {
                        isbnNumber = scanner.nextLine();
                    }
                    libMS.returnBook(accountId, isbnNumber);

                }
                else if (action.equalsIgnoreCase("LOADUSERS"))
                {
                    // Load users from a file
                    String filename = "";
                    System.out.print("User File: ");
                    if (scanner.hasNextLine())
                    {
                        filename = scanner.nextLine();
                    }
                    try 
                    {
                        ArrayList<User> usersList = LibraryRegistered.loadPreregisteredUsers(filename);
                        libMS.setUsers(usersList);
                        System.out.println("Users Loaded");
                    } 
                    catch (FileNotFoundException e) 
                    {
                        System.out.println("Users File: " + filename + " Not Found");
                    }    
                }
                else if (action.equalsIgnoreCase("LOADBOOKS"))
                {
                    // Load books from a file
                    String filename = "";
                    System.out.print("Book File: ");
                    if (scanner.hasNextLine())
                    {
                        filename = scanner.nextLine();
                    }
                    try 
                    {
                        ArrayList<Book> bookList = LibraryRegistered.loadPreregisteredBooks(filename);
                        libMS.setBooks(bookList);
                        System.out.println("Books Loaded");
                    } 
                    catch (FileNotFoundException e) 
                    {
                        System.out.println("Books File: " + filename + " Not Found");
                    }   
                }
                else if (action.equalsIgnoreCase("REGUSER"))
                {
                    // Register a new user
                    String name = "";
                    System.out.print("Name: ");
                    if (scanner.hasNextLine())
                    {
                        name = scanner.nextLine();
                    }
                    String DOB = "";
                    System.out.print("Date of Birth (MM/DD/YYYY): ");
                    if (scanner.hasNextLine())
                    {
                        DOB = scanner.nextLine();
                    }
                    String phoneNumber = "";
                    System.out.print("Phone Number: ");
                    if (scanner.hasNextLine())
                    {
                        phoneNumber = scanner.nextLine();
                    }
                    libMS.registerNewUser(name,DOB,phoneNumber);
                    System.out.println("User successfully registered!"); 
                }
                else if (action.equalsIgnoreCase("REGBOOK"))
                {
                    // Register a new book
                    String title = "";
                    System.out.print("Title: ");
                    if (scanner.hasNextLine())
                    {
                        title = scanner.nextLine();
                    }
                    String author = "";
                    System.out.print("Author: ");
                    if (scanner.hasNextLine())
                    {
                        author = scanner.nextLine();
                    }
                    String isbnNumber = "";
                    System.out.print("ISBN Number: ");
                    if (scanner.hasNextLine())
                    {
                        isbnNumber = scanner.nextLine();
                    }
                    libMS.registerNewBook(title, author, isbnNumber);
                    System.out.println("Book successfully registered!");

                }

                else if (action.equalsIgnoreCase("SUMMARY"))
                {
                    // Show summary of user's activity
                    int accountId = 0;
                    System.out.print("User Account Id: ");
                    if (scanner.hasNextInt())
                    {
                        accountId = scanner.nextInt();
                    }
                    scanner.nextLine();
                    libMS.listSummary(accountId);
                }

                else if (action.equalsIgnoreCase("SORTBYTITLE"))
                {
                    // Sort books by title
                    libMS.sortByTitle(); 
                }

                else 
                {
                    // Handle invalid input
                    System.out.println("Invalid response. Please enter a valid command.");
                }
            }
            catch (RuntimeException e)
            {
              // Handle runtime exceptions
              System.out.println(e.getMessage());
            }
            System.out.print("\n>");
        }
        // Close the scanner
        scanner.close();
    }
}