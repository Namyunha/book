package com.example.book.entity;

import com.example.book.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.awt.print.Book;

@Entity
@Getter
@Setter
@Table(name = "book_table")
@ToString
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String bookName;
    @Column(length = 20)
    private String bookAuthor;
    @Column
    private int bookPrice;
    //  테이블 설정

    public static BookEntity toSaveEntity(BookDTO bookDTO){
        System.out.println("bookDTO = " + bookDTO);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        return bookEntity;
    }

    public static BookEntity toUpdateEntity(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        return bookEntity;
    }
}
