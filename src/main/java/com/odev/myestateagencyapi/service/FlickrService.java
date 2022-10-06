package com.odev.myestateagencyapi.service;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.odev.myestateagencyapi.model.Picture;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FlickrService {

  @Value("${flickr.apiKey}")
  private String apiKey;

  @Value("${flickr.apiSecret}")
  private String apiSecret;

  @Value("${flickr.appKey}")
  private String appKey;

  @Value("${flickr.appSecret}")
  private String appSecret;

  private Flickr flickr;

  // upload images to flickr's storage
  @SneakyThrows
  public Picture savePhoto(InputStream photo, String title) {
    UploadMetaData uploadMetaData = new UploadMetaData();
    uploadMetaData.setTitle(title);
    String pictureId = flickr.getUploader().upload(photo, uploadMetaData);
    String pictureLink = flickr.getPhotosInterface().getPhoto(pictureId).getMedium640Url();

    Picture picture = new Picture();
    picture.setPId(pictureId);
    picture.setLink(pictureLink);
    picture.setTitle(title);
    picture.setIsDefault(false);
    picture.setIsSlide(false);
    return picture;
  }

  // delete images from flickr's storage
  public void deletePhoto(String pid) throws FlickrException {
    flickr.getPhotosInterface().delete(pid);
  }

  // connect to flickr's storage
  public void connect() throws InterruptedException, ExecutionException, IOException, FlickrException {
    flickr = new Flickr(apiKey, apiSecret, new REST());
    Auth auth = new Auth();
    auth.setPermission(Permission.READ);
    auth.setPermission(Permission.WRITE);
    auth.setPermission(Permission.DELETE);
    auth.setToken(appKey);
    auth.setTokenSecret(appSecret);
    RequestContext requestContext = RequestContext.getRequestContext();
    requestContext.setAuth(auth);
    flickr.setAuth(auth);
  }

}
