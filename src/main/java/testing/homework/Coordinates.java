package testing.homework;

import java.util.Objects;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        if (x<0||y<0){
            throw new IllegalArgumentException("Position can't bo less than 0");
        }
        if (x>100||y>100){
            throw new IllegalArgumentException("Position can't be bigger than 100");
        }
        this.x = x;
        this.y = y;
    }

    public static Coordinates copy (Coordinates coordinates, int x, int y ){
        return new  Coordinates(coordinates.x +x, coordinates.y + y);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
