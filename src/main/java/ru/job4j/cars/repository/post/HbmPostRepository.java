package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.CrudRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс - реализация хранилища объявлений
 * @see ru.job4j.cars.model.Post
 */
@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {

    private final CrudRepository crudRepository;
    private static final String BY_ID = " WHERE id = :fId";
    private static final String CREATED_INTERVAL = " WHERE created  BETWEEN :fStart AND :fEnd";
    private static final String WITH_PHOTO = " WHERE photo is not null";
    private static final String DELETE = "DELETE Post" + BY_ID;
    private static final String FIND_ALL = "from Post";
    private static final String DELETE_PRICE_HISTORY = "DELETE PriceHistory ph WHERE ph.auto_post_id = :fId";
    private static final String BY_BRAND = " p left join fetch p.car pc where pc.name = :fBrand";

    @Override
    public boolean create(Post post) {
        boolean result = true;
        try {
            crudRepository.run(session -> session.save(post));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(Post post) {
        boolean result = true;
        try {
            crudRepository.run(session -> session.merge(post));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = true;
        deletePriceHistory(id);
        try {
            crudRepository.run(DELETE, Map.of("fId", id));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Post> findAllOrderById() {
        return crudRepository.query(
                FIND_ALL, Post.class
        );
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional(
                FIND_ALL + BY_ID, Post.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Post> findCreatedLastDay() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(1);
        return crudRepository.query(FIND_ALL + CREATED_INTERVAL, Post.class,
        Map.of("fStart", start, "fEnd", end));
    }

    @Override
    public List<Post> findWithPhoto() {
        return crudRepository.query(FIND_ALL + WITH_PHOTO, Post.class);
    }

    @Override
    public List<Post> findByBrand(String key) {
        return crudRepository.query(
                FIND_ALL + BY_BRAND, Post.class, Map.of("fBrand", key)
        );
    }

    private boolean deletePriceHistory(int id) {
        boolean result = true;
        try {
            crudRepository.run(DELETE_PRICE_HISTORY, Map.of("fId", id));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
