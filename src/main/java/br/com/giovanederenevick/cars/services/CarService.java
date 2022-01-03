package br.com.giovanederenevick.cars.services;

import br.com.giovanederenevick.cars.domains.Car;
import br.com.giovanederenevick.cars.dtos.CarDTO;
import br.com.giovanederenevick.cars.repositories.CarRepository;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Page<CarDTO> getCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(CarDTO::create);
    }

    public CarDTO getCarById(Long id) {

        Optional<Car> result = carRepository.findById(id);

        return result.map(CarDTO::create)
                .orElseThrow(() -> new ObjectNotFoundException(id, "Carro não encontrado"));
    }

    public List<CarDTO> getCarByType(String type) {

        return carRepository.findByType(type).stream().map(CarDTO::create).collect(Collectors.toList());
    }

    public CarDTO insertNewCar(Car newCar) {

        Assert.isNull(newCar.getId(), "Não foi possível inserir o registro");

        return CarDTO.create(carRepository.save(newCar));
    }

    public CarDTO updateCar(Long id, Car car) {

        return carRepository.findById(id).map(carOptional -> {
            carOptional.setName(car.getName());
            carOptional.setType(car.getType());

            carRepository.save(carOptional);

            return CarDTO.create(carOptional);
        }).orElseThrow(() -> new ObjectNotFoundException(id, ("Não foi possível atualizar o registro")));
    }

    public void deleteCar(Long id) {

        carRepository.deleteById(id);
    }
}
