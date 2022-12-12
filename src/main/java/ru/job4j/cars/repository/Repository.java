package ru.job4j.cars.repository;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, описывающий хранилище объектов, с методами CRUD
 */
public interface Repository<T> {

    /**
     * Сохранить в базе.
     * @param t сохраняемый объект.
     * @return boolean результат сохранения.
     */
    public boolean create(T t);

    /**
     * Обновить в базе объект.
     * @param t обновляемый объект.
     * @return boolean результат обновления.
     */
    public boolean update(T t);

    /**
     * Удалить объект по id.
     * @param id ID
     * @return boolean результат удаления.
     */
    public boolean delete(int id);

    /**
     * Список объектов отсортированных по id.
     * @return список объектов T.
     */
    public List<T> findAllOrderById();

    /**
     * Найти объект по ID
     * @param id ID
     * @return Optional of T.
     */
    public Optional<T> findById(int id);

}
