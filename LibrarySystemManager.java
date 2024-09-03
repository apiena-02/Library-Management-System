import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LibrarySystemManager {
    private Map<Integer, User> users;
    private ArrayList<Book> books;
    int userAccountId = 2000;

    public LibrarySystemManager()
    {
        users = new LinkedHashMap<>();
        books = new ArrayList<Book>();
    }

    
    public void setUsers(ArrayList<User> userList)
    {
       
        for (User user : userList) 
        {
            int id = 2000 + users.size();
            user.setAccountId(id);
            users.put(id, user);
        }
    }

    public void setBooks(ArrayList<Book> bookList)
    {
       
        for (int i = 0; i < bookList.size(); i++)
        {    
            books.add(bookList.get(i));
        }
    }

   
    public void listAllUsers()
    {
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
      // Loop through map of users
      for (Map.Entry<Integer, User> entry : users.entrySet())
      {
        // Get the user account id
        int id = entry.getKey();
        // Check if the account id is equal 
        if (id == accountId)
        {
          // return user
          return entry.getValue();
        }
      }

      return null;
    } 

    private static boolean isNumeric(String str) 
    {
        return str.chars().allMatch(Character::isDigit);
    }

    public void checkoutBook(int accountId, String isbnNumber) 
    {
        User user = getUser(accountId);
    
        // Check if the user exists first
        if (user == null) 
        {
            throw new UserDoesNotExistException("User does not exist.");
        }
    
        // Proceed with ISBN validation if the user is valid
        if (isbnNumber.length() != 13 || !isNumeric(isbnNumber)) 
        {
            throw new InvalidISBNNumberException("Invalid ISBN Number. ISBN Number must be 13 digits.");
        }
    
        boolean bookFlag = false;
        for (int i = 0; i < books.size(); i++) 
        {
            if (books.get(i).getIsbnNumber().equals(isbnNumber)) 
            {
                bookFlag = true;
                if (books.get(i).getStatus().equals(Book.Status.AVAILABLE)) 
                {
                    System.out.println("Book successfully checked out.");
                    books.get(i).setStatus(Book.Status.UNAVAILABLE);
                    books.get(i).setUserID(accountId);
                }
                else if (books.get(i).getStatus().equals(Book.Status.UNAVAILABLE) && books.get(i).getUserID() == accountId) 
                {
                    throw new UnavailableBookException("User has already checked out this book.");
                }
                else 
                {
                    throw new UnavailableBookException("Book is not available for checkout.");
                }
            }
        }
    
        // If no matching book was found, throw an exception
        if (!bookFlag) 
        {
            throw new BookDoesNotExistException("Book does not exist in the library.");
        }
    }
    
    

    public void returnBook(int accountId, String isbnNumber) 
    {
        // Validate if the user exists first
        User user = getUser(accountId);
        if (user == null) 
        {
            throw new UserDoesNotExistException("User does not exist.");
        }
    
        // Validate the ISBN number
        if (isbnNumber.length() != 13 || !isNumeric(isbnNumber)) 
        {
            throw new InvalidISBNNumberException("Invalid ISBN Number. ISBN Number must be 13 digits.");
        }
    
        boolean bookFlag = false;
        for (int i = 0; i < books.size(); i++) 
        {
            if (books.get(i).getIsbnNumber().equals(isbnNumber)) 
            {
                bookFlag = true;
                if (books.get(i).getStatus().equals(Book.Status.UNAVAILABLE) && books.get(i).getUserID() == accountId) 
                {
                    System.out.println("Book successfully returned.");
                    books.get(i).setStatus(Book.Status.AVAILABLE);
                } 
                else if (books.get(i).getStatus().equals(Book.Status.AVAILABLE)) 
                {
                    throw new UnavailableBookException("Book is not checked out.");
                } 
                else 
                {
                    throw new WrongUserException("User did not check out this book.");
                }
            }
        }
    
        // If no matching book was found, throw an exception
        if (!bookFlag) 
        {
            throw new BookDoesNotExistException("Book does not exist in the library.");
        }
    }
    
    

    public void searchBook(String title)
    {
        int count = 1;
        boolean bookFlag = false;
        for (int i = 0; i < books.size(); i++)
        {
            if (books.get(i).getTitle().equals(title))
            {
                bookFlag = true;
                System.out.printf("%-2s. ", count);
                books.get(i).printInfo();
                System.out.println();
                count++;
            }
        }
        if (bookFlag == false)
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

        
        if (phoneNumber.charAt(3) != '-' || phoneNumber.charAt(7) != '-') 
        {
            return false; 
        }

        
        String part1 = phoneNumber.substring(0, 3);
        String part2 = phoneNumber.substring(4, 7);
        String part3 = phoneNumber.substring(8, 12);

        return isNumeric(part1) && isNumeric(part2) && isNumeric(part3);
    }

    public void registerNewUser(String name, String DOB, String phoneNumber)
    {
        if (name != null && isValidDateOfBirth(DOB) && isValidPhoneNumber(phoneNumber))
        {
            int id = users.size() + 2000;
            User person = new User(id, name, DOB, phoneNumber);
            
            users.put(id, person);

        }
        else if (name == null)
        {
            throw new InvalidUserNameException("Invalid Name.");
        }
        else if (!isValidDateOfBirth(DOB))
        {
            throw new InvalidDateofBirthException("Invalid Date of Birth.");
        }
        else if (!isValidPhoneNumber(phoneNumber))
        {
            throw new InvalidPhoneNumberException("Invalid Phone Number");
        }
    }

    public void registerNewBook(String title, String author, String isbnNumber) {
        if (title != null && author != null && isbnNumber.length() == 13 && isNumeric(isbnNumber)) {
            Book newBook = new Book(title, author, isbnNumber);
            books.add(newBook);
        } else if (title == null) {
            throw new InvalidTitleException("Invalid Title.");
        } else if (author == null) {
            throw new InvalidAuthorException("Invalid Author.");
        } else if (isbnNumber.length() != 13 || !isNumeric(isbnNumber)) {
            throw new InvalidISBNNumberException("Invalid ISBN Number. ISBN Number must be 13 digits." + isbnNumber.length());
        }
    }
    

    public void sortByTitle()
    {
        Collections.sort(books, new TitleComparator());
        listAllBooks();
    }

    private class TitleComparator implements Comparator<Book>
    {
        public int compare(Book a, Book b)
        {
            return a.getTitle().compareTo(b.getTitle());
        }
    }

}

