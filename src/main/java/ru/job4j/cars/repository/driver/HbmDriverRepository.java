package ru.job4j.cars.repository.driver;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс - реализация хранилища водителей
 * @see ru.job4j.cars.model.Driver
 */
@Repository
@AllArgsConstructor
public class HbmDriverRepository implements DriverRepository {

    private final CrudRepository crudRepository;
    private static final String BY_ID = " WHERE id = :fId";
    private static final String UPDATE = "UPDATE Driver SET name = :fName, user_id = :fUser" + BY_ID;
    private static final String DELETE = "DELETE Driver" + BY_ID;
    private static final String FIND_ALL = "from Driver";

    @Override
    public boolean create(Driver driver) {
        boolean result = true;
        try {
            crudRepository.run(session -> session.save(driver));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(Driver driver) {
        boolean result = true;
        try {
            crudRepository.run(
                    UPDATE, Map.of(
                            "fName", driver.getName(), "fUser", driver.getUser().getId(), "fId", driver.getId()
                    ));
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
    public List<Driver> findAllOrderById() {
        return crudRepository.query(
                FIND_ALL, Driver.class
        );
    }

    @Override
    public Optional<Driver> findById(int id) {
        return crudRepository.optional(
                FIND_ALL + BY_ID, Driver.class,
                Map.of("fId", id)
        );
    }
}
