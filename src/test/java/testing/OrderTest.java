package testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OrderTest {

    private Order order;

    //Metoda która uruchomi się przed każdym testem
    //W tej metodzie wykonuje się inicjalizacja klasy order
    @BeforeEach
    void initializeOrder(){
        System.out.println("Inside @BeforeEach method");
        order = new Order();
    }

    @AfterEach
    void cleanUp(){
        System.out.println("Inside @AfterEach method");
        order.cancel();
    }

    @Test
    void testAssertArrayEquals() {
        System.out.println("Inside Test");

        //given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        //then
        assertArrayEquals(ints1, ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder(){
        //given

        //then
        assertThat(order.getMeals(), empty()); //Czy jest pusta?
        assertThat(order.getMeals().size(), equalTo(0));// Czy rozmiar = 0?
        assertThat(order.getMeals(), hasSize(0)); //Czy rozmiar =0?
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class)); //Czy pusta kolekcja?
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){
        //given
        Meal meal = new Meal(15, "Burger");
        Meal meal1 = new Meal(5, "Sandwich");
        //When
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));

        assertThat(order.getMeals().get(0).getPrice(), equalTo(15));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){

        //given
        Meal meal = new Meal(15, "Burger");

        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder(){
        //given
        Meal meal1 = new Meal (15,"Burger");
        Meal meal2 = new Meal(17,"Sandwich");

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(), contains(meal1,meal2));//contains(meal2,meal1) - nie przejdzie
        assertThat(order.getMeals(), containsInAnyOrder(meal2, meal1)); // dowolna kolejnosć w liscie
    }

    @Test
    void testIfTwoMealListsAreTheSame(){
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");
        Meal meal3 = new Meal(11, "Kebab");

        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);

        assertThat(meals1, is(meals2));
    }
}
