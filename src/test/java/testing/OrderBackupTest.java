package testing;

import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OrderBackupTest {

    private static OrderBackup orderBackup;
    //Najpierw uruchamiana jest kolejno Metoda:
//   1) @BeforeAll - tylko raz przed wszystkimi testami - tu tworzy nowy plik
//   2) @BeforeEach - dopisuje w pliku informacje "New order"
//    Test - Tworzy nowe zamowienie zapisuje je do pliku
//   3) @AfterEach - "Wpisuje tekst "This is The End of the Order"
//   4) @AfterAll - zamyka plik

    @BeforeAll
    static void setup() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void appendAtTheStartOfTheLine() throws IOException{
        orderBackup.getWriter().append("New order");
    }

    @AfterEach
    void appendAtTheEndOfTheLine() throws IOException{
        orderBackup.getWriter().append("This is The End of the order");
    }

    @Tag("fries")
    @Test
    void backupOrderWithOneMeal() throws IOException{
        //given
        Meal meal = new Meal(7, "Fries");
        Order order = new Order();
        order.addMealToOrder(meal);
        //when
        orderBackup.backupOrder(order);
        //then
        System.out.println("Order: "+ order.toString() +" backed up.");
    }

    @AfterAll
    static void tearDown() throws IOException{
        orderBackup.closeFile();
    }
}
