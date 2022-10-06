package com.odev.myestateagencyapi.controller;

import com.odev.myestateagencyapi.model.City;
import com.odev.myestateagencyapi.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class CityController {

    private final CityService cityService;

    @GetMapping("/cities")
    public List<City> cities(){
        return cityService.findAll();
    }

    @PostMapping("/admin/city/save")
    public City save(@RequestBody City city){
        return cityService.save(city);
    }

    @PostMapping("/admin/city/delete")
    public void delete(@RequestParam Long id){
        cityService.deleteById(id);
    }
}
