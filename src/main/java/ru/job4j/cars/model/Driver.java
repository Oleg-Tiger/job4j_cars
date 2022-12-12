package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Модель данных водителя
 */
@Entity
@Table(name = "driver")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Driver {

    /**
     * Идентификатор водителя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Имя водителя
     */
    private String name;

    /**
     * Профиль водителя
     */
    @EqualsAndHashCode.Include
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
