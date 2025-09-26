package com.neogiciel.mtls;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class SslController {

     /*
    *Logger
    */
    static Logger LOGGER = LoggerFactory.getLogger(SslController.class);

    @RequestMapping(value = "/.well-known/pki-validation/48201C65AF5E4E312CF7E0658643D8F6.txt")
    public String verify(Model model) {
        LOGGER.info("[PageController]***************Verify SSL*******************");
        //return "/.well-known/pki-validation/48201C65AF5E4E312CF7E0658643D8F6.txt";
        return "F03301AFD03731B3C41E954C19A91F5AD3F1E234615A6F425A1C00B52DFFCE4E\ncomodoca.com\nb72b16034e429d2";
    }
    
    @RequestMapping(value = "/toto")
    public String handleGetRequest() {
        return "Handled GET request";
    }
    
}
