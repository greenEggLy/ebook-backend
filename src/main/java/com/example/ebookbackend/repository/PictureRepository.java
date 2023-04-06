package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.Picture;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PictureRepository extends JpaRepository<Picture, Long> {
    Picture getPictureById(Long id);

    void deletePictureById(Long id);
}
