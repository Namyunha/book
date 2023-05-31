package com.example.book.repository;


import com.example.book.entity.BookEntity;
import com.example.book.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
