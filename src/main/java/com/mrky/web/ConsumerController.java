package com.mrky.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mrky.domain.Consumer;
import com.mrky.domain.Order;
import com.mrky.repository.ConsumerRepository;
import com.mrky.exception.*;

import com.mrky.service.ConsumerServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/app")
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerServiceImpl; // create, reader, update, delete

    @RequestMapping(path = "/consumer/add", method = RequestMethod.POST)
    public @ResponseBody String addNewConsumer(@RequestParam Integer id, @RequestParam String password,
            @RequestParam String address) {

        Consumer c = new Consumer(id, password, address);
        consumerServiceImpl.addConsumer(c);
        return "Saved " + c.toString();
    }

    // TODO
    @RequestMapping(path = "/consumer/update", method = RequestMethod.POST)
    public @ResponseBody String updateConsumer(@RequestParam Integer id, @RequestParam String password,
            @RequestParam String address) {
        // TODO spring security
        Consumer c = new Consumer(id, password, address);
        consumerServiceImpl.updateConsumer(c);
        return "Update" + c.toString();
    }

    @RequestMapping(path = "/consumer/delete", method = RequestMethod.GET)
    public @ResponseBody void deleteConsumer(@RequestParam Integer id) {
        consumerServiceImpl.deleteConsumer(id);
    }

    @RequestMapping(path = "/consumer/order", method = RequestMethod.GET)
    public @ResponseBody List<Order> findOrder(@RequestParam Integer id) {
        Consumer c = new Consumer();
        c.setId(id);
        return consumerServiceImpl.findOrder(c);
    }

}