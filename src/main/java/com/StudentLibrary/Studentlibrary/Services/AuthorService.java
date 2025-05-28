package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Author;
import com.StudentLibrary.Studentlibrary.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;


    public void createAuthor(Author author){
        authorRepository.save(author);

    }
    public void updateAuthor(int id, Author author){
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));

        existing.setName(author.getName());
        existing.setEmail(author.getEmail());
        existing.setAge(author.getAge());
        existing.setCountry(author.getCountry());

        authorRepository.save(existing);  // save updated object
    }

    public void deleteAuthor(int id ){
        authorRepository.deleteCustom(id);
    }
}
