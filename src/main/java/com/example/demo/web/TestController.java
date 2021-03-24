package com.example.demo.web;

import com.example.demo.model.SetNameRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {


    @GetMapping(path = "/name")
    public String getName() {
        return "Kamil";
    }

    @PostMapping(value = "/name")
    public String setName(@RequestBody(required = true) SetNameRequest setNameRequest){
        return setNameRequest.getName();
    }
}
