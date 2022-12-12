package ru.job4j.cars.repository.engine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс - реализация хранилища двигателей
 * @see ru.job4j.cars.model.Engine
 */
@Repository
@AllArgsConstructor
public class HbmEngineRepository implements EngineRepository {

    private final CrudRepository crudRepository;
    private static final String BY_ID = " WHERE id = :fId";
    private static final String UPDATE = "UPDATE Engine SET name = :fName" + BY_ID;
    private static final String DELETE = "DELETE Engine" + BY_ID;
    private static final String FIND_ALL = "from Engine";

    @Override
    public boolean create(Engine engine) {
        boolean result = true;
        try {
            crudRepository.run(session -> session.save(engine));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(Engine engine) {
        boolean result = true;
        try {
            crudRepository.run(
                    UPDATE, Map.of("fName", engine.getName(), "fId", engine.getId()));
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
    public List<Engine> findAllOrderById() {
        return crudRepository.query(
                FIND_ALL, Engine.class
        );
    }

    @Override
    public Optional<Engine> findById(int id) {
        return crudRepository.optional(
                FIND_ALL + BY_ID, Engine.class,
                Map.of("fId", id)
        );
    }
}
