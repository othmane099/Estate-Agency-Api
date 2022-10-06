package com.odev.myestateagencyapi.controller;

import com.odev.myestateagencyapi.model.PropertyType;
import com.odev.myestateagencyapi.service.PropertyTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class PropertyTypeController {

    private final PropertyTypeService typeService;

    @GetMapping("/types")
    public List<PropertyType> types(){
        return typeService.findAll();
    }

    @PostMapping("/admin/type/save")
    public PropertyType save(@RequestBody PropertyType type){
        return typeService.save(type);
    }

    @PostMapping("/admin/type/delete")
    public void delete(@RequestParam Long id){
        typeService.deleteById(id);
    }
}
