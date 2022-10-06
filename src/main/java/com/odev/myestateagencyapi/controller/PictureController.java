package com.odev.myestateagencyapi.controller;

import com.flickr4java.flickr.FlickrException;
import com.odev.myestateagencyapi.dto.DeletePictureDto;
import com.odev.myestateagencyapi.model.Picture;
import com.odev.myestateagencyapi.model.Property;
import com.odev.myestateagencyapi.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class PictureController {

    private final PictureService pictureService;

    @PostMapping("/admin/property/picture/delete")
    public Property delete(@RequestBody DeletePictureDto dto) throws FlickrException, IOException, ExecutionException, InterruptedException {
        return pictureService.deletePicture(dto.getPicture(), dto.getProperty());
    }

    @PostMapping("/admin/property/{propertyId}/pictures/save")
    public Property save(@RequestPart("pictures") MultipartFile[] pictures,
                         @PathVariable Long propertyId) throws FlickrException, IOException, ExecutionException, InterruptedException {

        return pictureService.savePictures(pictures, propertyId);
    }

    @GetMapping("/admin/property/{propertyId}/pictures")
    public List<Picture> pictures(@PathVariable Long propertyId) throws FlickrException, IOException, ExecutionException, InterruptedException {
        return pictureService.findAllByPropertyId(propertyId);
    }
}
