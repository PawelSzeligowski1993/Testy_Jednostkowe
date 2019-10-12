package testing;

public class Account {

    private boolean active;
    private Address defaultDeliveryAddress;

    public Account(){
        this.active = false;
    }

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        //jesli domyslny adres != null konto active
        if(defaultDeliveryAddress != null){
            activate();
        }else {
            this.active = false;
        }
    }

    public void activate(){
        this.active = true;
    }

    public boolean isActive(){
        return this.active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }
}
