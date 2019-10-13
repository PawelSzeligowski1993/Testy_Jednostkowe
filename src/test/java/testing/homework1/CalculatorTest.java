package testing.homework1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    @BeforeAll
    static void setup(){
        System.out.println("Calculator Test Class");
    }

    @Test
    void addition() {
        //Given
        Calculator calculator = new Calculator();
        //When
        //Then
        assertThat(calculator.addition(10,2), equalTo(12));
        assertThat(calculator.addition(18,5), not(equalTo(22)));
    }

    @Test
    void subtraction() {
        //Given
        Calculator calculator = new Calculator();
        //When
        //Then
        assertThat(calculator.subtraction(10,2), equalTo(8));
        assertThat(calculator.subtraction(10,2), not(equalTo(9)));
    }

    @Test
    void multiplication() {
        //Given
        Calculator calculator = new Calculator();
        //When
        //Then
        assertThat(calculator.multiplication(10,9), equalTo(90));
        assertThat(calculator.multiplication(10,9), not(equalTo(91)));
    }

    @Test
    void methodMultiplicationShouldFailAbyArgumentsEqual0() {
        //Given
        Calculator calculator = new Calculator();
        //When
        //Then
        assertThrows(IllegalArgumentException.class, ()->calculator.multiplication(10,0));

    }

    @Test
    void division() {
        //Given
        Calculator calculator = new Calculator();
        //When
        //Then
        assertThat(calculator.division(100,3), equalTo(33%NaN));
        assertThat(calculator.division(10,2), not(equalTo(7)));
    }

    @Test
    void methodDivisionShouldFailAbyArgumentsEqual0(){
        //Given
        Calculator calculator = new Calculator();
        //When
        //Then
        assertThrows(IllegalArgumentException.class, ()->calculator.division(10,0));
    }

    @AfterAll
    static void end(){
        System.out.println("Test completed successfully");
    }
}