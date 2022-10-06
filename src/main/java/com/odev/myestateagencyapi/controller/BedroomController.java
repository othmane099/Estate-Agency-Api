package com.odev.myestateagencyapi.controller;

import com.odev.myestateagencyapi.model.Bedroom;
import com.odev.myestateagencyapi.service.BedroomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class BedroomController {

    private final BedroomService bedroomService;

    @GetMapping("/bedrooms")
    public List<Bedroom> bedrooms(){
        return bedroomService.findAll();
    }

    @PostMapping("/admin/bedroom/save")
    public Bedroom save(@RequestBody Bedroom bedroom){
        return bedroomService.save(bedroom);
    }

    @PostMapping("/admin/bedroom/delete")
    public void delete(@RequestParam Long id){
        bedroomService.deleteById(id);
    }
}
