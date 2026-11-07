package lab4.library;

import java.util.List;

public class LibrarianController {

	// GRASP Principle: Controller
	// Handles user requests like borrowing and returning books.
	// Delegates tasks to the appropriate objects.

	// TODO: implement functionality of Member class

    private final Library library;

    public LibrarianController(Library library) {
        if (library == null) {
            throw new IllegalArgumentException("Library cannot be null.");
        }
        this.library = library;
    }

    // ---------- Catalog management ----------

    public void addBook(String title, String author) {
        try {
            Book book = library.addBook(title, author);
            if (book == null) {
                System.out.println("\"" + title + "\" is already in the catalog.");
            } else {
                System.out.println(book.getTitle() + " has been added to the catalog.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add book: " + e.getMessage());
        }
    }

    public void removeBook(String title) {
        Book book = library.findBookByTitle(title);
        if (book == null) {
            System.out.println("Book \"" + title + "\" not found.");
        } else if (!book.isAvailable()) {
            System.out.println("Cannot remove \"" + book.getTitle() + "\": it is currently borrowed.");
        } else if (library.removeBook(book)) {
            System.out.println("\"" + book.getTitle() + "\" has been removed from the catalog.");
        }
    }

    // ---------- Member management ----------

    public void registerMember(String name) {
        try {
            Member member = library.registerMember(name);
            if (member == null) {
                System.out.println("Member " + name + " already exists.");
            } else {
                System.out.println("Member " + member.getName()
                        + " has been added with ID #" + member.getMemberId() + ".");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Could not register member: " + e.getMessage());
        }
    }

    public void removeMember(String name) {
        Member member = library.findMemberByName(name);
        if (member == null) {
            System.out.println("Member " + name + " not found.");
        } else if (member.hasBorrowedBooks()) {
            System.out.println("Cannot remove " + member.getName()
                    + ": they still have " + member.getBorrowedBooks().size() + " book(s) borrowed.");
        } else if (library.removeMember(member)) {
            System.out.println("Member " + member.getName() + " has been removed.");
        }
    }

    // ---------- Borrow / return ----------

    public void borrowBook(String memberName, String title) {
        Member member = library.findMemberByName(memberName);
        if (member == null) {
            System.out.println("Member " + memberName + " not found.");
            return;
        }
        Book book = library.findBookByTitle(title);
        if (book == null) {
            System.out.println("Book \"" + title + "\" is not in the catalog.");
            return;
        }
        if (member.borrowBook(book)) {
            System.out.println(member.getName() + " has successfully borrowed " + book.getTitle() + ".");
        } else {
            rejectRequest(member, book);
        }
    }

    //Extension of "Borrow a book": the requested book is not available.
    private void rejectRequest(Member member, Book book) {
        System.out.println("Request rejected: " + book.getTitle()
                + " is currently borrowed, so " + member.getName() + " cannot borrow it.");
    }

    public void returnBook(String memberName, String title) {
        Member member = library.findMemberByName(memberName);
        if (member == null) {
            System.out.println("Member " + memberName + " not found.");
            return;
        }
        Book book = library.findBookByTitle(title);
        if (book == null) {
            System.out.println("Book \"" + title + "\" is not in the catalog.");
            return;
        }
        if (member.returnBook(book)) {
            System.out.println(member.getName() + " has successfully returned " + book.getTitle() + ".");
        } else {
            System.out.println(member.getName() + " didn't borrow " + book.getTitle() + ".");
        }
    }

    // ---------- Lookups and displays ----------

    public void lookupBook(String title) {
        Book book = library.findBookByTitle(title);
        System.out.println(book == null ? "Book \"" + title + "\" not found." : "Found: " + book);
    }

    public void lookupMember(String name) {
        Member member = library.findMemberByName(name);
        System.out.println(member == null ? "Member " + name + " not found." : "Found: " + member);
    }

    public void displayCatalog() {
        printBooks("Book catalog:", library.getCatalog());
    }

    public void displayAvailableBooks() {
        printBooks("Available books:", library.getAvailableBooks());
    }

    public void displayBorrowedBooks() {
        printBooks("Borrowed books:", library.getBorrowedBooks());
    }

    public void displayMembers() {
        System.out.println("Library members:");
        List<Member> members = library.getMembers();
        if (members.isEmpty()) {
            System.out.println("  (none)");
        }
        for (Member member : members) {
            System.out.println("  " + member);
        }
    }

    public void displayMemberBooks(String name) {
        Member member = library.findMemberByName(name);
        if (member == null) {
            System.out.println("Member " + name + " not found.");
            return;
        }
        printBooks("Books borrowed by " + member.getName() + ":", member.getBorrowedBooks());
    }

    private void printBooks(String heading, List<Book> books) {
        System.out.println(heading);
        if (books.isEmpty()) {
            System.out.println("  (none)");
        }
        for (Book book : books) {
            System.out.println("  " + book);
        }
    }

}
