package fr.lucas_van_vooren.book_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.lucas_van_vooren.book_service.model.Book;
import fr.lucas_van_vooren.book_service.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBook();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return;
    }

    @PatchMapping("/{id}/borrow")
    public void borrowBook(@PathVariable Long id) {
        bookService.borrowBook(id);
        return;
    }

    @PatchMapping("/{id}/unBorrow")
    public void unBorrowBook(@PathVariable Long id) {
        bookService.unBorrowBook(id);
        return;
    }
}
