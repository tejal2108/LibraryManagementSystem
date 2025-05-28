package com.StudentLibrary.Studentlibrary.Controllers;

import com.StudentLibrary.Studentlibrary.Model.Book;
import com.StudentLibrary.Studentlibrary.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/createBook")
    public ResponseEntity<?> createBook(@RequestBody Book book){
        try {
            bookService.createBook(book);
            return new ResponseEntity<>("Book added to the library system", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



//    @GetMapping("/getBooks")
//    public ResponseEntity getBooks(@RequestParam(value = "genre",required = false) String genre,
//                                   @RequestParam(value = "available",required = false,defaultValue = "false") boolean available,
//                                   @RequestParam(value = "author",required = false) String author){
//
//        List<Book> bookList=bookService.getBooks(genre,available,author);
//        return new ResponseEntity(bookList,HttpStatus.OK);
//    }


    @PutMapping("/updateBook/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody Book book){
        try {
            Book updatedBook = bookService.updateBook(id, book);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getBooks")
    public ResponseEntity<?> getBooks(
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "available", required = false) Boolean available,
            @RequestParam(value = "author", required = false) String author) {

        try {
            // if available is null, you may choose to treat it as 'no filter'
            List<Book> bookList = bookService.getBooks(genre, available, author);
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // print stack trace in console/log
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    // Delete a book by ID
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        try {
            String response = bookService.deleteBook(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
