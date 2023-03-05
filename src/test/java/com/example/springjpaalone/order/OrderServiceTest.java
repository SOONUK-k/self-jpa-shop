package com.example.springjpaalone.order;


import com.example.springjpaalone.member.entity.*;
import com.example.springjpaalone.member.exception.NotEnoughException;
import com.example.springjpaalone.member.repository.ItemRepository;
import com.example.springjpaalone.member.repository.MemberRepository;
import com.example.springjpaalone.member.repository.OrderRepository;
import com.example.springjpaalone.member.service.OrderService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;



    @DisplayName("오더생성테스트")
    @Test
    public void 오더생성테스트() {
        //given
        //새로운 오더를 만드는데 필요한 파라미터 생성
        //멤버, 아이템, count
        Member member = createMember();
        Item album = createAlbum();
        int count =5;

        //when
        //저장했을때
        Long orderId = orderService.order(member.getId(), album.getId(), count);

        //then
        Order findedOrder = orderRepository.findOne(orderId);

        assertEquals(findedOrder.getId(), orderId );
        assertEquals("상품 주문시 상태는 ORDER",Status.ORDER, findedOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.",1, findedOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 20000*5, findedOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",25, album.getStockQuantity());

    }

    @DisplayName("오더생성테스트but재고초과")
    @Test(expected = NotEnoughException.class)
    public void 오더생성테스트2() {
        Member member = createMember();
        Item album = createAlbum();
        int count = 40;
        orderService.order(member.getId(), album.getId(), count);

        fail("재고 수량 부족 예외 발생안함");

    }




    @DisplayName("오더취소테스트")
    @Test
    public void 오더취소테스트() {

        //Given : 오더 생성에 필요한
        Member member = createMember();
        Item album = createAlbum();
        int count =5;

        //when
        //저장했을때
        Long orderId = orderService.order(member.getId(), album.getId(), count);

        Order findedOrder = orderRepository.findOne(orderId);

        orderService.cancelOrder(findedOrder.getId());
        //given
        assertEquals("상품 주문시 상태는 ORDER",Status.CANCEL,
                findedOrder.getStatus());
        assertEquals("주문 수량 취소만큼 재고가 돌아와야 한다.",30, album.getStockQuantity());




    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원");
        member.setAddress(new Address("Seoul", "NearRiver", "123-123"));
        em.persist(member);
        return member;
    }
    private Item createAlbum() {
        Item album = new Album();
        album.setName("이문세");
        album.setPrice(20000);
        album.setStockQuantity(30);
        em.persist(album);
        return album;
    }

}
