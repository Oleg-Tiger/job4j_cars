package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Модель данных автомобиля
 */
@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {

    /**
     * Идентификатор автомобиля
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Марка автомобиля
     */
    private String brand;

    /**
     * Модель автомобиля
     */
    private String model;

    /**
     * Двигатель автомобиля
     */
    @EqualsAndHashCode.Include
    @OneToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    /**
     * Водитель - владелец автомобиля
     */
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    /**
     * История всех владельцев автомобиля
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "owner_history", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)})
    private Set<Driver> owners = new HashSet<>();
}
