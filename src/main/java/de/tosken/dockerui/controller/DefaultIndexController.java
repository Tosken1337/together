package de.tosken.dockerui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 11:26
 */
@Controller
public class DefaultIndexController {

    @RequestMapping("/")
    public String index() {
        return "index.xhtml";
    }
}
