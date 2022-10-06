package com.odev.myestateagencyapi.service;

import com.flickr4java.flickr.FlickrException;
import com.odev.myestateagencyapi.exception.EstateAgencyException;
import com.odev.myestateagencyapi.model.Picture;
import com.odev.myestateagencyapi.model.Property;
import com.odev.myestateagencyapi.repository.AppSettingRepository;
import com.odev.myestateagencyapi.repository.PictureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.odev.myestateagencyapi.exception.ErrorCodes.NULL_CLASS_PROPERTY;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PictureService {

    private final PictureRepository pictureRepository;
    private final PropertyService propertyService;
    private final FlickrService flickrService;
    private final AppSettingRepository appSettingRepository;


    public Property deletePicture(Picture picture, Property property) throws FlickrException, IOException, ExecutionException, InterruptedException {
        flickrService.connect();
        flickrService.deletePhoto(picture.getPId());
        pictureRepository.delete(picture);

        if (property.getDefaultPicture().equals(picture.getLink()) || property.getPictures().size() == 1 ){
            String defaultPicture = appSettingRepository.findAll().get(0).getDefaultImageLink();
            property.setDefaultPicture(defaultPicture);
        }
        property.getPictures().removeIf( pic -> pic.getId().equals(picture.getId()));
        return propertyService.update(property);
    }

    public Property savePictures(MultipartFile[] pictures, Long propertyId) throws FlickrException, IOException, ExecutionException, InterruptedException {

        if (pictures.length == 0)
            throw new RuntimeException("pictures list is empty, it should contain at least one picture");
        flickrService.connect();
        Property property = propertyService.findById(propertyId);

        Arrays.stream(pictures)
                .forEach(picture -> {
                    try {
                        Picture pictureTemp = flickrService.savePhoto(picture.getInputStream(), picture.getOriginalFilename());
                        pictureTemp.setProperty(property);
                        if (!StringUtils.hasLength(pictureTemp.getLink())) {
                            throw new RuntimeException("Erreur lors de l'enregistrement de photo de property");
                        }
                        property.getPictures().add(pictureRepository.save(pictureTemp));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return propertyService.update(property);
    }

    public List<Picture> findAllByPropertyId(Long id){
        if (id == null){
            log.error("Property's ID is null");
            throw new EstateAgencyException("Property's ID is null", new NullPointerException(), NULL_CLASS_PROPERTY);
        }

        return pictureRepository.findAllByPropertyId(id);

    }
}
