package com.example.jpashop;

import com.example.jpashop.domain.Address;
import com.example.jpashop.domain.Delivery;
import com.example.jpashop.domain.Member;
import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.OrderItem;
import com.example.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager entityManager;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            entityManager.persist(member);

            Book book1 = createItem("JPA1 BOOK", 10000, 100);
            entityManager.persist(book1);

            Book book2 = createItem("JPA2 BOOK", 20000,100);
            entityManager.persist(book2);

            OrderItem ordedItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem ordedItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, ordedItem1, ordedItem2);
            entityManager.persist(order);

        }

        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            entityManager.persist(member);

            Book book1 = createItem("SPRING1 BOOK", 20000, 200);
            entityManager.persist(book1);

            Book book2 = createItem("SPRING2 BOOK", 40000,300);
            entityManager.persist(book2);

            OrderItem ordedItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem ordedItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, ordedItem1, ordedItem2);
            entityManager.persist(order);

        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createItem(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address(name, street, zipcode));
            return member;
        }
    }
}
