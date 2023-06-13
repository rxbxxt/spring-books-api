package book.api.app.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column (name = "book_name")
  private String name;

  @Column (name = "end_time")
  private LocalDateTime endTime;

  public Book() { /* Default constructor required by JPA */ }

  public Book(String name) {
    this.name = name;
    this.endTime = LocalDateTime.now();
  }

  public Long getId() { return id; }
  public String getName() { return name; }

  public void setName(String name) { this.name = name; }
  public void setId(Long id) { this.id = id; }
}
