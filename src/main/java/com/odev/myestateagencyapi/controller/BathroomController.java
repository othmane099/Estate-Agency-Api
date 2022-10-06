package com.odev.myestateagencyapi.controller;

import com.odev.myestateagencyapi.model.Bathroom;
import com.odev.myestateagencyapi.service.BathroomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class BathroomController {

    private final BathroomService bathroomService;

    @GetMapping("/bathrooms")
    public List<Bathroom> bathrooms(){
        return bathroomService.findAll();
    }

    @PostMapping("/admin/bathroom/save")
    public Bathroom save(@RequestBody Bathroom bathroom){
        return bathroomService.save(bathroom);
    }

    @PostMapping("/admin/bathroom/delete")
    public void delete(@RequestParam Long id){
        bathroomService.deleteById(id);
    }

    @GetMapping("/bathroom")
    public Bathroom bathroom(@RequestParam("bid") Long bathroomId){
        return bathroomService.findById(bathroomId);
    }
}
