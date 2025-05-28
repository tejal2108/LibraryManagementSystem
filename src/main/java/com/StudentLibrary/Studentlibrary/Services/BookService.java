package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Author;
import com.StudentLibrary.Studentlibrary.Model.Book;
import com.StudentLibrary.Studentlibrary.Repositories.AuthorRepository;
import com.StudentLibrary.Studentlibrary.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    //Either giving you all the available books
    //OR gicing you all the non-available books

    // Create book with author check
//    public void createBook(Book book){
//        Author author = book.getAuthor();
//
//        if (author != null && author.getId() != 0) {
//            // Check if author exists
//            Optional<Author> existingAuthor = authorRepository.findById(author.getId());
//            book.setAuthor(existingAuthor.orElse(null));  // Set to null if not found
//        } else {
//            book.setAuthor(null);
//        }
//        book.setAvailable(book.getAvailableCopies() > 0);
//        bookRepository.save(book);
//    }

    public void createBook(Book book){
        Author author = book.getAuthor();

        if (author != null && author.getId() != 0) {
            Optional<Author> existingAuthor = authorRepository.findById(author.getId());
            if (existingAuthor.isPresent()) {
                System.out.println("Author found with ID: " + author.getId());
                book.setAuthor(existingAuthor.get());
            } else {
                System.out.println("Author NOT found with ID: " + author.getId());
                book.setAuthor(null);
            }
        } else {
            System.out.println("No author ID provided, setting author as null.");
            book.setAuthor(null);
        }

        book.setAvailable(book.getAvailableCopies() > 0);
        bookRepository.save(book);
    }



    public List<Book> getBooks(String genre, Boolean available, String authorName) {
        List<Book> books = bookRepository.findAll();

        if (genre != null && !genre.isEmpty()) {
            books = books.stream()
                    .filter(book -> book.getGenre() != null &&
                            book.getGenre().name().equalsIgnoreCase(genre))
                    .collect(Collectors.toList());  // Use collect(Collectors.toList()) for Java 8 compatibility
        }


        if (authorName != null && !authorName.isEmpty()) {
            books = books.stream()
                    .filter(book -> book.getAuthor() != null &&
                            book.getAuthor().getName().equalsIgnoreCase(authorName))
                    .collect(Collectors.toList());
        }

        if (available != null) {
            books = books.stream()
                    .filter(book -> book.isAvailable() == available)
                    .collect(Collectors.toList());
        }

        return books;
    }



    // Delete book

    public String deleteBook(int id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
        return "Book deleted successfully.";
    }

    public Book updateBook(int id, Book bookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));

        existingBook.setName(bookDetails.getName());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setAvailableCopies(bookDetails.getAvailableCopies());
        existingBook.setAvailable(bookDetails.getAvailableCopies() > 0);

        return bookRepository.save(existingBook);
    }


}
