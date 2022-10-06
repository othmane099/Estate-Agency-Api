package com.odev.myestateagencyapi.repository;

import com.odev.myestateagencyapi.model.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppSettingRepository extends JpaRepository<AppSetting, Long> {
}
