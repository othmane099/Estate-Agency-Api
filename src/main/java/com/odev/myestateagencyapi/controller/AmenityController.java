package com.odev.myestateagencyapi.controller;

import com.odev.myestateagencyapi.model.Amenity;
import com.odev.myestateagencyapi.service.AmenityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class AmenityController {

    private final AmenityService amenityService;

    @GetMapping("/amenities")
    public List<Amenity> amenities(){
        return amenityService.findAll();
    }

    @PostMapping("/admin/amenity/save")
    public Amenity save(@RequestBody Amenity amenity){
        return amenityService.save(amenity);
    }

    @PostMapping("/admin/amenity/delete")
    public void delete(@RequestParam Long id){
        amenityService.deleteById(id);
    }

    @GetMapping("/amenity")
    public Amenity amenity(@RequestParam("aid") Long amenityId){
        return amenityService.findById(amenityId);
    }

}
