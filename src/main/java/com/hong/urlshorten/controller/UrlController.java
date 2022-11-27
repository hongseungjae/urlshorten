package com.hong.urlshorten.controller;

import com.hong.urlshorten.domain.UrlCountForm;
import com.hong.urlshorten.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;


import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@Slf4j
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(value = "/", method = GET)
    public String main() {
        return "hello";
    }

   @RequestMapping(value = "/{path}", method = GET)
    public ResponseEntity<?> urlRedirect(@PathVariable String path) {
        String redirectUrl = urlService.Redirect(path);
        return originRedirectResponse(redirectUrl);

    }

    @RequestMapping(value = "/{path}/count", method = GET)
    @ResponseBody
    public UrlCountForm urlCount(@PathVariable String path) {
        return new UrlCountForm(urlService.getRequestCount(path));
    }

    private static ResponseEntity<Object> originRedirectResponse(String userUrl) {
        log.info("userUrl={}", userUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(userUrl));
        CacheControl cacheControl = CacheControl.noStore();
        headers.setCacheControl(cacheControl);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
