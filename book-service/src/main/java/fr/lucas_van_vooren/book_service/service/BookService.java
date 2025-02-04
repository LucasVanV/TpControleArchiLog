package fr.lucas_van_vooren.book_service.service;

import fr.lucas_van_vooren.book_service.model.Book;

public interface BookService {

    public Iterable<Book> getAllBook();
    public Book getBookById(Long id);
    public Book createBook(Book book);
    public void deleteBook(Long id);
    public void updateBook(Book book);
    public void borrowBook(Long id);
    public void unBorrowBook(Long id);

}
