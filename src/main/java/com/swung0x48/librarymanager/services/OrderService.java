package com.swung0x48.librarymanager.services;

import com.swung0x48.librarymanager.domain.Book;
import com.swung0x48.librarymanager.domain.LibUser;
import com.swung0x48.librarymanager.domain.Order;
import com.swung0x48.librarymanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public List<Order> getAllOrder() {
        return orderRepository.selectAllOrder();
    }

    public Order generateLendOrder(HttpServletRequest httpRequest,Integer bookID) {
        Book book = bookService.lendBook(bookID);
        LibUser lender = userService.getLoginUser(httpRequest);
        Order order = new Order(book, lender);
        orderRepository.insertOrder(order);

        return order;
    }

    public Order generateReturnOrder(Integer orderId) {
        Order temp = new Order(orderId, new Date(), 1);
        orderRepository.updateOrder(temp);

        return orderRepository.selectOrderById(orderId);
    }

    public List<Order> getMyOrders(Integer userId) {
        return orderRepository.selectOrderByUserId(userId);
    }

    public Integer countOrder() {
        return orderRepository.selectAllOrder().size();
    }

    public Integer deleteOrder(Integer id) {
        orderRepository.deleteOrder(id);
        return id;
    }
}
