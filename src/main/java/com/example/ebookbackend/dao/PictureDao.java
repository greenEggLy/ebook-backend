package com.example.ebookbackend.dao;

import com.example.ebookbackend.entity.Picture;

import java.util.Optional;

public interface PictureDao {
    Picture getOne(Long id);

    Optional<Picture> findPictureById(Long id);

    Picture addPic(String url);

    void rmPic(Long id);

    void modUrl(Long id, String url);
}
