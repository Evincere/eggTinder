package edu.egg.tinder.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Semper Evincere
 */
@Controller
public class PortalControlador {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

}
