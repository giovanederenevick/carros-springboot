package br.com.giovanederenevick.cars.repositories;

import br.com.giovanederenevick.cars.domains.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByType(String type);
}
