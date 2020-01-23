package hu.szabonorbert.af_easymock.service;

import org.junit.Test;

import hu.szabonorbert.af_easymock.dao.CarDAO;
import hu.szabonorbert.af_easymock.core.model.Car;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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

        ///

        assertThat(carlist, hasItem(new Car(1, 2005, "grey", "Megane CC", "Renault")));
        assertThat(carlist, hasItem(new Car(2, 2009, "red", "Focus CC", "Ford")));
        assertThat(carlist, hasItem(new Car(3, 2014, "black", "Astra CC", "Opel")));
        assertTrue(carlist.size() == 3);
    }

    @Test
    public void addCarTest() {

        Car oldCar = new Car(1, 2005, "grey", "Megane CC", "Renault");
        Car newCar = new Car(2, 2009, "red", "Focus CC", "Ford");

        LinkedList<Car> dummyDB = new LinkedList<Car>();
        dummyDB.add(oldCar);

        LinkedList<Car> dummyDB2 = new LinkedList<Car>();
        dummyDB2.add(oldCar);
        dummyDB2.add(newCar);

        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB);
        EasyMock.expect(garage.createCar(newCar)).andReturn(2);
        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB2);

        EasyMock.replay(garage);

        //

        LinkedList<Car> carlist = store.listCars();
        int new_id = store.addCar(newCar);
        LinkedList<Car> carlist2 = store.listCars();

        //

        assertEquals(1, carlist.size());
        assertEquals(2, carlist2.size());
        assertEquals(2, new_id);
        assertThat(carlist, hasItem(oldCar));
        assertThat(carlist, not(hasItem(newCar)));
        assertThat(carlist2, hasItem(oldCar));
        assertThat(carlist2, hasItem(newCar));

    }

    @Test
    public void removeCarTest() {

        Car oldCar = new Car(1, 2005, "grey", "Megane CC", "Renault");
        Car newCar = new Car(2, 2009, "red", "Focus CC", "Ford");

        LinkedList<Car> dummyDB = new LinkedList<Car>();
        dummyDB.add(oldCar);

        LinkedList<Car> dummyDB2 = new LinkedList<Car>();
        dummyDB2.add(oldCar);
        dummyDB2.add(newCar);

        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB2);
        EasyMock.expect(garage.deleteCar(2)).andReturn(true);
        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB);

        EasyMock.replay(garage);

        //

        LinkedList<Car> carlist = store.listCars();
        boolean remove_result = store.removeCar(carlist.get(1));
        LinkedList<Car> carlist2 = store.listCars();

        //

        assertEquals(2, carlist.size());
        assertEquals(1, carlist2.size());
        assertEquals(true, remove_result);
        assertThat(carlist2, hasItem(oldCar));
        assertThat(carlist2, not(hasItem(newCar)));
        assertThat(carlist, hasItem(oldCar));
        assertThat(carlist, hasItem(newCar));

    }

    @Test
    public void getCarCopyByIdTest() {

        Car expected_return = new Car(3, 2014, "black", "Astra CC", "Opel");

        EasyMock.expect(garage.readCar(3)).andReturn(new Car(3, 2014, "black", "Astra CC", "Opel"));
        EasyMock.expect(garage.readCar(4)).andReturn(null);
        EasyMock.replay(garage);

        ///
        Car result_car =  store.getCarCopyById(3);
        Car result_car2 =  store.getCarCopyById(4);

       assertTrue(expected_return.equals(result_car));
       assertEquals(null, result_car2);
    }

    @Test
    public void listCarsByColor() {
        LinkedList<Car> dummyDB = new LinkedList();
        dummyDB.add(new Car(1, 2005, "grey", "Megane CC", "Renault"));
        dummyDB.add(new Car(2, 2009, "red", "Focus CC", "Ford"));
        dummyDB.add(new Car(3, 2014, "black", "Astra CC", "Opel"));

        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB);
        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB);
        EasyMock.replay(garage);

        //

        LinkedList<Car> result1 = store.listCarsByColor("grey");
        LinkedList<Car> result2 = store.listCarsByColor("red");

        //

        assertEquals(1, result1.size());
        assertEquals(1, result2.size());
        assertThat(result1, hasItem(new Car(1, 2005, "grey", "Megane CC", "Renault")));
        assertThat(result2, hasItem(new Car(2, 2009, "red", "Focus CC", "Ford")));
    }

    @Test
    public void listCarsTest() {
        LinkedList<Car> dummyDB = new LinkedList();
        dummyDB.add(new Car(1, 2005, "grey", "Megane CC", "Renault"));
        dummyDB.add(new Car(2, 2009, "red", "Focus CC", "Ford"));
        dummyDB.add(new Car(3, 2014, "black", "Astra CC", "Opel"));

        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB);
        EasyMock.replay(garage);

        ///

        LinkedList<Car> carlist = store.listCars();

        ///

        assertThat(carlist, hasItem(new Car(1, 2005, "grey", "Megane CC", "Renault")));
        assertThat(carlist, hasItem(new Car(2, 2009, "red", "Focus CC", "Ford")));
        assertThat(carlist, hasItem(new Car(3, 2014, "black", "Astra CC", "Opel")));
        assertTrue(carlist.size() == 3);
    }

    @Test
    public void recolorCar() {
        EasyMock.expect(garage.readCar(1)).andReturn(new Car(1, 2005, "grey", "Megane CC", "Renault"));
        Car car_as_recolored = new Car(1, 2005, "red", "Megane CC", "Renault");
        EasyMock.expect(garage.updateCar(car_as_recolored)).andReturn(true);
        EasyMock.expect(garage.readCar(1)).andReturn(new Car(1, 2005, "red", "Megane CC", "Renault"));
        EasyMock.replay(garage);

        //

        boolean colorization = store.recolorCar(1, "red");
        Car myNewCar = store.getCarCopyById(1);

        //

        assertEquals(true, colorization);
        assertEquals("red", myNewCar.getColor());
        assertEquals(1, myNewCar.getId());
    }

    @Test
    public void recolorCar2() {
        EasyMock.expect(garage.readCar(1)).andReturn(new Car(1, 2005, "grey", "Megane CC", "Renault"));
        Car car_as_recolored = new Car(1, 2005, "grey", "Megane CC", "Renault");
        EasyMock.expect(garage.updateCar(car_as_recolored)).andReturn(false);
        EasyMock.expect(garage.readCar(1)).andReturn(new Car(1, 2005, "grey", "Megane CC", "Renault"));
        EasyMock.replay(garage);

        //

        boolean colorization = store.recolorCar(1, "grey");
        Car myNewCar = store.getCarCopyById(1);

        //

        assertEquals(false, colorization);
        assertEquals("grey", myNewCar.getColor());
        assertEquals(1, myNewCar.getId());
    }


    @Test
    public void getOldestCar() {
        LinkedList<Car> dummyDB = new LinkedList();
        dummyDB.add(new Car(1, 2005, "grey", "Megane CC", "Renault"));
        dummyDB.add(new Car(2, 2009, "red", "Focus CC", "Ford"));
        dummyDB.add(new Car(3, 2014, "black", "Astra CC", "Opel"));

        EasyMock.expect(garage.readAllCars()).andReturn(dummyDB);
        EasyMock.replay(garage);

        //

        Car oldest_car_from_store = store.getOldestCar();
        Car oldest_car_i_think = new Car(1, 2005, "grey", "Megane CC", "Renault");

        //

        assertEquals(oldest_car_i_think, oldest_car_from_store);

    }
}