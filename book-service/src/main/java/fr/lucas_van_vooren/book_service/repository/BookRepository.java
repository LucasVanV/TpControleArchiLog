package fr.lucas_van_vooren.book_service.repository;

import org.springframework.data.repository.CrudRepository;

import fr.lucas_van_vooren.book_service.model.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
    
    
}
