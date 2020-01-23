package hu.szabonorbert.af_easymock.service;

import org.junit.Test;

import hu.szabonorbert.af_easymock.dao.CarDAO;
import hu.szabonorbert.af_easymock.core.model.Car;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.LinkedList;

@RunWith(JUnit4.class)
public class CarStoreTest {

    @Mock
    private CarDAO garage;

    @TestSubject
    private CarStore store;

    @Before
    public void setUp(){
        garage = EasyMock.niceMock(CarDAO.class);
        store = new CarStore(garage);
    }


    @Test
    public void getGarageTest() {
        LinkedList<Car> dummyDB = new LinkedList();
        dummyDB.add(new Car(1, 2005, "grey", "Megane CC", "Renault"));
        dummyDB.add(new Car(2, 2009, "red", "Focus CC", "Ford"));
        dummyDB.add(new Car(3, 2014, "black", "Astra CC", "Opel"));

        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB);
        EasyMock.replay(garage);

        ///

        LinkedList<Car> carlist = store.listCars();
        assertThat(carlist, hasItem(new Car(1, 2005, "grey", "Megane CC", "Renault")));
        assertThat(carlist, hasItem(new Car(2, 2009, "red", "Focus CC", "Ford")));
        assertThat(carlist, hasItem(new Car(3, 2014, "black", "Astra CC", "Opel")));
        assertTrue(carlist.size() == 3);
    }

    @Test
    public void addCar() {
        LinkedList<Car> dummyDB = new LinkedList();
        dummyDB.add(new Car(1, 2005, "grey", "Megane CC", "Renault"));

        Car newCar = new Car(2, 2009, "red", "Focus CC", "Ford");

        LinkedList<Car> dummyDB2 = new LinkedList();
        dummyDB2.add(newCar);

        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB);
        EasyMock.expect(garage.createCar(newCar)).andReturn(2);
        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB2);
        EasyMock.replay(garage);
    }

    @Test
    public void removeCar() {
    }

    @Test
    public void getCarCopyById() {
    }

    @Test
    public void listCarsByColor() {
    }

    @Test
    public void listCars() {
    }

    @Test
    public void recolorCar() {
    }

    @Test
    public void getOldestCar() {
    }
}