package hu.szabonorbert.af_easymock.core.dao;
import hu.szabonorbert.af_easymock.core.model.Car;

import java.util.LinkedList;

public interface CarDAOinterface {

    int createCar(Car car);
    Car readCar(int id);
    LinkedList<Car> readAllCars();
    boolean deleteCar(int id);
    boolean updateCar(Car car);

}
