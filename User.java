import java.util.ArrayList;

public class User 
{
    private int accountId;  // Unique account ID for the user
    private String name;  // User's name
    private String DOB;  // User's date of birth
    private String phoneNumber;  // User's phone number
    private ArrayList<Book> currentBooks;  // List of currently borrowed books
    private ArrayList<Book> returnedBooks;  // List of previously returned books

    // Constructor to initialize User object
    public User(int id, String name, String DOB, String phoneNumber)
    {
        this.accountId = id;
        this.name = name;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;

        // Initialize the ArrayLists for current and returned books
        this.currentBooks = new ArrayList<>();
        this.returnedBooks = new ArrayList<>();
    }

    // Getter for user account ID
    public int getAccountId()
    {
        return this.accountId;
    }

    // Setter for user account ID
    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    // Getter for user's name
    public String getName()
    {
        return name;
    }

    // Setter for user's name
    public void setName(String name)
    {
        this.name = name;
    }

    // Getter for user's date of birth
    public String getDateofBirth()
    {
        return this.DOB;
    }

    // Setter for user's date of birth
    public void setDateofBirth(String DOB)
    {
        this.DOB = DOB;
    }

    // Getter for user's phone number
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    // Setter for user's phone number
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    // Add a book to the list of currently borrowed books
    public void addCurrentBook(Book book)
    {
        currentBooks.add(book);
    }

    // Add a book to the list of returned books
    public void addReturnedBook(Book book)
    {
        returnedBooks.add(book);
    }

    // Remove a book from the list of currently borrowed books
    public void removeCurrentBook(Book book)
    {
        // Find the book in the list and remove it
        for (int i = 0; i < currentBooks.size(); i++)
        {
            if (currentBooks.get(i).equals(book))
            {
                currentBooks.remove(i);
                break; 
            }
        }
    }

    // Getter for the list of currently borrowed books
    public ArrayList<Book> getCurrentBooks()
    {
        return currentBooks;
    }

    // Getter for the list of returned books
    public ArrayList<Book> getReturnedBooks()
    {
        return returnedBooks;
    }

    // Remove a book from the list of returned books
    public void removeReturnedBook(Book book)
    {
        // Find the book in the list and remove it
        for (int i = 0; i < returnedBooks.size(); i++)
        {
            if (returnedBooks.get(i).equals(book))
            {
                returnedBooks.remove(i);
                break; 
            }
        }
    }

    // Print user's information
    public void printInfo()
    {
        System.out.println("Id: " + accountId + " Name: " + name + " Date of Birth: " + DOB + " Phone Number: " + phoneNumber);
    }

    // Check if two users are equal (based on name and phone number)
    public boolean equals(Object other)
    {
        // Cast the other object to a User
        User otherUser = (User) other;

        // Compare name and phone number to determine equality
        if (otherUser.getName().equals(name) && otherUser.getPhoneNumber().equals(phoneNumber))
        {
            return true;
        }
        return false;
    }
}