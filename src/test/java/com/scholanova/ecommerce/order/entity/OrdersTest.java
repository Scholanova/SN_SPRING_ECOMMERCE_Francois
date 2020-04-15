package com.scholanova.ecommerce.order.entity;

import com.scholanova.ecommerce.cart.entity.Cart;
import com.scholanova.ecommerce.cart.exception.IllegalArgException;
import com.scholanova.ecommerce.cart.exception.NotAllowedException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrdersTest {

    @Test
    public void checkout_ShouldSetTheDateAndTimeOfTodayInTheOrder(){
        // given
        Orders order = new Orders();
        String instantExpected = "2014-12-22T10:15:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));
        Instant instant = Instant.now(clock);

        // when
        order.checkout();

        // then
        assertThat(instant.toString()).isEqualTo(order.getIssueDate());
    }


    @Test
    public void checkout_ShouldSetOrderStatusToPending(){
        // given
        Orders orders = new Orders();
        orders.createOrder();

        // when
        orders.checkout();

        // then
        assertThat(orders.getStatus().equals(OrderStatus.PENDING));
    }

    @Test
    public void checkout_ShouldThrowNotAllowedExceptionIfStatusIsClosed() throws NotAllowedException {
        // given
        Orders orders = new Orders();
        orders.createOrder();

        // when
        orders.setStatus(OrderStatus.CLOSED);

        // then
        assertThrows(NotAllowedException.class,() -> orders.checkout());
    }

    @Test
    public void checkout_ShouldThrowIllegalArgExceptionIfCartTotalItemsQuantityIsZERO() throws  IllegalArgumentException{
        //given
        Orders orders = new Orders();
        orders.createOrder();

        //when

        //then
        assertThrows(IllegalArgException.class,() -> orders.checkout());
    }

    @Test
    public void setCart_ShouldThrowNotAllowedExceptionIfStatusIsClosed(){

    }

    @Test
    public void createOrder_ShouldSetTheCartInTheOrder(){

    }

    @Test
    public void createOrder_ShouldSetStatusToCreated(){

    }

    @Test
    public void getDiscount_shouldReturnZEROIFCartTotalPriceIsLessThan100(){

    }

    @Test
    public void getDiscount_shouldReturn5percentIfCartTotalPriceIsMoreOrEqual100(){

    }

    @Test
    public void getOrderPrice_shouldReturnTotalPriceWithDiscount(){

    }

    @Test
    public void close_ShouldSetStatusToClose(){

    }

}