package hu.szabonorbert.af_easymock.dao;
import hu.szabonorbert.af_easymock.core.dao.CarDAOinterface;
import hu.szabonorbert.af_easymock.core.model.Car;

import java.util.LinkedList;

public class CarDAO implements CarDAOinterface {

    private LinkedList<Car> db;

    public CarDAO(){
        db = new LinkedList<Car>();
        db.add(new Car(1, 2005, "grey", "Megane CC", "Renault"));
        db.add(new Car(2, 2009, "red", "Focus CC", "Ford"));
        db.add(new Car(3, 2014, "black", "Astra CC", "Opel"));
    }

    @Override
    public void createCar(Car car){
        if (car.getId() == 0 || isIdTaken(car.getId())){
            car.setId(getLastId()+1);
        }
        db.add(car);
    }

    @Override
    public Car readCar(int id){
        for(Car c:db){
            if (c.getId() == id) return c;
        }
        return null;
    }

    @Override
    public LinkedList<Car> readAllCars() {
        LinkedList<Car> ret;
        ret = (LinkedList<Car>) db.clone();
        return ret;
    }

    @Override
    public void deleteCar(int id){
        int index = 0;
        int found_index = -1;
        for(Car c:db){
            if (c.getId() == id) found_index = index;
            index++;
        }
        if (found_index>-1){
            db.remove(found_index);
        }
    }

    @Override
    public void updateCar(Car car){
        int index = 0;
        int found_index = -1;
        for(Car c:db){
            if (c.getId() == car.getId()) found_index = index;
            index++;
        }
        if (found_index>-1){
            db.set(found_index, car);
        }
    }

    private int getLastId(){
        int ret = 0;
        for(Car c:db){
            if (c.getId() > ret) ret = c.getId();
        }
        return ret;
    }

    private boolean isIdTaken(int id){
        for(Car c:db){
            if (c.getId() == id) return true;
        }
        return false;
    }



}
