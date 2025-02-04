package fr.lucas_van_vooren.borrowing_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.lucas_van_vooren.borrowing_service.model.Borrowing;

public interface BorrowingRepository extends CrudRepository<Borrowing, Long>{
    
    @Query("SELECT b FROM Borrowing b WHERE b.bookId = :bookId AND b.returnDate IS NULL")
    Optional<Borrowing> findByBookIdAndNotReturned(@Param("bookId") Long bookId);

    @Query("SELECT COUNT(b) FROM Borrowing b WHERE b.userId = :userId AND b.returnDate IS NULL")
    int countUnreturnedBooksByUserId(@Param("userId") Long userId);

    void deleteByBookId(Long bookId);
    void deleteByUserId(Long userId);
}
