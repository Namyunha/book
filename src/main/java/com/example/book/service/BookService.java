package com.example.book.service;


import com.example.book.dto.BookDTO;
import com.example.book.entity.BookEntity;
import com.example.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    //  bookDTO -> BookEntity 로 전환 후 save(insert)

    public Long save(BookDTO bookDTO) {
//      bookDTO -> bookEntity 객체로 변환
//      JpaRepository<BookEntity, Long>의 save 함수를 사용하기위해
//      JpaRepository<BookEntity, Long>를 상속받은 bookRepository에 보내준다.
        BookEntity bookEntity = BookEntity.toSaveEntity(bookDTO);
//        System.out.println("bookEntity = " + bookEntity);
//        BookEntity savedEntity = bookRepository.save(bookEntity);
//        System.out.println("savedEntity = " + savedEntity);
        return bookRepository.save(bookEntity).getId();
    }


    //  List<BoaKEntity> -> List<BookDTO>
    public List<BookDTO> findAll() {
//      findAll 함수를 통해 database에 있는 BookEntity 객체들이 들어있는 List를 받아온다
        List<BookEntity> bookEntityList = bookRepository.findAll();

//      BookEntity > BookDTO 로 전환 후 DTO 들을 받을 List를 만든다.
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (BookEntity bookEntity : bookEntityList) {
            BookDTO bookDTO = BookDTO.toDTO(bookEntity);
            bookDTOList.add(bookDTO);
//          bookDTOList.add(BookDTO.toDTO(bookEntity)); 위의 두줄은 한 줄로 축약
        }
        return bookDTOList;
    }

    public BookDTO findById(Long id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);

        if (optionalBookEntity.isPresent()) {
//      Optional<BookEntity> optionalBookEntity에 제대로 id가 들어와있는지 확인하기
            System.out.println("있다");
//      optional 객체에서 꺼내기
            BookEntity bookEntity = optionalBookEntity.get();

//      BookEntity -> BookDTO 변환
            BookDTO bookDTO = BookDTO.toDTO(bookEntity);
            return bookDTO;
//      return BookDTO.toDTO(optionalBookEntity.get());

        } else {
            System.out.println("없다");
            return null;
        }
//      Optional은 java클래스이다.
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public void update(BookDTO bookDTO) {
        BookEntity bookEntity = BookEntity.toUpdateEntity(bookDTO);
        bookRepository.save(bookEntity);
    }


//     1. Entity -> DTO 변환 메서드 호출  (메서드는 BookDTO에 정의)
//     2. 호출결과를 DTO 객체로 받음.
//     3. DTO객체를 DTO리스트에 추가
//     4. 반복문 종료후 DTO 리스트를 리턴
//     Repository로 주고받는 타입은 Entity이다.
//     Entity는 소중하기 때문에 dto리스트로 옮겨야한다.


}
