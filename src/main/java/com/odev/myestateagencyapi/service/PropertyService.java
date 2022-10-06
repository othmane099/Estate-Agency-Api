package com.odev.myestateagencyapi.service;

import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.model.*;
import com.odev.myestateagencyapi.repository.PictureRepository;
import com.odev.myestateagencyapi.repository.PropertyRepository;
import com.odev.myestateagencyapi.validator.PropertyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.odev.myestateagencyapi.util.Constants.ANY;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final BedroomService bedroomService;
    private final BathroomService bathroomService;
    private final GarageService garageService;
    private final PropertyTypeService typeService;
    private final PropertyStatusService statusService;
    private final PictureRepository pictureRepository;

    public Property findById(Long id){
        if (id == null)
            throw new NullPointerException("Property id is null");

        Optional<Property> property = propertyRepository.findById(id);
        if (property.isEmpty())
            throw new EntityNotFoundException("Property with ID= "+id+" not found", ErrorCodes.PROPERTY_NOT_FOUND);
        return property.get();
    }

    public Property update(Property property){
        if(!property.getPictures().isEmpty()){
            property.getPictures().forEach(picture -> {
                Picture pic = pictureRepository.save(picture);
                pic.setProperty(property);
            });
        }
        return propertyRepository.save(property);
    }


    public Property save(Property property){
        Optional<Property> propertyTemp = propertyRepository.findByPropertyTitle(property.getPropertyTitle());
        if (propertyTemp.isPresent()){
            log.error("Property with title {} already exist", property.getPropertyTitle());
            throw new InvalidEntityException("Property's title already exist", ErrorCodes.PROPERTY_ALREADY_EXISTS);
        }

        return propertyRepository.save(property);
    }

    public List<Property> findAll(){
        return propertyRepository.findAll();
    }

    public List<Property> findAllVisible(){
        return propertyRepository.findAllByVisibility(Visibility.VISIBLE);
    }

    public void deleteById(Long id){
        this.findById(id);
        propertyRepository.deleteById(id);
    }

    public void changeVisibility(Long propertyId){
        Property property = this.findById(propertyId);
        if (property.getVisibility() == Visibility.HIDDEN) property.setVisibility(Visibility.VISIBLE);
        else property.setVisibility(Visibility.HIDDEN);
        this.save(property);
    }

    public void updateLatest(Long propertyId){
        Property property = this.findById(propertyId);
        property.setLatest(!property.getLatest());
        this.save(property);
    }

    public void updateMain(Long propertyId){
        Property property = this.findById(propertyId);
        property.setMain(!property.getMain());
        this.save(property);
    }

    public List<Property> search(String keyword, String city, String type, String status, String beds, String baths, String garages) {
        SearchProperty searchProperty = new SearchProperty(keyword, city, type, status, beds, baths, garages);
        System.out.println(searchProperty);
        if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndBathsAndLocationAndGarageAndVisibility(
                    keyword, pType, pStatus, pBed, pBath, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBedsAndBathsAndLocationAndGarageAndVisibility(
                    keyword, pStatus, pBed, pBath, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBedsAndBathsAndLocationAndGarageAndVisibility(
                    keyword, pType, pBed, pBath, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBathsAndLocationAndGarageAndVisibility(
                    keyword, pType, pStatus, pBath, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndLocationAndGarageAndVisibility(
                    keyword, pType, pStatus, pBed, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndBathsAndLocationAndVisibility(
                    keyword, pType, pStatus, pBed, pBath, city, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ){
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndBathsAndGarageAndVisibility(
                    keyword, pType, pStatus, pBed, pBath, pGarage, Visibility.VISIBLE
            );
        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ){
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBedsAndBathsAndGarageAndVisibility(
                    keyword, pStatus, pBed, pBath, pGarage, Visibility.VISIBLE
            );
        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBedsAndBathsAndGarageAndVisibility(
                    keyword, pType, pBed, pBath, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBathsAndGarageAndVisibility(
                    keyword, pType, pStatus, pBath, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndGarageAndVisibility(
                    keyword, pType, pStatus, pBed, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndBathsAndVisibility(
                    keyword, pType, pStatus, pBed, pBath, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndBedsAndBathsAndLocationAndGarageAndVisibility(
                    keyword, pBed, pBath, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBathsAndLocationAndGarageAndVisibility(
                    keyword, pStatus, pBath, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBedsAndLocationAndGarageAndVisibility(
                    keyword, pStatus, pBed, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBedsAndBathsAndLocationAndVisibility(
                    keyword, pStatus, pBed, pBath, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBathsAndLocationAndGarageAndVisibility(
                    keyword, pType, pBath, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBedsAndLocationAndGarageAndVisibility(
                    keyword, pType, pBed, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBedsAndBathsAndLocationAndVisibility(
                    keyword, pType, pBed, pBath, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndLocationAndGarageAndVisibility(
                    keyword, pType, pStatus, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBathsAndLocationAndVisibility(
                    keyword, pType, pStatus, pBath, city, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ){
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndBedsAndBathsAndGarageAndVisibility(
                    keyword, pBed, pBath, pGarage, Visibility.VISIBLE
            );
        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBathsAndGarageAndVisibility(
                    keyword, pStatus, pBath, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBedsAndGarageAndVisibility(
                    keyword, pStatus, pBed, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBedsAndBathsAndVisibility(
                    keyword, pStatus, pBed, pBath, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBathsAndGarageAndVisibility(
                    keyword, pType, pBath, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBedsAndGarageAndVisibility(
                    keyword, pType, pBed, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBedsAndBathsAndVisibility(
                    keyword, pType, pBed, pBath, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndGarageAndVisibility(
                    keyword, pType, pStatus, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBathsAndVisibility(
                    keyword, pType, pStatus, pBath, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndBedsAndVisibility(
                    keyword, pType, pStatus, pBed, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndBathsAndLocationAndGarageAndVisibility(
                    keyword, pBath, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            Bedroom pBed = bedroomService.findByNumber(beds);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndBedsAndLocationAndGarageAndVisibility(
                    keyword, pBed, city, pGarage, Visibility.VISIBLE
            );

        } else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndBedsAndBathsAndLocationAndVisibility(
                    keyword, pBed, pBath, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndLocationAndGarageAndVisibility(
                    keyword, pStatus, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBathsAndLocationAndVisibility(
                    keyword, pStatus, pBath, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBedsAndLocationAndVisibility(
                    keyword, pStatus, pBed, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndLocationAndGarageAndVisibility(
                    keyword, pType, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBathsAndLocationAndVisibility(
                    keyword, pType, pBath, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bedroom pBed = bedroomService.findByNumber(beds);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBedsAndLocationAndVisibility(
                    keyword, pType, pBed, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndLocationAndVisibility(
                    keyword, pType, pStatus, city, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY) &&
                !garages.equals(ANY)
        ){
            Bathroom pBath = bathroomService.findByNumber(baths);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndBathsAndGarageAndVisibility(
                    keyword, pBath, pGarage, Visibility.VISIBLE
            );
        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            Bedroom pBed = bedroomService.findByNumber(beds);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndBedsAndGarageAndVisibility(
                    keyword, pBed, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            Bedroom pBed = bedroomService.findByNumber(beds);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndBedsAndBathsAndVisibility(
                    keyword, pBed, pBath, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndGarageAndVisibility(
                    keyword, pStatus, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBathsAndVisibility(
                    keyword, pStatus, pBath, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY) &&
                !beds.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            Bedroom pBed = bedroomService.findByNumber(beds);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndBedsAndVisibility(
                    keyword, pStatus, pBed, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndGarageAndVisibility(
                    keyword, pType, pGarage, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBathsAndVisibility(
                    keyword, pType, pBath, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            Bedroom pBed = bedroomService.findByNumber(beds);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndBedsAndVisibility(
                    keyword, pType, pBed, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                !type.equals(ANY) &&
                !status.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            PropertyStatus pStatus = statusService.findByName(status);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndStatusAndVisibility(
                    keyword, pType, pStatus, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ) {
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndLocationAndGarageAndVisibility(
                    keyword, city, pGarage, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndBathsAndLocationAndVisibility(
                    keyword, pBath, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY)
        ) {
            Bedroom pBed = bedroomService.findByNumber(beds);
            return propertyRepository.findAllByPropertyTitleContainingAndBedsAndLocationAndVisibility(
                    keyword, pBed, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndLocationAndVisibility(
                    keyword, pStatus, city, Visibility.VISIBLE
            );

        }else if (
                !city.equals(ANY) &&
                !type.equals(ANY) &&
                status.equals(ANY)
        ) {
            PropertyType pType = typeService.findByName(type);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndLocationAndVisibility(
                    keyword, pType, city, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                baths.equals(ANY) &&
                !garages.equals(ANY)
        ){
            Garage pGarage = garageService.findByNumber(garages);
            return propertyRepository.findAllByPropertyTitleContainingAndGarageAndVisibility(
                    keyword, pGarage, Visibility.VISIBLE
            );
        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                beds.equals(ANY) &&
                !baths.equals(ANY)
        ) {
            Bathroom pBath = bathroomService.findByNumber(baths);
            return propertyRepository.findAllByPropertyTitleContainingAndBathsAndVisibility(
                    keyword, pBath, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                status.equals(ANY) &&
                !beds.equals(ANY)
        ) {
            Bedroom pBed = bedroomService.findByNumber(beds);
            return propertyRepository.findAllByPropertyTitleContainingAndBedsAndVisibility(
                    keyword, pBed, Visibility.VISIBLE
            );

        }else if (
                city.equals(ANY) &&
                type.equals(ANY) &&
                !status.equals(ANY)
        ) {
            PropertyStatus pStatus = statusService.findByName(status);
            return propertyRepository.findAllByPropertyTitleContainingAndStatusAndVisibility(
                    keyword, pStatus, Visibility.VISIBLE
            );

        }else if (city.equals(ANY) && !type.equals(ANY)) {
            PropertyType pType = typeService.findByName(type);
            return propertyRepository.findAllByPropertyTitleContainingAndTypeAndVisibility(keyword, pType, Visibility.VISIBLE);
        }else if (!city.equals(ANY) && type.equals(ANY)) {

            return propertyRepository.findAllByPropertyTitleContainingAndLocationAndVisibility(keyword, city, Visibility.VISIBLE);

        }else if (city.equals(ANY)){
            return propertyRepository.findAllByPropertyTitleContainingAndVisibility(keyword, Visibility.VISIBLE);
        }

        return null;
    }

    public Property saveOrUpdate(Property property) {
        List<String> errors = PropertyValidator.validate(property);
        if (!errors.isEmpty()) {
            log.error("Property is not valid {}", property);
            throw new InvalidEntityException("Property is not valid", ErrorCodes.PROPERTY_NOT_VALID, errors);
        }

        if (property.getId() == null) return this.save(property);
        return this.update(property);
    }
}
