package br.com.giovanederenevick.cars.domains;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "carro")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "tipo")
    private String type;
}
