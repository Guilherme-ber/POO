package com.mycompany.proprietario.carro.manager;

// Models
import com.mycompany.proprietario.carro.model.Car;

// Utils
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guilherme
 */
public class CarManager {
    private List<Car> carsList;
    
    public CarManager() {
        this.carsList = new ArrayList<>();
    }
    
    public void addCar(Car e) {
        carsList.add(e);
    }
    
    public void removeCar(int i) {
        carsList.remove(i);
    }
    
    public Car searchByIndex(int i) {
        return carsList.get(i);
    }
    
    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    for(Car c : carsList) {
        sb.append(c.toString()).append("\n");
    }
    return sb.toString();
}
}
