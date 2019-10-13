package testing.homework1;

public class Calculator {
    private int a;
    private int b;
    private int c;
    private double d;

    public Calculator() {
    }


    public int addition(int a, int b){
        c = a + b;
        return c;
    }

    public int subtraction(int a, int b){
        c = a - b;
        return c;
    }

    public int multiplication(int a, int b){
        if (a>0&&b>0) {
            c = a * b;
            return c;
        }else {
            throw new IllegalArgumentException("Arguments can't equals zero");
        }
    }

    public double division(int a, int b){
        if(a>0&&b>0){
            c = a/b;
            d = a%d;
            return c+d;
        }
        throw new IllegalArgumentException("Arguments can't equals zero");
    }
}