class UnavailableBookException extends RuntimeException
{
    public UnavailableBookException(String message)
    {
        super(message);
    }
}
class BookDoesNotExistException extends RuntimeException
{
    public BookDoesNotExistException(String message)
    {
        super(message);
    }
}
class UserDoesNotExistException extends RuntimeException
{
    public UserDoesNotExistException(String message)
    {
        super(message);
    }
}
class InvalidISBNNumberException extends RuntimeException
{
    public InvalidISBNNumberException(String message)
    {
        super(message);
    }
}
class InvalidUserNameException extends RuntimeException
{
    public InvalidUserNameException(String message)
    {
        super(message);
    }
}
class InvalidDateofBirthException extends RuntimeException
{
    public InvalidDateofBirthException(String message)
    {
        super(message);
    }
}
class InvalidPhoneNumberException extends RuntimeException
{
    public InvalidPhoneNumberException(String message)
    {
        super(message);
    }
}
class InvalidTitleException extends RuntimeException
{
    public InvalidTitleException(String message)
    {
        super(message);
    }
}
class InvalidAuthorException extends RuntimeException
{
    public InvalidAuthorException(String message)
    {
        super(message);
    }
}
class WrongUserException extends RuntimeException
{
    public WrongUserException(String message)
    {
        super(message);
    }
}
