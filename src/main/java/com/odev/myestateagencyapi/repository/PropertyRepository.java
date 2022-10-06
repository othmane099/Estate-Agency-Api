package com.odev.myestateagencyapi.repository;

import com.odev.myestateagencyapi.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByPropertyTitle(String title);

    List<Property> findAllByVisibility(Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndBathsAndLocationAndGarageAndVisibility(
            String title, PropertyType type, PropertyStatus status, Bedroom bed, Bathroom bath, String city, Garage garage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndBathsAndGarageAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bedroom pBed, Bathroom pBath, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBedsAndBathsAndGarageAndVisibility(String keyword, PropertyStatus pStatus, Bedroom pBed, Bathroom pBath, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBedsAndBathsAndGarageAndVisibility(String keyword, Bedroom pBed, Bathroom pBath, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBathsAndGarageAndVisibility(String keyword, Bathroom pBath, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndGarageAndVisibility(String keyword, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndVisibility(String keyword, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBedsAndBathsAndLocationAndGarageAndVisibility(String keyword, PropertyStatus pStatus, Bedroom pBed, Bathroom pBath, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBedsAndBathsAndLocationAndGarageAndVisibility(String keyword, PropertyType pType, Bedroom pBed, Bathroom pBath, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBathsAndLocationAndGarageAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bathroom pBath, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndLocationAndGarageAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bedroom pBed, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndBathsAndLocationAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bedroom pBed, Bathroom pBath, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBedsAndBathsAndGarageAndVisibility(String keyword, PropertyType pType, Bedroom pBed, Bathroom pBath, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBathsAndGarageAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bathroom pBath, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndGarageAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bedroom pBed, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndBathsAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bedroom pBed, Bathroom pBath, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBedsAndBathsAndLocationAndGarageAndVisibility(String keyword, Bedroom pBed, Bathroom pBath, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBathsAndLocationAndGarageAndVisibility(String keyword, PropertyStatus pStatus, Bathroom pBath, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBedsAndLocationAndGarageAndVisibility(String keyword, PropertyStatus pStatus, Bedroom pBed, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBedsAndBathsAndLocationAndVisibility(String keyword, PropertyStatus pStatus, Bedroom pBed, Bathroom pBath, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBathsAndLocationAndGarageAndVisibility(String keyword, PropertyType pType, Bathroom pBath, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBedsAndLocationAndGarageAndVisibility(String keyword, PropertyType pType, Bedroom pBed, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBedsAndBathsAndLocationAndVisibility(String keyword, PropertyType pType, Bedroom pBed, Bathroom pBath, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndLocationAndGarageAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBathsAndLocationAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bathroom pBath, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBathsAndGarageAndVisibility(String keyword, PropertyStatus pStatus, Bathroom pBath, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBedsAndGarageAndVisibility(String keyword, PropertyStatus pStatus, Bedroom pBed, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBedsAndBathsAndVisibility(String keyword, PropertyStatus pStatus, Bedroom pBed, Bathroom pBath, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBathsAndGarageAndVisibility(String keyword, PropertyType pType, Bathroom pBath, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBedsAndGarageAndVisibility(String keyword, PropertyType pType, Bedroom pBed, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBedsAndBathsAndVisibility(String keyword, PropertyType pType, Bedroom pBed, Bathroom pBath, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndGarageAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBathsAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bathroom pBath, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Bedroom pBed, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBathsAndLocationAndGarageAndVisibility(String keyword, Bathroom pBath, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBedsAndLocationAndGarageAndVisibility(String keyword, Bedroom pBed, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBedsAndBathsAndLocationAndVisibility(String keyword, Bedroom pBed, Bathroom pBath, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndLocationAndGarageAndVisibility(String keyword, PropertyStatus pStatus, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBathsAndLocationAndVisibility(String keyword, PropertyStatus pStatus, Bathroom pBath, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBedsAndLocationAndVisibility(String keyword, PropertyStatus pStatus, Bedroom pBed, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndLocationAndGarageAndVisibility(String keyword, PropertyType pType, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBathsAndLocationAndVisibility(String keyword, PropertyType pType, Bathroom pBath, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBedsAndLocationAndVisibility(String keyword, PropertyType pType, Bedroom pBed, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndLocationAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBedsAndGarageAndVisibility(String keyword, Bedroom pBed, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBedsAndBathsAndVisibility(String keyword, Bedroom pBed, Bathroom pBath, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndGarageAndVisibility(String keyword, PropertyStatus pStatus, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBathsAndVisibility(String keyword, PropertyStatus pStatus, Bathroom pBath, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndBedsAndVisibility(String keyword, PropertyStatus pStatus, Bedroom pBed, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndGarageAndVisibility(String keyword, PropertyType pType, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBathsAndVisibility(String keyword, PropertyType pType, Bathroom pBath, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndBedsAndVisibility(String keyword, PropertyType pType, Bedroom pBed, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndStatusAndVisibility(String keyword, PropertyType pType, PropertyStatus pStatus, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndLocationAndGarageAndVisibility(String keyword, String city, Garage pGarage, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBathsAndLocationAndVisibility(String keyword, Bathroom pBath, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBedsAndLocationAndVisibility(String keyword, Bedroom pBed, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndLocationAndVisibility(String keyword, PropertyStatus pStatus, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndLocationAndVisibility(String keyword, PropertyType pType, String city, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBathsAndVisibility(String keyword, Bathroom pBath, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndBedsAndVisibility(String keyword, Bedroom pBed, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndStatusAndVisibility(String keyword, PropertyStatus pStatus, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndTypeAndVisibility(String keyword, PropertyType pType, Visibility visibility);

    List<Property> findAllByPropertyTitleContainingAndLocationAndVisibility(String keyword, String city, Visibility visibility);
}
