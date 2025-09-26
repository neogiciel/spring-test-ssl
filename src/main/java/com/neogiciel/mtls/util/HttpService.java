package com.neogiciel.mtls.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;


public class HttpService {
    

    static public ResponseEntity<String> get(String uri){

        
        RestTemplate restTemplate=new RestTemplate();

        //you can create and edit header
        //HttpHeaders header= new HttpHeaders();
        //header.add("Accept", "application/json");
        
        ResponseEntity<String> response= restTemplate.getForEntity(uri, String.class);

        return response;
    }



}
