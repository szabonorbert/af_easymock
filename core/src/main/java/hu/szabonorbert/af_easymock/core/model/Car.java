package hu.szabonorbert.af_easymock.core.model;

import java.util.Objects;

public class Car {

    private int id;
    private int year;
    private String color;
    private String type;
    private String manufacturer;

    public Car(int id, int year, String color, String type, String manufacturer) {
        this.id = id;
        this.year = year;
        this.color = color;
        this.type = type;
        this.manufacturer = manufacturer;
    }

    public Car(int year, String color, String type, String manufacturer) {
        this.id = 0;
        this.year = year;
        this.color = color;
        this.type = type;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                year == car.year &&
                Objects.equals(color, car.color) &&
                Objects.equals(type, car.type) &&
                Objects.equals(manufacturer, car.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, color, type, manufacturer);
    }
}
