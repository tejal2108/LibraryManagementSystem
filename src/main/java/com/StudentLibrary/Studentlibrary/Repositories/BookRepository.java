package com.StudentLibrary.Studentlibrary.Repositories;

import com.StudentLibrary.Studentlibrary.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b where b.genre = :genre and b.author.name = :author and b.availableCopies > 0")
    List<Book> findByGenreAndAuthorAndAvailable(@Param("genre") String genre, @Param("author") String author);

    @Query("select b from Book b where b.genre = :genre and b.author.name = :author and b.availableCopies = 0")
    List<Book> findByGenreAndAuthorAndUnavailable(@Param("genre") String genre, @Param("author") String author);

    @Query("select b from Book b where b.genre = :genre and b.availableCopies > 0")
    List<Book> findByGenreAndAvailable(@Param("genre") String genre);

    @Query("select b from Book b where b.genre = :genre and b.availableCopies = 0")
    List<Book> findByGenreAndUnavailable(@Param("genre") String genre);

    @Query("select b from Book b where b.author.name = :author and b.availableCopies > 0")
    List<Book> findByAuthorAndAvailable(@Param("author") String author);

    @Query("select b from Book b where b.author.name = :author and b.availableCopies = 0")
    List<Book> findByAuthorAndUnavailable(@Param("author") String author);

    @Query("select b from Book b where b.availableCopies > 0")
    List<Book> findBooksByAvailability();

    @Query("select b from Book b where b.availableCopies = 0")
    List<Book> findUnavailableBooks();

}
