package testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class MealTest {

    // Wartosci prymitywne
    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(35);
        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);         //Porównanie wartości prymitywnej
        //Matcher
        assertThat(discountedPrice,equalTo(28));
    }

    //Porównanie obiektów - refernecje
    //The Same
    @Test
    void referencesToTheSameObjectShouldBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);
        //Matcher
        assertThat(meal1, sameInstance(meal2));
    }

    //Diffrent
    @Test
    void  referencesToDiffrentObjectShouldNotBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

        //then
        assertNotSame(meal1,meal2);
        //Matcher
        assertThat(meal1, not(sameInstance(meal2)));
    }

    //porównanie obiektów wzgledem siebie
    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){

        //given
        Meal meal1 = new Meal(10,"Pizza");
        Meal meal2 = new Meal(10,"Pizza");

        //then
        assertEquals(meal1,meal2, "Checking if two meals are equal");
    }

    @Test
    void exceptionShouldBeThrowIfDiscountIsHigherThanThePrice() {

        //given
        Meal meal = new Meal(8, "Soup");

        //when
        //then
        assertThrows(IllegalArgumentException.class, ()-> meal.getDiscountedPrice(49)); //Lambda interface bezargumentowego
                                                                                        //2 arg = executable - iterface bez argumentowy
    }
    //Test Parametryzowany
    //zaleta testów parametryzowanych to możliwość używania argumentów w tescie
    // poniżej inty (warotości) dla których test bedzie wykonany
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18})
    void mealPricesShouldBeLowerThan20(int prices){
        assertThat(prices, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }


    //Metoda źródło argumentów
    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheseburger", 12)
        );
    }

    //Test
    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name){
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }
    //Metoda źródło testu
    private static Stream<String> createCakeNames(){
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    //Przykładowy test dynamiczny
    @TestFactory
    Collection<DynamicTest> dynamicTestCollection(){
        return Arrays.asList(
                dynamicTest("Dynamic test 1", ()-> assertThat(5,lessThan(6))),
                dynamicTest("Dynamic test 2", ()-> assertEquals(4, 2*2))

        );
    }

    @Tag("fries")
    @TestFactory
    Collection<DynamicTest>calculateMealPrices(){
        Order order = new Order();
        order.addMealToOrder(new Meal (10, 2, "Hamburger"));
        order.addMealToOrder(new Meal (7, 4, "Fries"));
        order.addMealToOrder(new Meal (22, 3, "Pizza"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for (int i =0; i< order.getMeals().size(); i++){
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = ()->{
                assertThat(calculatePrice(price, quantity),lessThan(67));
            };
            String name = "Test name: " +i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;
    }

    private int calculatePrice(int price, int quantity){
        return price * quantity;
    }

}