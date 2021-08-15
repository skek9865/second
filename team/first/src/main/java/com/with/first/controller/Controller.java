package com.with.first.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
@Log4j2
public class Controller {

    @GetMapping("/login/index")
    public void get(){

        log.info("hello");
    }
}
