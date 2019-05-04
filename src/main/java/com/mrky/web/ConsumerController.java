package com.mrky.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mrky.domain.Consumer;
import com.mrky.domain.Order;
import com.mrky.repository.ConsumerRepository;
import com.mrky.exception.*;

import com.mrky.service.ConsumerServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerServiceImpl; // create, reader, update, delete

    // 注册
    @RequestMapping(path = "/consumers/registry", method = RequestMethod.POST)
    public String addNewConsumer(@RequestParam Integer id, @RequestParam String password,
            @RequestParam String address) {

        return null;
    }

    // TODO
    @RequestMapping(path = "/consumer/update", method = RequestMethod.POST)
    public String updateConsumer(@RequestParam Integer id, @RequestParam String password,
            @RequestParam String address) {

        return null;
    }

    @RequestMapping(path = "/consumer/delete", method = RequestMethod.GET)
    public void deleteConsumer(@RequestParam Integer id) {

    }

    @RequestMapping(path = "/consumer/order", method = RequestMethod.GET)
    public List<Order> findOrder(@RequestParam Integer id) {

        return null;
    }

}