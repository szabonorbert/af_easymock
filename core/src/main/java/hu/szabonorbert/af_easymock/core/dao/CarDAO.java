package hu.szabonorbert.af_easymock.core.dao;
import hu.szabonorbert.af_easymock.core.model.Car;

public interface CarDAO {

    public void createCar(Car c);
    public Car readCar();

}
