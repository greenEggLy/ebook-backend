package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.PictureDao;
import com.example.ebookbackend.entity.Picture;
import com.example.ebookbackend.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PicDaoImpl implements PictureDao {
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public Picture getOne(Long id) {
        return pictureRepository.getPictureById(id);
    }

    @Override
    public Optional<Picture> findPictureById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public Picture addPic(String url) {
        Picture pic = Picture.builder()
                .url(url).build();
        pictureRepository.save(pic);
        return pic;
    }

    @Override
    public void rmPic(Long id) {
        pictureRepository.deletePictureById(id);
    }

    @Override
    public void modUrl(Long id, String url) {
        Picture pic = getOne(id);
        pic.setUrl(url);
    }
}
