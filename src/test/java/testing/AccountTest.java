package testing;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation(){
        //given
        Account newAccount = new Account();
        //then
        assertFalse(newAccount.isActive(),"Check if new account is not active");
        //Matchers - odpowiedniki assercji
        assertThat(newAccount.isActive(), equalTo(false));
        assertThat(newAccount.isActive(), is(false));

    }

    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();
        //when
        assertFalse(newAccount.isActive());
        //then
        newAccount.activate();
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive(), equalTo(true)); //Matcher dla =True
    }

    //Testowanie null - czy dany obiekt przyjnuje nulla
    @Test
    void newlyCreatedAccountShouldNotHavenDefaultDeliveryAddresSet(){
        //given
        Account account = new Account();

        //When
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address, nullValue());//Matcher null
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){
        //given
        Address address = new Address("Krakowska", "67c");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddres = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultAddres);
        assertThat(defaultAddres, is(notNullValue()));
    }

    //Test wykonany zostanie 5 razy
    @RepeatedTest(5)
    //Wykorzystanie asumpcji
    void newAccountWithNotNullAddressShouldBeActive(){
        //given
        Address address = new Address("Puławska", "46/6");
        //when
        Account account = new Account(address);
        //then
        assumingThat(address != null, ()->{
            assertTrue(account.isActive());
        });
    }

}
