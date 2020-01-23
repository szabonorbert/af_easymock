package hu.szabonorbert.af_easymock.controller;
import hu.szabonorbert.af_easymock.core.model.Car;
import hu.szabonorbert.af_easymock.service.CarStore;

import java.util.LinkedList;

public class CarSalesman {
    Car workCar;
    CarStore store;

    public CarSalesman(CarStore store){
        this.store = store;
        workCar = null;
    }

    public Car getWorkCar() {
        return workCar;
    }

    public void setWorkCar(Car workCar) {
        this.workCar = workCar;
    }

    public CarStore getStore() {
        return store;
    }

    public void setStore(CarStore store) {
        this.store = store;
    }

    public String getOldestCarInStore(){
        return store.getOldestCar().toString();
    }

    public String reColorCar(int id, String color){
        if(store.recolorCar(id, color)){
            return "car " + id + " successfully recolored";
        }
        return "car " + id + " failed to recolor";
    }

    public String placeCarInGarage(Car car){
        car.setId(0);
        int newid = store.addCar(car);
        return "Car added to garage with id " + newid;
    }
    public String removeCarFromGarage(Car car){
        if (store.removeCar(car)){
            return "Successfully removed: " +car.toString();
        }
        return "Failed to remove " + car.toString();
    }

    public String lookAtCarById(int id){
        return store.getCarCopyById(id).toString();
    }

    public String listCarsByColor(String color){
        LinkedList<Car> cars = store.listCarsByColor(color);
        String ret = new String();
        for (Car c: cars){
            ret += c.toString();
        }
        return ret;
    }

    public String listCars(){
        LinkedList<Car> cars = store.listCars();
        String ret = new String();
        for (Car c: cars){
            ret += c.toString();
        }
        return ret;
    }

    public Car touchCarById(int id){
        return store.getCarCopyById(id);
    }
}
