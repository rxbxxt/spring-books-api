package book.api.app.controller;

import book.api.app.dto.BookDto;
import book.api.app.model.Book;
import book.api.app.service.BooksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {
  private final BooksService booksService;

  public BooksController(BooksService booksService) {
    this.booksService = booksService;
  }

  @PostMapping("/book")
  public void createBook(@RequestBody BookDto book) {
    booksService.createBook(book);
  }

  @GetMapping("/books")
  public List<Book> getBooks() {
    return booksService.getBooks();
  }

  @PutMapping("/book/{id}")
  public void updateBook(@PathVariable Long id, @RequestBody BookDto book) {
    booksService.updateBook(id, book);
  }

  @DeleteMapping("/book/{id}")
  public void deleteBook(@PathVariable Long id) {
    booksService.deleteBook(id);
  }
}
