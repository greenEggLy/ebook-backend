package com.example.ebookbackend.repository;

import com.example.ebookbackend.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    Picture getPictureById(Long id);


    void deletePictureById(Long id);

}
