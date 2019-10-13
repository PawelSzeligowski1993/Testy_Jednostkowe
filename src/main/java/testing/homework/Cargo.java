package testing.homework;

import java.util.Objects;

public class Cargo {
    private String name;
    private String weight;

    public Cargo(String name, String weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(name, cargo.name) &&
                Objects.equals(weight, cargo.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }
}
