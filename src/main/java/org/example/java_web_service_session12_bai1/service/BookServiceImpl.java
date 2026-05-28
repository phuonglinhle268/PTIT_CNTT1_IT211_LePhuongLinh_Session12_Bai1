package org.example.java_web_service_session12_bai1.service;

import org.example.java_web_service_session12_bai1.exception.ResourceNotFoundException;
import org.example.java_web_service_session12_bai1.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final List<Book> books = new ArrayList<>();

    private Long nextId = 1L;

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getBookById(Long id) {

        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Không thấy sách với id: " + id));
    }

    @Override
    public Book createBook(Book book) {

        book.setId(nextId++);
        books.add(book);

        return book;
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {

        Book existingBook = getBookById(id);

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPrice(updatedBook.getPrice());

        return existingBook;
    }

    @Override
    public void deleteBook(Long id) {

        Book book = getBookById(id);

        books.remove(book);
    }
}