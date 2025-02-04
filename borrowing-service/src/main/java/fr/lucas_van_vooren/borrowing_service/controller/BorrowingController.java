package fr.lucas_van_vooren.borrowing_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.lucas_van_vooren.borrowing_service.model.Borrowing;
import fr.lucas_van_vooren.borrowing_service.service.BorrowingService;


@RestController
@RequestMapping("/borrowings")
public class BorrowingController {
    
    @Autowired
    private BorrowingService borrowingService;

    @GetMapping
    public Iterable<Borrowing> getAllBorrow() {
        return borrowingService.getAllBorrow();
    }

    @GetMapping("/{id}")
    public Borrowing getBorrowByid(@PathVariable Long id) {
        return borrowingService.getBorrowById(id);
    }

    @PostMapping
    public Borrowing createBorrow(@RequestBody Borrowing borrow) {
        return borrowingService.createBorrow(borrow);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable Long id) {
        borrowingService.deleteBorrow(id);
        return;
    }

    @PatchMapping("/{id}/return")
    public void unBorrowBook(@PathVariable Long id) {
        borrowingService.returnBook(id);
        return;
    }
}
