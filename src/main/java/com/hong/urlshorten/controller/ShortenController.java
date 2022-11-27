package com.hong.urlshorten.controller;

import com.hong.urlshorten.domain.ShotrenUrlForm;
import com.hong.urlshorten.service.ShotenService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
public class ShortenController {

    private final ShotenService shotenService;

    @Autowired
    public ShortenController(ShotenService shotenService) {
        this.shotenService = shotenService;
    }


    @RequestMapping(value = "/urlConvert", method = POST)
    public ShotrenUrlForm urlConvert(@RequestParam String url) {
        return new ShotrenUrlForm(shotenService.shorten(url));
    }

}
