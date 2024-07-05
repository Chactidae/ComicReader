package com.example.ComicReader.repositories;


import com.example.ComicReader.model.Book;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long>{

    @Query(value = "SELECT * FROM book WHERE book.b_name = :b_name", nativeQuery = true)
    List<Book> findByBname(@Param("b_name") String b_name);

    @Query(value = "SELECT * FROM book ORDER BY book_id DESC LIMIT 10", nativeQuery = true)
    List<Book> findAllBooks();

    @Query(value = "SELECT author_id FROM book WHERE book.book_id = :id", nativeQuery = true)
    Long findAuthor(@Param("id") Long id);

    @Query(value = "SELECT book_info_id FROM book WHERE book.book_id = :id", nativeQuery = true)
    Long findInfo(@Param("id") Long id);




}
