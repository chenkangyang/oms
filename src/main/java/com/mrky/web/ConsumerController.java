package com.mrky.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrky.domain.Consumer;
import com.mrky.repository.ConsumerRepository;
import com.mrky.exception.*;

import com.mrky.service.ConsumerService;

import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class ConsumerController {

    @Autowired
    private ConsumerRepository consumerRepository;

    @RequestMapping(path = "/consumer/add", method = RequestMethod.POST)
    public @ResponseBody String addNewConsumer(@RequestParam Integer id, @RequestParam String password,
            @RequestParam String address) {

        Consumer t = new Consumer();
        t.setId(id);
        t.setPassword(password);
        t.setAddress(address);
        consumerRepository.save(t);

        return "Saved " + t.toString();
    }

    // TODO
    @RequestMapping(path = "/consumer/update", method = RequestMethod.POST)
    public @ResponseBody String updateConsumerAdress(@RequestParam Integer id, @RequestParam String adress) {
        // TODO spring security
        return null;
    }

    @RequestMapping(path = "/consumer/delete", method = RequestMethod.GET)
    public @ResponseBody void deleteConsumer(@RequestParam Integer id) {
        consumerRepository.deleteById(id);
    }

    @RequestMapping(path = "/consumer/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Consumer> getAllUsers() {
        return consumerRepository.findAll();
    }

}