import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LibrarySystemManager {
    private Map<Integer, User> users; // Stores users with their ID
    private ArrayList<Book> books; // List of books in the library
    int userAccountId = 2000;  // Starting user account ID

    public LibrarySystemManager()
    {
        users = new LinkedHashMap<>(100, 0.75f);
        books = new ArrayList<Book>();
    }

    private boolean userExists(User user)
    {
      // Check if the user exists in the system
      for (Map.Entry<Integer, User> entry : users.entrySet())
      {
        User person = entry.getValue();
        if (person.equals(user))
        {
          return true;
        }
      }
      return false;
    }

    private boolean bookExists(Book book)
    {
        // Check if the book exists in the library
        for (int i = 0; i < books.size(); i++)
        {
            Book newBook = books.get(i);
            if (newBook.equals(book))
            {
                return true;
            }
        }
        return false;
    }

    public void setUsers(ArrayList<User> userList)
    {
        // Assign account ID to each user and add them to the system
        for (User user : userList) 
        {
            int id = 2000 + users.size();
            user.setAccountId(id);
            users.put(id, user);
        }
    }

    public void setBooks(ArrayList<Book> bookList)
    {
         // Add books to the library
        for (int i = 0; i < bookList.size(); i++)
        {    
            books.add(bookList.get(i));
        }
    }

   
    public void listAllUsers()
    {
        // List all registered users
        int count = 1;
        for (Map.Entry<Integer, User> entry : users.entrySet()) 
        {
            System.out.printf("%-2s. ", count);
            entry.getValue().printInfo(); 
            System.out.println();
            count++;
        }
    }

    public void listAllBooks()
    {
        // List all books in the library
        int count = 1;
        for (int i = 0; i < books.size(); i++)
        {
            System.out.printf("%-2s. ", count);
            books.get(i).printInfo();
            System.out.println();
            count++;
        }
    }

    public User getUser(int accountId)
    {
      // Retrieve user by account ID
      for (Map.Entry<Integer, User> entry : users.entrySet())
      {
        int id = entry.getKey();
        if (id == accountId)
        {
          return entry.getValue();
        }
      }

      return null;
    } 

    private static boolean isNumeric(String str) 
    {
        // Check if all characters in a string are numbers
        return str.chars().allMatch(Character::isDigit);
    }

    public void checkoutBook(int accountId, String isbnNumber) 
    {
        // Retrieve the user based on account ID
        User user = getUser(accountId);
        
        // Check if the user exists in the system
        if (user == null) 
        {
            throw new UserDoesNotExistException("User does not exist.");
        }
    
        // Validate the ISBN number: must be exactly 13 digits
        if (isbnNumber.length() != 13 || !isNumeric(isbnNumber)) 
        {
            throw new InvalidISBNNumberException("Invalid ISBN Number. ISBN Number must be 13 digits.");
        }
    
        boolean bookFound = false;
        
        // Iterate through the list of books to find the matching ISBN
        for (int i = 0; i < books.size(); i++) 
        {
            Book book = books.get(i);
            
            // Check if the current book matches the ISBN number
            if (book.getIsbnNumber().equals(isbnNumber)) 
            {
                bookFound = true;
    
                // Check if the book is available for checkout
                if (book.getStatus().equals(Book.Status.AVAILABLE)) 
                {
                    // Update the book status to UNAVAILABLE
                    book.setStatus(Book.Status.UNAVAILABLE);
                    // Add the book to the user's current checked-out books
                    user.addCurrentBook(book);
                    
                    // Remove the book from the user's returned books list if present
                    user.removeReturnedBook(book);
                    
                    // Set the book's user ID to the current user's account ID
                    book.setUserID(accountId);
                    
                    System.out.println("Book successfully checked out.");
                } 
                // Check if the book is already checked out by the user
                else if (book.getStatus().equals(Book.Status.UNAVAILABLE) && book.getUserID() == accountId) 
                {
                    throw new UnavailableBookException("User has already checked out this book.");
                } 
                // Handle the case where the book is unavailable for other reasons
                else 
                {
                    throw new UnavailableBookException("Book is not available for checkout.");
                }
            }
        }
        
        // If no matching book was found in the list, throw an exception
        if (!bookFound) 
        {
            throw new BookDoesNotExistException("Book does not exist in the library.");
        }
    } 
    
    public void returnBook(int accountId, String isbnNumber) 
    {
        // Retrieve the user based on account ID
        User user = getUser(accountId);
        
        // Check if the user exists in the system
        if (user == null) 
        {
            throw new UserDoesNotExistException("User does not exist.");
        }
    
        // Validate the ISBN number: must be exactly 13 digits
        if (isbnNumber.length() != 13 || !isNumeric(isbnNumber)) 
        {
            throw new InvalidISBNNumberException("Invalid ISBN Number. ISBN Number must be 13 digits.");
        }
    
        boolean bookFound = false;
        
        // Iterate through the list of books to find the matching ISBN
        for (int i = 0; i < books.size(); i++) 
        {
            Book book = books.get(i);
            
            // Check if the current book matches the ISBN number
            if (book.getIsbnNumber().equals(isbnNumber)) 
            {
                bookFound = true;
    
                // Check if the book is currently checked out by the user
                if (book.getStatus().equals(Book.Status.UNAVAILABLE) && book.getUserID() == accountId) 
                {
                    // Successfully return the book
                    System.out.println("Book successfully returned.");
                    // Add the book to the user's returned books list
                    user.addReturnedBook(book);
                    // Remove the book from the user's current checked-out books
                    user.removeCurrentBook(book);
                    // Update the book status to AVAILABLE
                    book.setStatus(Book.Status.AVAILABLE);
                    
                    // Reset the book's user ID 
                    book.setUserID(-1); 
                } 
                // Handle the case where the book is available (not checked out)
                else if (book.getStatus().equals(Book.Status.AVAILABLE)) 
                {
                    throw new UnavailableBookException("Book is not checked out.");
                } 
                // Handle the case where the book is checked out by a different user
                else 
                {
                    throw new WrongUserException("User did not check out this book.");
                }
            }
        }
        
        // If no matching book was found in the list, throw an exception
        if (!bookFound) 
        {
            throw new BookDoesNotExistException("Book does not exist in the library.");
        }
    }    
    

    public void searchBook(String title)
    {
        int count = 1;
        boolean bookFound = false;
        
        // Iterate through the list of books to find those with the matching title
        for (Book book : books)
        {
            if (book.getTitle().equals(title))
            {
                bookFound = true;
                // Print book details with a count prefix
                System.out.printf("%-2d. ", count);
                book.printInfo();
                System.out.println();
                count++;
            }
        }
        
        // If no books with the matching title were found, throw an exception
        if (!bookFound)
        {
            throw new BookDoesNotExistException("Book does not exist in library.");
        }
    }
    
    public static boolean isValidDateOfBirth(String dateOfBirth) 
    {
        if (dateOfBirth == null || dateOfBirth.length() != 10) 
        {
            return false;
        }
    
        // Check format MM/DD/YYYY
        return dateOfBirth.charAt(2) == '/' &&
               dateOfBirth.charAt(5) == '/' &&
               isNumeric(dateOfBirth.substring(0, 2)) &&
               isNumeric(dateOfBirth.substring(3, 5)) &&
               isNumeric(dateOfBirth.substring(6, 10));
    }
    
    public static boolean isValidPhoneNumber(String phoneNumber) 
    {
        if (phoneNumber == null || phoneNumber.length() != 12) 
        {
            return false; 
        }
    
        // Check format XXX-XXX-XXXX
        if (phoneNumber.charAt(3) != '-' || phoneNumber.charAt(7) != '-') 
        {
            return false; 
        }
    
        // Validate that all parts are numeric
        String part1 = phoneNumber.substring(0, 3);
        String part2 = phoneNumber.substring(4, 7);
        String part3 = phoneNumber.substring(8, 12);
    
        return isNumeric(part1) && isNumeric(part2) && isNumeric(part3);
    }
    
    public void registerNewUser(String name, String DOB, String phoneNumber)
    {
        // Validate input fields
        if (name != null && isValidDateOfBirth(DOB) && isValidPhoneNumber(phoneNumber))
        {
            int id = users.size() + 2000;
            User person = new User(id, name, DOB, phoneNumber);
    
            // Check if the user already exists
            if (userExists(person))
            {
                throw new UserExistsException("User already exists in the system.");
            }
    
            // Register the new user
            users.put(id, person);
        }
        else
        {
            // Validate and throw specific exceptions
            if (name == null)
            {
                throw new InvalidUserNameException("Invalid Name.");
            }
            if (!isValidDateOfBirth(DOB))
            {
                throw new InvalidDateofBirthException("Invalid Date of Birth.");
            }
            if (!isValidPhoneNumber(phoneNumber))
            {
                throw new InvalidPhoneNumberException("Invalid Phone Number");
            }
        }
    }
    
    public void registerNewBook(String title, String author, String isbnNumber) 
    {
        // Validate input fields
        if (title != null && author != null && isbnNumber.length() == 13 && isNumeric(isbnNumber)) 
        {
            Book newBook = new Book(title, author, isbnNumber);
            
            // Check if the book already exists
            if (bookExists(newBook))
            {
                throw new BookExistsException("A book with this ISBN Number already exists in the system.");
            }
    
            // Register the new book
            books.add(newBook);
        }
        else
        {
            // Validate and throw specific exceptions
            if (title == null) 
            {
                throw new InvalidTitleException("Invalid Title.");
            }
            if (author == null) 
            {
                throw new InvalidAuthorException("Invalid Author.");
            }
            if (isbnNumber.length() != 13 || !isNumeric(isbnNumber)) 
            {
                throw new InvalidISBNNumberException("Invalid ISBN Number. ISBN Number must be 13 digits.");
            }
        }
    }
    
    public void listSummary(int accountId)
    {
        // Retrieve the user based on account ID
        User user = getUser(accountId);
        
        if (user != null)
        {
            // List current checked-out books
            System.out.println("Books checked out: ");
            for (Book book : user.getCurrentBooks())
            {
                book.printInfoShort();
            }
    
            // List returned books
            System.out.println("\nReturned books: ");
            for (Book book : user.getReturnedBooks())
            {
                book.printInfoShort();
            }
        }
        else
        {
            throw new UserDoesNotExistException("User does not exist.");
        }
    }
    
    public void sortByTitle()
    {
        // Sort the list of books by title using the TitleComparator
        Collections.sort(books, new TitleComparator());
        // List all books after sorting
        listAllBooks();
    }
    
    // Comparator class for sorting books by title
    private class TitleComparator implements Comparator<Book>
    {
        public int compare(Book a, Book b)
        {
            return a.getTitle().compareTo(b.getTitle());
        }
    }    
}

