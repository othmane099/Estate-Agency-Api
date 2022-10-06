package com.odev.myestateagencyapi.controller;


import com.odev.myestateagencyapi.dto.ContactUsDto;
import com.odev.myestateagencyapi.service.ContactUsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

import static com.odev.myestateagencyapi.util.Constants.CLIENT_BASE_URL;

@RestController
@AllArgsConstructor
@CrossOrigin(CLIENT_BASE_URL)
public class ContactUsController {

    private final ContactUsService contactUsFormService;

    @PostMapping("/contactus")
    public void contactForm(@RequestBody ContactUsDto contactUsForm) throws MessagingException, UnsupportedEncodingException {
        System.out.println(contactUsForm);
        contactUsFormService.sendMail(contactUsForm);
    }

}
