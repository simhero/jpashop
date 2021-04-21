package com.example.jpashop.repository;

import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);
    }

    public Order findOne(Long id) {
        return entityManager.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        return entityManager.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit) {
        return entityManager.createQuery(" select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();


    }

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return entityManager.createQuery(" select new com.example.jpashop.repository.OrderSimpleQueryDto(o.id,m.name,o.orderDate,o.orderStatus,d.address) from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();

    }


    public List<Order> findAllWithItem() {
        return entityManager.createQuery(" select distinct o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d" +
                " join fetch o.orderItems oi" +
                " join fetch oi.item i", Order.class)
                .getResultList();
    }

}
