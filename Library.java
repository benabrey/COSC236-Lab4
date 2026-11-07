package lab4.library;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {

	//  GRASP Principles: Creator, Controller
	//	Manage the catalog of books and members.
	//	Track which books are borrowed and available


	private final List<Book> catalog = new ArrayList<>();
	private final List<Member> members = new ArrayList<>();
    private int nextMemberId = 1;

	// TODO: implement functionality of Member class

     // Creates a new Book and adds it to the catalog.
    public Book addBook(String title, String author) {
        if (findBookByTitle(title) != null) {
            return null;
        }
        Book book = new Book(title, author);
        catalog.add(book);
        return book;
    }

    // Removes a book from the catalog. Borrowed books cannot be removed.
    public boolean removeBook(Book book) {
        if (book == null || !book.isAvailable()) {
            return false;
        }
        return catalog.remove(book);
    }

    public Book findBookByTitle(String title) {
        for (Book book : catalog) {
            if (book.hasTitle(title)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getCatalog() {
        return Collections.unmodifiableList(catalog);
    }

    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : catalog) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : catalog) {
            if (!book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    // ---------- Members ----------

    //Creates a new Member with a unique id and registers it.
    public Member registerMember(String name) {
        if (findMemberByName(name) != null) {
            return null;
        }
        Member member = new Member(nextMemberId++, name);
        members.add(member);
        return member;
    }

    //Removes a member. Members who still have books out cannot be removed.
    public boolean removeMember(Member member) {
        if (member == null || member.hasBorrowedBooks()) {
            return false;
        }
        return members.remove(member);
    }

    public Member findMemberByName(String name) {
        for (Member member : members) {
            if (member.hasName(name)) {
                return member;
            }
        }
        return null;
    }

    public List<Member> getMembers() {
        return Collections.unmodifiableList(members);
    }

}
