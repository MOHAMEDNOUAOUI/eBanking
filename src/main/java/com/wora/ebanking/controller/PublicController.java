package com.wora.ebanking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PublicController {
    @GetMapping("/notices")
    public String getNotices() {
        return "Notices";
    }

    @GetMapping("/contact")
    public String getContactInfo() {
        return "Contact Info";
    }
}
