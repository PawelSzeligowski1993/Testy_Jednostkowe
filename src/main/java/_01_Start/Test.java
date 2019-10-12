package _01_Start;

public class Test {
    public static void main(String[] args) {
        testCalcAdd();
    }

    private static void testCalcAdd() {
        Calc calc = new Calc();

        int result = calc.sum(2, 2);

        if (result != 4){
            throw new IllegalStateException("Wrong result, 2+2 is not 4");
        }else {
            System.out.println("OK");
        }
    }
}
