package hu.szabonorbert.af_easymock.controller;
import hu.szabonorbert.af_easymock.core.model.Car;
import hu.szabonorbert.af_easymock.service.CarStore;

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
}
