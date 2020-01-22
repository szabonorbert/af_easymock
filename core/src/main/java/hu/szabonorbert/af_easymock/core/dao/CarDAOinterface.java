package hu.szabonorbert.af_easymock.core.dao;
import hu.szabonorbert.af_easymock.core.model.Car;

import java.util.LinkedList;

public interface CarDAOinterface {

    void createCar(Car car);
    Car readCar(int id);
    LinkedList<Car> readAllCars();
    void deleteCar(int id);
    void updateCar(Car car);

}
