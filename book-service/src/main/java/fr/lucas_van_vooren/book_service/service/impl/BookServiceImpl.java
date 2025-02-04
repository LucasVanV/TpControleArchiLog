package fr.lucas_van_vooren.book_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.lucas_van_vooren.book_service.kafka.BookKafkaProducer;
import fr.lucas_van_vooren.book_service.model.Book;
import fr.lucas_van_vooren.book_service.repository.BookRepository;
import fr.lucas_van_vooren.book_service.service.BookService;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookKafkaProducer bookKafkaProducer;

    public Iterable<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book createBook(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookKafkaProducer.sendBookDeleteEvent(id);
        bookRepository.deleteById(id);
    }

    public void updateBook(Book book){
        if(!bookRepository.existsById(book.getId())){
            throw new RuntimeException("User not found");
        }
        bookRepository.save(book);
    }

    public void borrowBook(Long id){
        Book databaseBook = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));
        
        if(!databaseBook.isAvailable()){
            throw new RuntimeException("Book not available");
        }
        databaseBook.setAvailable(false);
        bookRepository.save(databaseBook);
    }

    public void unBorrowBook(Long id){
        Book databaseBook = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));
        
        databaseBook.setAvailable(true);
        bookRepository.save(databaseBook);
    }
}
