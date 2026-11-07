package lab4.library;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {

	// GRASP Principle: Information Expert and Low Coupling
	// Can borrow and return books.

    private final int memberId;
	private final String name;
    private final List<Book> borrowedBooks;

	// TODO: implement functionality of Member class
    public Member(int memberId, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Member name cannot be empty.");
        }
        this.memberId = memberId;
        this.name = name.trim();
        this.borrowedBooks = new ArrayList<>();
    }

    //Borrows a book. The Book decides whether it is available.

    public boolean borrowBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        if (!book.checkOut()) {
            return false;
        }
        borrowedBooks.add(book);
        return true;
    }

    //Returns a book this member previously borrowed.
    public boolean returnBook(Book book) {
        if (book == null || !borrowedBooks.remove(book)) {
            return false;
        }
        book.checkIn();
        return true;
    }

    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }

    public boolean hasBorrowedBooks() {
        return !borrowedBooks.isEmpty();
    }

    //Read-only view so outside classes can't modify the list directly.
    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public boolean hasName(String otherName) {
        return otherName != null && name.equalsIgnoreCase(otherName.trim());
    }

    @Override
    public String toString() {
        return "#" + memberId + " " + name + " (" + borrowedBooks.size() + " book(s) borrowed)";
    }

}
