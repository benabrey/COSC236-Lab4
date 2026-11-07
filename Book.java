package lab4.library;

public class Book {

	// GRASP Principle: Information Expert
	// Manages its own state (available or borrowed).
	private String title;
    private final String author;
	private boolean isAvailable;

	// TODO: implement Book functionality


	public Book(String title, String author){
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        if(author == null || author.isBlank()){
            throw new IllegalArgumentException("Book author cannot be empty");
        }
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    //Marks book as borrowed
    public boolean checkOut() {
        if (!isAvailable) {
            return false;
        }
        isAvailable = false;
        return true;
    }

    //Marks book as returned
    public boolean checkIn() {
        if (isAvailable) {
            return false;
        }
        isAvailable = true;
        return true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    //Case-insensitive title match, so lookups don't depend on capitalisation.
    public boolean hasTitle(String otherTitle) {
        return otherTitle != null && title.equalsIgnoreCase(otherTitle.trim());
    }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + author + (isAvailable ? " [available]" : " [borrowed]");
    }
}

//Extra comment for the commit
