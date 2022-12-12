package ru.job4j.cars.repository.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс - реализация хранилище автомобилей
 * @see ru.job4j.cars.model.Car
 */
@Repository
@AllArgsConstructor
public class HbmCarRepository implements CarRepository {

    private final CrudRepository crudRepository;
    private static final String BY_ID = " WHERE id = :fId";
    private static final String DELETE = "DELETE Car" + BY_ID;
    private static final String FIND_ALL = "from Car";

    @Override
    public boolean create(Car car) {
        boolean result = true;
        try {
            crudRepository.run(session -> session.save(car));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(Car car) {
        boolean result = true;
        try {
            crudRepository.run(session -> session.merge(car));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = true;
        try {
            crudRepository.run(DELETE, Map.of("fId", id));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Car> findAllOrderById() {
        return crudRepository.query(
                FIND_ALL, Car.class
        );
    }

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional(
                FIND_ALL + BY_ID, Car.class,
                Map.of("fId", id)
        );
    }
}
