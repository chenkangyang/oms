package com.mrky.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrky.domain.Consumer;
import com.mrky.repository.ConsumerRepository;
import com.mrky.exception.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class ConsumerController {

    @Autowired
    private ConsumerRepository consumerRepository;

    @GetMapping(path = "/consumer/add")
    public @ResponseBody String addNewConsumer(@RequestParam Integer id, @RequestParam String password) {

        try {
            consumerRepository.findById(id);
        } catch (IllegalArgumentException e) {

        }

        Consumer t = new Consumer();
        t.setId(id);
        t.setPassword(password);
        consumerRepository.save(t);

        return "Saved " + t.toString();
    }

    @GetMapping(path = "/consumer/all")
    public @ResponseBody Iterable<Consumer> getAllUsers() {
        return consumerRepository.findAll();
    }

}