package com.odev.myestateagencyapi.controller;

import com.odev.myestateagencyapi.model.Garage;
import com.odev.myestateagencyapi.service.GarageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class GarageController {

    private final GarageService garageService;

    @GetMapping("/garages")
    public List<Garage> garages(){
        return garageService.findAll();
    }

    @PostMapping("/admin/garage/save")
    public Garage save(@RequestBody Garage garage){
        return garageService.save(garage);
    }

    @PostMapping("/admin/garage/delete")
    public void delete(@RequestParam Long id){
        garageService.deleteById(id);
    }
}
