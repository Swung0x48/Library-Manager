package com.swung0x48.librarymanager.controller;

import com.swung0x48.librarymanager.domain.LibUser;
import com.swung0x48.librarymanager.domain.Order;
import com.swung0x48.librarymanager.services.OrderService;
import com.swung0x48.librarymanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> allOrder() {
        return orderService.getAllOrder();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Integer deleteOrder(@PathVariable Integer id) {
        return orderService.deleteOrder(id);
    }

    @GetMapping(value = "/home")
    @ResponseStatus(HttpStatus.OK)
    public Integer adminOrderHome() {
        return orderService.countOrder();
    }


    @PostMapping("/lend")
    @ResponseStatus(HttpStatus.CREATED)
    public Order lendBook(HttpServletRequest httpRequest, @RequestBody Map<String, Integer> bookId) {
        return orderService.generateLendOrder(httpRequest, bookId.get("bookId"));
    }


    @PutMapping(value = "/return/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Order returnBook(@PathVariable Integer id) {
        return orderService.generateReturnOrder(id);
    }

    @GetMapping(value = "/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> userOrder(@PathVariable Integer userId) {
        return orderService.getMyOrders(userId);
    }

    @GetMapping(value = "/my")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> myOrder(HttpServletRequest httpRequest) {
        return orderService.getMyOrders((Integer) httpRequest.getAttribute("userId"));
    }
}
