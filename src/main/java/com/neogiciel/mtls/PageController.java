package com.neogiciel.mtls;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.core.io.Resource;


import java.security.cert.X509Certificate;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Collections;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class PageController {

     /*
    *Logger
    */
    static Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Autowired
	private RestTemplate restTemplate;

    //@Value("classpath:certificate.crt")
    //private Resource resource;

    @Value("${spring.application.name:spring-test-ssl}")
    String applicationName;


    @GetMapping("/")
    public String index(Model model) {
        LOGGER.info("[PageController]***************Home*******************");
        LOGGER.info("[PageController]applicationName = "+applicationName);

        return "index.html";
    }
    
    @PostMapping(value = "/test")
    //@GetMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    //@RequestMapping(value = "/addmetierb", method = RequestMethod.POST)
    public String test(Model model, @RequestParam(value = "url", required = false) String url) {

        LOGGER.info("[PageController]***************Test*******************");
        LOGGER.info("[PageController] url = " + url);
        //Affiche des données
        //displayMetierActivite(model,id);
 
        try{

            // create headers
            HttpHeaders headers = new HttpHeaders();
            // set `Content-Type` and `Accept` headers
            //headers.setContentType(MediaType.APPLICATION_JSON);
           // headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            // example of custom header
            headers.set("application", "java");
            //headers.set("source", "test8.neogicie.net");

            // build the request
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,request,String.class,1);

            
            LOGGER.info("[PageController] response = " + response);
            model.addAttribute("response", response);
            model.addAttribute("erreur", "OK");

        }catch (Exception e){
            LOGGER.info("[PageController] erreur = " + e.getMessage());
            model.addAttribute("erreur", e.getMessage());

        }

        return "index.html";

    }


    /*
     * getFileContent
    */
    public String  getFileContent( FileInputStream fis ) {

        try{

            StringBuilder sb = new StringBuilder();
            Reader r = new InputStreamReader(fis, "UTF-8");  //or whatever encoding
            int ch = r.read();
            while(ch >= 0) {
                sb.append(ch);
                ch = r.read();
            }
            return sb.toString();
        }catch (Exception e){
            LOGGER.info("[PageController] getFileContent = " + e.getMessage());
            return null;
        }

    }
}
