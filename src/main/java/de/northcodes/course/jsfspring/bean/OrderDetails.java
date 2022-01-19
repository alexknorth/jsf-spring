package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Order;
import de.northcodes.course.jsfspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@ManagedBean
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private OrderService orderService;

    private long orderId;

    private Order order;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void onload() {
        order = this.orderService.getOrderById(orderId);
    }

    public Order getOrder() {
        return order;
    }
}
