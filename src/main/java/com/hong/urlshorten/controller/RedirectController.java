package com.hong.urlshorten.controller;

import com.hong.urlshorten.dto.UrlDTO;
import com.hong.urlshorten.service.DecodeUrl;
import com.hong.urlshorten.service.IndexUrl;
import com.hong.urlshorten.service.ParseUrl;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URI;


import static com.hong.urlshorten.controller.IndexController.listStorage;
import static com.hong.urlshorten.controller.IndexController.base62;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class RedirectController {
    @RequestMapping(value = "/*/", method = GET)
    public ResponseEntity<?> urlRedirect(HttpServletRequest request, Model model) {

        ParseUrl parseUrl = getParseUrl(request);     // http://localhost:8080/abcd/  -> abcd
        String indexDecoded = getIndexDecoded(parseUrl.getUrl());      // abcd -> 58

        IndexUrl indexUrl = new IndexUrl();
        //널,숫자, 범위 체크
        if(!indexUrl.integerCheck(indexDecoded)) return indexRedirectResponse();
        if(!indexUrl.nullCheck(indexDecoded)) return indexRedirectResponse();

        if(indexUrl.indexRangeCheck(indexDecoded, listStorage)){  // arr 범위내에 인덱스
                return originRedirectResponse(getOriginUrl(Integer.parseInt(indexDecoded)));
            }

        //그 외 매칭 안 될 경우 인덱스 페이지
        return indexRedirectResponse();
    }


    @RequestMapping(value = "/*/count", method = GET)
    @ResponseBody
    public UrlDTO count(HttpServletRequest request, HttpServletResponse response) {

        ParseUrl parseUrl = getCountParseUrl(request);
        String indexDecoded = getIndexDecoded(parseUrl.getUrl());      // abcd -> 58

        IndexUrl indexUrl = new IndexUrl();
        //널,숫자, 범위 체크
        if(!indexUrl.integerCheck(indexDecoded)) return null;
        if(!indexUrl.nullCheck(indexDecoded)) return null;

        if(indexUrl.indexRangeCheck(indexDecoded, listStorage)){  // arr 범위내에 인덱스
            UrlDTO urlDto = listStorage.get(Integer.parseInt(indexDecoded));
            return urlDto;
        }

        return null;
    }

    private static ParseUrl getCountParseUrl(HttpServletRequest request) {
        ParseUrl parseUrl = new ParseUrl(request);
        parseUrl.pathExtract();  // http://localhost:8080/abcd/ -> /abcd/count
        parseUrl.pathCountSplit();    // /abcd/count -> abcd
        return parseUrl;
    }


    private static String getIndexDecoded(String parseUrl) {
        DecodeUrl decodeUrl = new DecodeUrl();
        String indexDecoded = decodeUrl.urlToIndex(parseUrl,base62); // abcd -> 58
        return indexDecoded;
    }

    private static ParseUrl getParseUrl(HttpServletRequest request) {
        ParseUrl parseUrl = new ParseUrl(request);
        parseUrl.pathExtract();  // http://localhost:8080/abcd/ -> /abcd/
        parseUrl.pathSplit();    // /abcd -> abcd
        return parseUrl;
    }

    private static String getOriginUrl(int index) {
        UrlDTO urlDto = listStorage.get(index);
        String userUrl = urlDto.getOriginUrl(); // 원본 url 매칭
        urlDto.setRequestCount(urlDto.getRequestCount()+1); // 카운팅
        return userUrl;
    }

    private static ResponseEntity<Object> indexRedirectResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/hello/url"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    private static ResponseEntity<Object> originRedirectResponse(String userUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(userUrl));
        CacheControl cacheControl = CacheControl.noStore();
        headers.setCacheControl(cacheControl);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
