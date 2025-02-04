package fr.lucas_van_vooren.borrowing_service.service;

import fr.lucas_van_vooren.borrowing_service.model.Borrowing;

public interface BorrowingService {
    public Iterable<Borrowing> getAllBorrow();
    public Borrowing getBorrowById(Long id);
    public Borrowing createBorrow(Borrowing borrow);
    public void deleteBorrow(Long id);
    public void returnBook(Long id);
    public boolean isBookAvailable(Long id);
}
