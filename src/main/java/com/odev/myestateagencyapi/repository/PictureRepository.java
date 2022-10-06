package com.odev.myestateagencyapi.repository;

import com.odev.myestateagencyapi.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findAllByPropertyId(Long propertyId);
}
