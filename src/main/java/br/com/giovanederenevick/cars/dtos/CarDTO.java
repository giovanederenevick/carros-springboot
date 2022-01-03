package br.com.giovanederenevick.cars.dtos;

import br.com.giovanederenevick.cars.domains.Car;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarDTO {

    private Long id;
    private String name;
    private String type;

    public static CarDTO create(Car car) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(car, CarDTO.class);
    }
}
