package testing;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Test cases for Cart")
public class CartTest {

    @Test
    @DisplayName("Cart is able to process 1000 orders i 2 ms")
    void simulateLargeOrder(){
        //given
        Cart cart = new Cart();

        //when

        //then
        assertTimeout(Duration.ofMillis(2), cart::simulateLargeOrder);
        //czy nasza metoda wykona się w czasie T<2ms (mi przeszło)
    }

    @Disabled // ignoruje ponizszy test
    @Test
    void simulateLargeOrder2(){
        //given
        Cart cart = new Cart();

        //when

        //then
        assertTimeout(Duration.ofMillis(1), cart::simulateLargeOrder);
    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart(){

        //given
        Order order = new Order();
        Cart cart = new Cart();

        //when
        cart.addOrderToCart(order);

        //then
        //Matchery do łączenia assercji z allOf() - > musi być wszystko spełnione
//        , z anyOf - > chociaż 1 warunek musi byc spełniony
        assertThat(cart.getOrders(), anyOf(
                notNullValue(),
                hasSize(0),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        //Wypisze tylko pierwszy niespełniony warunek
        assertThat(cart.getOrders(), allOf(
            notNullValue(),
            hasSize(1),
            is(not(empty())),
            is(not(emptyCollectionOf(Order.class)))
        ));

        //Wypisze wszystkie nie spełnione warunki
        assertAll( "This is a group of assertions for cart",
                //przyjmuje Executable
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders(), is(not(empty()))),
                () -> assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class)))),
                //assertAll pozwala na kod wielolinijkowy
                () -> {
                    List<Meal> mealList = cart.getOrders().get(0).getMeals();
                    assertThat(mealList, empty());
                }
        );
    }
}
