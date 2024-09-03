
import java.util.ArrayList;

public class User 
{
    private int accountId;  
    private String name;
    private String DOB;
    private String phoneNumber; 
    private ArrayList<Book> currentBooks;
    private ArrayList<Book> returnedBooks;

    public User(int id, String name, String DOB, String phoneNumber)
    {
        this.accountId = id;
        this.name = name;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;

    }

    public int getAccountId()
    {
      return this.accountId;
    }
    public void setAccountId(int accountId)
    {
      this.accountId = accountId;
    }
    public String getName()
    {
      return name;
    }
    public void setName(String name)
    {
      this.name = name;
    }
    public String getDateofBirth()
    {
        return this.DOB;
    }
    public void setDateofBirth(String DOB)
    {
        this.DOB = DOB;
    }
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void addCurrentBook(Book book)
    {
      currentBooks.add(book);
    }

    public void addReturnedBook(Book book)
    {
      returnedBooks.add(book);
    }

    public void removeCurrentBook(Book book)
    {
      // get index then remove by index
      for (int i = 0; i < currentBooks.size(); i++)
      {
        if (currentBooks.get(i).equals(book))
        {
          currentBooks.remove(i);
          break; 
        }
      }
    }

    public ArrayList<Book> getCurrentBooks()
    {
      return currentBooks;
    }

    public ArrayList<Book> getReturnedBooks()
    {
      return returnedBooks;
    }

    public void removeReturnedBook(Book book)
    {
      // get index then remove by index
      for (int i = 0; i < returnedBooks.size(); i++)
      {
        if (returnedBooks.get(i).equals(book))
        {
          returnedBooks.remove(i);
          break; 
        }
      }
    }

    // Print Information about a User  
    public void printInfo()
    {
        System.out.println("Id: " + accountId + " Name: " + name + " Date of Birth: " + DOB + " Phone Number: " + phoneNumber);
    }

    // Check if two users are equal (Two users are equal if they have the same name and phone number)
    public boolean equals(Object other)
    {
        // Cast other to User
        User otherUser = (User) other;

        // Check if same and address are equal, if they are return true, else return false 
        if (otherUser.getName().equals(name) && otherUser.getPhoneNumber().equals(phoneNumber))
        {
        return true;
        }
        return false;
    }

}

