public class Book {
    private String title;  // Book title
    private String author;  // Book author
    private String isbnNumber;  // Book ISBN number
    private int userID;  // User ID for checked out user

    public static enum Status {AVAILABLE, UNAVAILABLE};  // Book status (available or not)
    private Status status;

    public Book(String title, String author, String isbnNumber)
    {
        this.title = title;
        this.author = author;
        this.isbnNumber = isbnNumber;
        this.status = Status.AVAILABLE;  // New books start as available
    }

    // Getter for book title
    public String getTitle() {
        return title;
    }

    // Setter for book title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for book author
    public String getAuthor() {
        return author;
    }

    // Setter for book author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter for ISBN number
    public String getIsbnNumber() {
        return isbnNumber;
    }

    // Setter for ISBN number
    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    // Print detailed book information
    public void printInfo()
    {
        System.out.println("Title: " + title + " Author: " + author + " ISBN Number: " + isbnNumber + " Status: " + status);
    }

    // Print short book information
    public void printInfoShort()
    {
        System.out.println("Title: " + title + " Author: " + author + " ISBN Number: " + isbnNumber);
    }

    // Compare books by ISBN number
    public boolean equals(Object other)
    {
        Book otherBook = (Book) other;
        return otherBook.getIsbnNumber().equals(isbnNumber);
    }

    // Getter for book status (available/unavailable)
    public Status getStatus() {
        return status;
    }

    // Setter for book status
    public void setStatus(Status status) {
        this.status = status;
    }

    // Set user ID for checked-out user
    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    // Get user ID for the user who checked out the book
    public int getUserID()
    {
        return userID;
    }
}