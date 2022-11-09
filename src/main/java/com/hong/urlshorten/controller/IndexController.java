package com.hong.urlshorten.controller;

import com.hong.urlshorten.dto.UrlDTO;
import com.hong.urlshorten.repositories.IStorage;
import com.hong.urlshorten.repositories.ListStorage;
import com.hong.urlshorten.utill.Base62;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;


import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IndexController {

    static IStorage<UrlDTO> listStorage = new ListStorage<>();
    static Base62 base62 = Base62.createInstance();

   // static IStorage iStorage = new ListStorage();


    @RequestMapping(value = "/hello/url", method = GET)
    public String main(HttpServletRequest request, Model model) {
        System.out.println("index");


        return "hello";
    }
}
