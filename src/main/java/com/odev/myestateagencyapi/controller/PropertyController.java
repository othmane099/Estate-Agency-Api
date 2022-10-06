package com.odev.myestateagencyapi.controller;

import com.odev.myestateagencyapi.model.Property;
import com.odev.myestateagencyapi.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/properties")
    public List<Property> properties(){
        return propertyService.findAll();
    }

    @GetMapping("/properties_visible")
    public List<Property> visibleProperties(){
        return propertyService.findAllVisible();
    }

    @PostMapping("/admin/property/save")
    public Property save(@RequestBody Property property){
        return propertyService.saveOrUpdate(property);
    }

    @PostMapping("/admin/property/delete")
    public void delete(@RequestParam Long id){
        propertyService.deleteById(id);
    }

    @GetMapping("/property")
    public Property property(@RequestParam("pid") Long propertyId){
        return propertyService.findById(propertyId);
    }

    @PostMapping("/admin/property/visibility")
    public void updateVisibility(@RequestParam("propertyId") Long propertyId){
        propertyService.changeVisibility(propertyId);
    }

    @PostMapping("/admin/property/latest")
    public void updateLatest(@RequestParam("propertyId") Long propertyId){
        propertyService.updateLatest(propertyId);
    }

    @PostMapping("/admin/property/main")
    public void updateMain(@RequestParam("propertyId") Long propertyId){
        propertyService.updateMain(propertyId);
    }

    @GetMapping("/properties/s")
    public List<Property> search(
            @RequestParam("k") String keyword,
            @RequestParam("c") String city,
            @RequestParam("t") String type,
            @RequestParam("s") String status,
            @RequestParam("be") String beds,
            @RequestParam("ba") String baths,
            @RequestParam("g") String garages
    ){
        return  propertyService.search(keyword, city, type, status, beds, baths, garages);
    }
}
