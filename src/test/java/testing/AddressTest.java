package testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class AddressTest {

    @ParameterizedTest
    //W środku tej adnotacji mozemy przekazywać zestawy wartości
    //do testowania - nie pojedyńcze wartości
    @CsvSource({"Fabryczna, 10","Armii Krajowej, 57/11","'Romka, Tomka, Atomka', 40"})
    void givenAddressesShouldNotBeEmptyAndProperNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
    }

    //Test który bedzie brał wartości z pliku CSV
    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void addressesFromFileShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
    }
}
