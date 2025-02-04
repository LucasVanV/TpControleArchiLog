package fr.lucas_van_vooren.borrowing_service.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.lucas_van_vooren.borrowing_service.model.Borrowing;
import fr.lucas_van_vooren.borrowing_service.repository.BorrowingRepository;
import fr.lucas_van_vooren.borrowing_service.service.BorrowingService;

@Service
public class BorrowingServiceImp implements BorrowingService{
    @Autowired
    private BorrowingRepository borrowingRepository;

    public Iterable<Borrowing> getAllBorrow() {
        return borrowingRepository.findAll();
    }

    public Borrowing getBorrowById(Long id) {
        return borrowingRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrow not found"));
    }

    public Borrowing createBorrow(Borrowing borrow) {
        borrow.setBorrowDate(LocalDate.now());
        return borrowingRepository.save(borrow);
    }

    public void deleteBorrow(Long id) {
        borrowingRepository.deleteById(id);
    }

    public void returnBook(Long id){
        Borrowing databaseBorrow = borrowingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("borrow not found"));
        databaseBorrow.setReturnDate(LocalDate.now());
        borrowingRepository.save(databaseBorrow);
    }

    public boolean isBookAvailable(Long id){
        return  borrowingRepository.findByBookIdAndNotReturned(id).isPresent();
    }
}
