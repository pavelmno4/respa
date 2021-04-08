package ru.pkozlov.respa.db.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.pkozlov.respa.db.entity.Category;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {
}
