package com.swung0x48.librarymanager.services;

import com.swung0x48.librarymanager.domain.Book;
import com.swung0x48.librarymanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.selectAllBook();
    }

    public Book lendBook(Integer bookID) {
        Book book = bookRepository.selectBookByID(bookID);
        book.lendChanged();
        bookRepository.updateBook(book);

        return bookRepository.selectBookByID(bookID);
    }

    public Map<String, Integer> getTotalMessage() {
        List<Book> books = bookRepository.selectAllBook();
        Integer sumTotalCount = books.parallelStream().mapToInt(Book::getTotalCount).sum();
        Integer sumLendCount = books.parallelStream().mapToInt(Book::getLendCount).sum();
        Integer sumNowCount = books.parallelStream().mapToInt(Book::getNowCount).sum();

        Map<String, Integer> map = new HashMap<>();
        map.put("sumTotalCount", sumTotalCount);
        map.put("sumLendCount", sumLendCount);
        map.put("sumNowCount", sumNowCount);
        return map;
    }

    public Integer deleteBook(Integer id) {
        return bookRepository.deleteBook(id);
    }

    public Book addBook(Book book) {
        bookRepository.insertBook(book);
        return book;
    }

    public Book banBook(Integer bookID)
    {
        bookRepository.banBook(bookID);
        return bookRepository.selectBookByID(bookID);
    }

    public Book unbanBook(Integer bookID)
    {
        Book temp= bookRepository.selectBookByID(bookID);
        temp.setNowCount(temp.getTotalCount() - temp.getLendCount());
        bookRepository.unbanBook(temp);
        return bookRepository.selectBookByID(bookID);
    }

}
