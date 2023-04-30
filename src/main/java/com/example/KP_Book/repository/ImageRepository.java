package com.example.KP_Book.repository;

import com.example.KP_Book.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image,Long> {
    @Query("DELETE from Image im " +
            "where im.book.id = ?1")
    void deleteAllByBookId(Long id);
}
