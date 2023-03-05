package com.example.springjpaalone.member.service;


import com.example.springjpaalone.member.entity.*;
import com.example.springjpaalone.member.repository.ItemRepository;
import com.example.springjpaalone.member.repository.MemberRepository;
import com.example.springjpaalone.member.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;




    @Transactional
    public Long order(Long memberId, Long ItemId, int count){
        Member findedMember = memberRepository.findOne(memberId);
        Item findItem = itemRepository.findOne(ItemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(findedMember.getAddress());
        delivery.setStatus(Status.ORDER);

        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);

        Order madeOrder=Order.createOrder(findedMember, delivery, orderItem);

        orderRepository.save(madeOrder);
        return madeOrder.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {

        Order findOrder = orderRepository.findOne(orderId);
        findOrder.cancel();
    }

//    public List<Order> findOrders(OrderSearch orderSearch) {
//        orderRepository.findAll(orderSearch);
//    }

}
