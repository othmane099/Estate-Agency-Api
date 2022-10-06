package com.odev.myestateagencyapi.dto;

import com.odev.myestateagencyapi.model.Picture;
import com.odev.myestateagencyapi.model.Property;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePictureDto {
    private Property property;
    private Picture picture;
}
