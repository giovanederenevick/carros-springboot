package br.com.giovanederenevick.cars.api;

import br.com.giovanederenevick.cars.domains.Car;
import br.com.giovanederenevick.cars.dtos.CarDTO;
import br.com.giovanederenevick.cars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<Page<CarDTO>> getCars(Pageable pageable) {
        return ResponseEntity.ok(carService.getCars(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id) {

        CarDTO result = carService.getCarById(id);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarDTO>> getCarsByType(@PathVariable("tipo") String type) {

        List<CarDTO> result = carService.getCarByType(type);

        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> insertCar(@RequestBody Car newCar) {

        CarDTO carDTO = carService.insertNewCar(newCar);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {

        CarDTO carUpdated = carService.updateCar(id, car);
        return ResponseEntity.ok(carUpdated);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {

        carService.deleteCar(id);

        return ResponseEntity.noContent().build();
    }
}
