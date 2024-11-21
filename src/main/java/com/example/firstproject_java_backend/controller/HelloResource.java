package com.example.firstproject_java_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.firstproject_java_backend.domain.Hello;
import com.example.firstproject_java_backend.domain.HelloRepository;

@RestController
public class HelloResource {

    private final HelloRepository helloRepository;

    public HelloResource(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @GetMapping("/hello")
    public String hello(String param ){
        return "Hello " + param;
    }

    @PostMapping("/hello")
    public HelloDto create(@RequestBody HelloDto helloDto){
        Hello hello = helloRepository.save(new Hello(helloDto.param()));
        return new HelloDto(hello.getId(), hello.getParam());
    }

    @GetMapping("/hello/{id}")
    public HelloDto get(@PathVariable Long id){
        Hello hello = helloRepository.findById(id).orElseThrow();
        return new HelloDto(hello.getId(), hello.getParam());
    }
}