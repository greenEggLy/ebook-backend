package com.example.ebookbackend.daoImpl;

import com.example.ebookbackend.dao.PictureDao;
import com.example.ebookbackend.entity.Picture;
import com.example.ebookbackend.repository.PictureRepository;

public class PicDaoImpl implements PictureDao {
    private PictureRepository pictureRepository;

    @Override
    public Picture getOne(Long id) {
        return pictureRepository.getPictureById(id);
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
