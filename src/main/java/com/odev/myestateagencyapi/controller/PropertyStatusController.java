package com.odev.myestateagencyapi.controller;

import com.odev.myestateagencyapi.model.PropertyStatus;
import com.odev.myestateagencyapi.service.PropertyStatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class PropertyStatusController {

    private final PropertyStatusService statusService;

    @GetMapping("/statuses")
    public List<PropertyStatus> statuses(){
        return statusService.findAll();
    }

    @PostMapping("/admin/status/save")
    public PropertyStatus save(@RequestBody PropertyStatus status){
        return statusService.save(status);
    }

    @PostMapping("/admin/status/delete")
    public void delete(@RequestParam Long id){
        statusService.deleteById(id);
    }
}
