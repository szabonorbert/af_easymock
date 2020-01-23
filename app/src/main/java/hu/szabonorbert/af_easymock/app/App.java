package hu.szabonorbert.af_easymock.app;
import hu.szabonorbert.af_easymock.core.model.Car;
import hu.szabonorbert.af_easymock.dao.CarDAO;
import hu.szabonorbert.af_easymock.service.CarStore;
import hu.szabonorbert.af_easymock.controller.CarSalesman;

public class App {

    public static void main(String args[]) {
        CarDAO db = new CarDAO();
        CarStore store = new CarStore(db);
        CarSalesman salesman = new CarSalesman(store);

        System.out.println("- Which cars do you have with color grey?");
        System.out.println("> " + salesman.listCarsByColor("grey"));
        System.out.println("- List all the cars!");
        System.out.println("> " + salesman.listCars());
        Car newCar = new Car(2012, "yellow", "Mustang GT", "Ford");
        System.out.println("- Add this car to the garage: " + newCar.toString());
        System.out.println("> " + salesman.placeCarInGarage(newCar));
        System.out.println("- List all the cars!");
        System.out.println("> " + salesman.listCars());
        System.out.println("- Let me see car with id 1!");
        Car carToRemove = salesman.touchCarById(1);
        System.out.println("> " + carToRemove.toString());
        System.out.println("- Remove that car!");
        System.out.println("> " + salesman.removeCarFromGarage(carToRemove));
        System.out.println("- List all the cars!");
        System.out.println("> " + salesman.listCars());

        System.out.println("- Thanks for nothing, may the force be with you.");
    }
}