package com.odev.myestateagencyapi.controller;

import com.odev.myestateagencyapi.model.AppSetting;
import com.odev.myestateagencyapi.service.AppSettingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class AppSettingController {

    private final AppSettingService appSettingService;

    @GetMapping("/setting")
    public AppSetting setting(){
        return appSettingService.getAppSetting();
    }

    @PostMapping("/admin/setting/save")
    public AppSetting save(@RequestBody AppSetting setting){
        return appSettingService.save(setting);
    }

}
