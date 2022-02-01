//package de.northcodes.course.jsfspring.bean;
//
//import javax.annotation.ManagedBean;
//import javax.faces.bean.SessionScoped;
//
//import de.northcodes.course.jsfspring.model.Uebung;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import de.northcodes.course.jsfspring.model.PlanItem;
//import de.northcodes.course.jsfspring.model.Meilenstein;
//import de.northcodes.course.jsfspring.service.MeilensteinService;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@SessionScoped
//@Component
//@ManagedBean
//public class ShoppingCart implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    private final List<Meilenstein> items = new ArrayList<>();
//
//    private int totalQuantity = 0;
//
//    private BigDecimal totalAmount = BigDecimal.ZERO;
//
//    @Autowired
//    private UserManager userManager;
//
//    @Autowired
//    MeilensteinService orderService;
//
//    public List<Meilenstein> getItems() {
//        return items;
//    }
//
//    public int getTotalQuantity() {
//        return totalQuantity;
//    }
//
//    public BigDecimal getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void addProduct(Uebung excercise) {
//
//    }
//
//    public void removeProduct(Uebung excercise) {
//
//    }
//
//    private void incrementTotalQuantityAndAmount(BigDecimal productPrice) {
//        totalQuantity++;
//        totalAmount = totalAmount.add(productPrice);
//    }
//
//    private void decrementTotalQuantityAndAmount(BigDecimal productPrice) {
//        totalQuantity--;
//        totalAmount = totalAmount.subtract(productPrice);
//    }
//
//    public void orderNow() {
//
//
//    }
//
//    public List<PlanItem> getAllOrders() {
//        return null;
//    }
//}
