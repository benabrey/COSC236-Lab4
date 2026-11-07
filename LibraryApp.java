package lab4.library;
//Simplified verion of Library Management System

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class LibraryApp {

    public static void main(String[] args) {

        Library library = new Library();
        LibrarianController librarian = new LibrarianController(library);

        System.out.println(" *** Library management system demo *** ");

        // Use case: Add a book
        step("Adding \"Dune\" to the library");
        librarian.addBook("Dune", "Frank Herbert");

        step("Adding \"1984\" to the library");
        librarian.addBook("1984", "George Orwell");

        step("Adding \"Moby Dick\" to the library");
        librarian.addBook("Moby Dick", "Herman Melville");

        // Error case: duplicate book
        step("Adding \"Dune\" again (duplicate)");
        librarian.addBook("Dune", "Frank Herbert");

        // Use case: Display book catalog
        step("Display book catalog");
        librarian.displayCatalog();

        // Use case: Add a member
        step("Adding \"Alice\" to the library members");
        librarian.registerMember("Alice");

        step("Adding \"Bob\" to the library members");
        librarian.registerMember("Bob");

        // Error case: duplicate member
        step("Adding \"Alice\" again (duplicate)");
        librarian.registerMember("Alice");

        // Use case: Display members
        step("Display all members");
        librarian.displayMembers();

        // Use case: Lookup a book / member
        step("Lookup book \"1984\"");
        librarian.lookupBook("1984");

        step("Lookup book \"Hamlet\" (not in catalog)");
        librarian.lookupBook("Hamlet");

        step("Lookup member \"Bob\"");
        librarian.lookupMember("Bob");

        step("Lookup member \"Charlie\" (not registered)");
        librarian.lookupMember("Charlie");

        // Use case: Borrow a book
        step("Alice borrows Dune");
        librarian.borrowBook("Alice", "Dune");

        step("Bob borrows 1984");
        librarian.borrowBook("Bob", "1984");

        step("Display available books");
        librarian.displayAvailableBooks();

        // Use case: Borrow a book -> extension: Reject Request
        step("Bob borrows Dune (already borrowed by Alice)");
        librarian.borrowBook("Bob", "Dune");

        // Error cases for borrowing
        step("Charlie borrows Moby Dick (not a member)");
        librarian.borrowBook("Charlie", "Moby Dick");

        step("Alice borrows Hamlet (not in catalog)");
        librarian.borrowBook("Alice", "Hamlet");

        // Use case: Display borrowed books / a member's books
        step("Display borrowed books");
        librarian.displayBorrowedBooks();

        step("Display books borrowed by Alice");
        librarian.displayMemberBooks("Alice");

        // Business rules protecting removal
        step("Remove \"Dune\" while it is borrowed");
        librarian.removeBook("Dune");

        step("Remove member Alice while she has a book out");
        librarian.removeMember("Alice");

        // Use case: Return a book
        step("Alice returns Dune");
        librarian.returnBook("Alice", "Dune");

        step("Alice returns 1984 (she didn't borrow it)");
        librarian.returnBook("Alice", "1984");

        step("Display available books");
        librarian.displayAvailableBooks();

        step("Bob borrows Dune (now available)");
        librarian.borrowBook("Bob", "Dune");

        step("Display books borrowed by Bob");
        librarian.displayMemberBooks("Bob");

        // Use case: Remove a book
        step("Remove \"Moby Dick\" from the library");
        librarian.removeBook("Moby Dick");

        // Use case: Remove a member
        step("Remove member Alice");
        librarian.removeMember("Alice");

        step("Display all members");
        librarian.displayMembers();

        step("Display book catalog");
        librarian.displayCatalog();
    }

    private static void step(String description) {
        System.out.println("\n *** " + description + ":");
    }
}
