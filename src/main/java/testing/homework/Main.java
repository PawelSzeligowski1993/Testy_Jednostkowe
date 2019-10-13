package testing.homework;

public class Main {
    public static void main(String[] args) {
         Coordinates coordinates = new Coordinates(10, 2);

         System.out.println(coordinates);

        coordinates= coordinates.copy(coordinates,2,3);

        System.out.println(coordinates);

    }
}
