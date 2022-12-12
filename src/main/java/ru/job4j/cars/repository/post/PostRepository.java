package ru.job4j.cars.repository.post;

import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.Repository;
import java.util.List;

/**
 * Хранилище объявлений
 * @see ru.job4j.cars.model.Post
 */
public interface PostRepository extends Repository<Post> {

    /**
     * Список объявлений за последние сутки
     * @return список объявлений.
     */
    List<Post> findCreatedLastDay();

    /**
     * Список объявлений с фото
     * @return список объявлений.
     */
    List<Post> findWithPhoto();

    /**
     * Список объявлений по марке авто
     * @param key key
     * @return список объявлений.
     */
    List<Post> findByBrand(String key);

}
