public class Book {
    private String title;
    private String author;
    private String isbnNumber;
    private int userID;

    public static enum Status {AVAILABLE, UNAVAILABLE};
    private Status status;

    public Book(String title, String author, String isbnNumber)
    {
        this.title = title;
        this.author = author;
        this.isbnNumber = isbnNumber;
        this.status = Status.AVAILABLE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public void printInfo()
    {
        System.out.println("Title: " + title + " Author: " + author + " ISBN Number: " + isbnNumber + " Status: " + status);
    }

    public boolean equals(Object other)
    {

        Book otherBook = (Book) other;

        return otherBook.getIsbnNumber().equals(isbnNumber);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public int getUserID()
    {
        return userID;
    }

        
}
