package testing.homework;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinatesTest {

    @Test
    void constructorShouldFailIfAnyValueAbove100(){
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, ()-> new Coordinates(101,0));
    }

    @Test
    void constructorShouldFailAbyValueBelow0(){
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class, ()->new Coordinates(0,-1));
    }

    @Test
    void copy() {
        //given
        Coordinates coordinates =new Coordinates(10, 10);
        //when
        Coordinates copy = Coordinates.copy(coordinates, 4, 5);
        //then
        assertThat(copy.getX(), equalTo(14));
        assertThat(copy.getY(), equalTo(15));
    }
}