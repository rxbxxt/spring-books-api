package book.api.app.service;

import book.api.app.dto.BookDto;
import book.api.app.model.Book;
import book.api.app.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
  private final BooksRepository booksRepository;

  @Autowired
  public BooksService(BooksRepository bookRepository) {
    this.booksRepository = bookRepository;
  }
  public List<Book> getBooks() {
    return booksRepository.findAll();
  }

  public void createBook(BookDto book) {
    booksRepository.save(new Book(book.getName()));
  }

  public void updateBook(Long id, BookDto bookDto) {
    Optional<Book> bookOptional = booksRepository.findById(id);

    if (bookOptional.isPresent()) {
      Book book = bookOptional.get();
      book.setName(bookDto.getName());
      booksRepository.save(book);
    }
  }

  public void deleteBook(Long id) {
    booksRepository.deleteById(id);
  }
}