// Exception thrown when a book is unavailable for checkout.
class UnavailableBookException extends RuntimeException
{
    public UnavailableBookException(String message)
    {
        super(message);
    }
}

// Exception thrown when a requested book does not exist in the library.
class BookDoesNotExistException extends RuntimeException
{
    public BookDoesNotExistException(String message)
    {
        super(message);
    }
}

// Exception thrown when a user does not exist in the system.
class UserDoesNotExistException extends RuntimeException
{
    public UserDoesNotExistException(String message)
    {
        super(message);
    }
}

// Exception thrown when an invalid ISBN number is provided.
class InvalidISBNNumberException extends RuntimeException
{
    public InvalidISBNNumberException(String message)
    {
        super(message);
    }
}

// Exception thrown when an invalid user name is provided.
class InvalidUserNameException extends RuntimeException
{
    public InvalidUserNameException(String message)
    {
        super(message);
    }
}

// Exception thrown when an invalid date of birth is provided.
class InvalidDateofBirthException extends RuntimeException
{
    public InvalidDateofBirthException(String message)
    {
        super(message);
    }
}

// Exception thrown when an invalid phone number is provided.
class InvalidPhoneNumberException extends RuntimeException
{
    public InvalidPhoneNumberException(String message)
    {
        super(message);
    }
}

// Exception thrown when an invalid book title is provided.
class InvalidTitleException extends RuntimeException
{
    public InvalidTitleException(String message)
    {
        super(message);
    }
}

// Exception thrown when an invalid author name is provided.
class InvalidAuthorException extends RuntimeException
{
    public InvalidAuthorException(String message)
    {
        super(message);
    }
}

// Exception thrown when an operation is attempted with a book that the user did not check out.
class WrongUserException extends RuntimeException
{
    public WrongUserException(String message)
    {
        super(message);
    }
}

// Exception thrown when attempting to register a user who already exists in the system.
class UserExistsException extends RuntimeException
{
    public UserExistsException(String message)
    {
        super(message);
    }
}

// Exception thrown when attempting to register a book that already exists in the system.
class BookExistsException extends RuntimeException
{
    public BookExistsException(String message)
    {
        super(message);
    }
}
