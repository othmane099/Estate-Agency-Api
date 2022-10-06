package com.odev.myestateagencyapi.service;

import com.odev.myestateagencyapi.exception.ErrorCodes;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import com.odev.myestateagencyapi.model.AppSetting;
import com.odev.myestateagencyapi.repository.AppSettingRepository;
import com.odev.myestateagencyapi.validator.AppSettingValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AppSettingService {

    private final AppSettingRepository appSettingRepository;

    public AppSetting save(AppSetting appSetting){
        List<String> errors = AppSettingValidator.validate(appSetting);
        if (!errors.isEmpty()) {
            log.error("AppSetting is not valid {}", appSetting);
            throw new InvalidEntityException("AppSetting is not valid", ErrorCodes.APP_SETTING_NOT_VALID, errors);
        }

        return appSettingRepository.save(appSetting);
    }

    public AppSetting getAppSetting(){
        return appSettingRepository.findAll().get(0);
    }

}
