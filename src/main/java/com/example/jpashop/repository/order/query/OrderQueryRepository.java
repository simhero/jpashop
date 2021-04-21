package com.example.jpashop.repository.order.query;

import com.example.jpashop.domain.Address;
import com.example.jpashop.domain.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final EntityManager entityManager;

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();
        result.forEach(orderQueryDto -> {
            List<OrderItemQueryDto> orderItems = findORderItems(orderQueryDto.getOrderId());

        });
        return result;

    }

    private List<OrderItemQueryDto> findORderItems(Long orderId) {
        return entityManager.createQuery("select new com.example.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice,oi.count ) " +
                " from OrderItem oi" +
                " join oi.item i" +
                " where oi.order.id =:orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        return entityManager.createQuery(" select new com.example.jpashop.repository.order.query.OrderQueryDto(o.id,m.name,o.orderDate,o.orderStatus,d.address) " +
                " from Order o" +
                " join o.member m" +
                " join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }
}
