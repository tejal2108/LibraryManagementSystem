package com.StudentLibrary.Studentlibrary.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    Author author;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Card card;

    @Transient
    private Boolean available = false;  // Default value to avoid null

    // getter and setter
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }


    @Column(name = "available_copies")
    private Integer availableCopies;


    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaction> transactions;

    public Book(){

    }

    public Book(String name, Genre genre, Author author, int availableCopies) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.availableCopies = availableCopies;
    }


    public void setAvailableCopies(Integer copies) {
        this.availableCopies = copies;
        this.available = (copies != null && copies > 0);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // getter for available (boolean uses 'is' prefix)
    public boolean isAvailable() {
        return available;
    }

    // setter for available
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // setter for availableCopies that also sets available
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
        this.available = (availableCopies > 0);
    }

    // getter for availableCopies
    public int getAvailableCopies() {
        return (availableCopies == null) ? 0 : availableCopies;
    }



}
