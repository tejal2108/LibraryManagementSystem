package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.*;
import com.StudentLibrary.Studentlibrary.Repositories.BookRepository;
import com.StudentLibrary.Studentlibrary.Repositories.CardRepository;
import com.StudentLibrary.Studentlibrary.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    @Value("${books.max_allowed}")
    int max_allowed_books;
    @Value("${books.max_allowed_days}")
    int max_days_allowed;
    @Value("${books.fine.per_day}")
    int fine_per_day;


    public String issueBooks(int cardId, int bookId) throws Exception {
        Book book = bookRepository.findById(bookId).orElse(null);

        if (book == null || book.getAvailableCopies() <= 0) {
            throw new Exception("Book is either unavailable or not present!!");
        }

        Card card = cardRepository.findById(cardId).orElse(null);

        if (card == null || card.getCardStatus() == CardStatus.DEACTIVATED) {
            throw new Exception("Card is invalid!!");
        }

        if (card.getBooks().size() >= max_allowed_books) {
            throw new Exception("Book limit reached for this card!!");
        }

        // Decrease available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        // Assign the book to card if needed
        book.setCard(card);

        List<Book> books = card.getBooks();
        books.add(book);
        card.setBooks(books);

        bookRepository.save(book); // use save to update book

        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setBook(book);
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);

        transactionRepository.save(transaction);

        return transaction.getTransactionId();
    }


    public String returnBooks(int cardId, int bookId) throws Exception {
        List<Transaction> transactions = transactionRepository.findByCard_Book(cardId, bookId, TransactionStatus.SUCCESSFUL, true);

        if (transactions.isEmpty()) {
            throw new Exception("No issue transaction found for this book and card");
        }

        Transaction last_issue_transaction = transactions.get(transactions.size() - 1);

        Date issueDate = last_issue_transaction.getTransactionDate();
        long issueTime = Math.abs(issueDate.getTime() - System.currentTimeMillis());
        long number_of_days_passed = TimeUnit.DAYS.convert(issueTime, TimeUnit.MILLISECONDS);

        int fine = 0;
        if (number_of_days_passed > max_days_allowed) {
            fine = (int) ((number_of_days_passed - max_days_allowed) * fine_per_day);
        }

        Card card = last_issue_transaction.getCard();
        Book book = last_issue_transaction.getBook();

        // Increase available copies
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        // Remove book from card’s list
        card.getBooks().remove(book);

        // Unlink card from book
        book.setCard(null);

        // Persist both
        bookRepository.save(book);
        cardRepository.save(card); // optional, good for consistency

        // Add return transaction
        Transaction new_transaction = new Transaction();
        new_transaction.setBook(book);
        new_transaction.setCard(card);
        new_transaction.setFineAmount(fine);
        new_transaction.setIssueOperation(false);
        new_transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);

        transactionRepository.save(new_transaction);

        return new_transaction.getTransactionId();
    }

}
