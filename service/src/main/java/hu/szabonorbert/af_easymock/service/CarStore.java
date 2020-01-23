package hu.szabonorbert.af_easymock.service;
import hu.szabonorbert.af_easymock.dao.CarDAO;
import hu.szabonorbert.af_easymock.core.model.Car;

import java.util.LinkedList;

public class CarStore {

    CarDAO garage;

    public CarStore(CarDAO cd){
        this.garage = cd;
    }

    public CarDAO getGarage() {
        return garage;
    }

    public void setGarage(CarDAO garage) {
        this.garage = garage;
    }

    public int addCar(Car car){
        return garage.createCar(car);
    }

    public boolean removeCar(Car car){
        return garage.deleteCar(car.getId());
    }

    public LinkedList<Car> listCarsByColor(String color){
        LinkedList<Car> allCars = garage.readAllCars();
        LinkedList<Car> ret = new LinkedList<Car>();
        for(Car c: allCars){
            if (c.getColor().equals(color)) ret.add(c);
        }
        return ret;
    }

    public boolean recolorCar(int id, String color){
        Car car = garage.readCar(id);
        if (car == null || car.getColor().equals(color)) return false;
        car.setColor(color);
        garage.updateCar(car);
        return true;
    }

    public Car getOldestCar(){
        LinkedList<Car> allCars = garage.readAllCars();
        Car ret = null;
        int max_year = -1;
        for(Car c: allCars){
            if (c.getYear()>max_year){
                ret = c;
                max_year = c.getYear();
            }
        }
        return ret;
    }
}
