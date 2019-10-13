package testing.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Unit {

    private static Random random = new Random();

    private Coordinates coordinates;
    private int fuel;
    private int maxFuel;
    private List<Cargo> cargo;
    private int maxCargoWeight;
    private int currentCargoWeight;

    Unit(Coordinates startCoordinates, int maxFuel, int maxCargoWeight){
        this.coordinates = startCoordinates;
        this.maxFuel = maxFuel;
        this.fuel = maxFuel;
        this.maxCargoWeight = maxCargoWeight;
        this.currentCargoWeight = 0;
        this.cargo = new ArrayList<>();
    }

    Coordinates move(int x, int y){
        if (this.fuel - (x+y)<0){
            throw new IllegalStateException("Unit cannot move that far");
        }

        Coordinates coordinatesAfterMove = Coordinates.copy(this.coordinates, x, y);
        this.coordinates = coordinatesAfterMove;

        this.fuel = this.fuel - (x+y);

        return coordinatesAfterMove;
    }

    void tankUp(){
        int restPoints = random.nextInt(10);

        if (restPoints + this.fuel >= maxFuel){
            this.fuel = maxFuel;
        }else {
            this.fuel += restPoints;
        }
    }
    
}
