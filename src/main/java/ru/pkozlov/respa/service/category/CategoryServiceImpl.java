package ru.pkozlov.respa.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.Category;
import ru.pkozlov.respa.db.repository.CategoryRepository;
import ru.pkozlov.respa.dto.category.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;

    @Override
    public Flux<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Mono<Category> save(CategoryDto categoryDto) {
        return Mono.just(categoryDto)
                .flatMap(catNew -> {
                    String[] stringAges = categoryDto.getAges().split("-");
                    List<Integer> ages = IntStream
                            .rangeClosed(Integer.parseInt(stringAges[0]), Integer.parseInt(stringAges[1]))
                            .boxed()
                            .collect(Collectors.toList());
                    String catName = "cat_" + categoryDto.getAges().replace('-', '_');

                    return categoryRepo.save(
                            Category.builder()
                                    .id(categoryDto.getId())
                                    .categoryName(catName)
                                    .ages(ages)
                                    .weights(categoryDto.getWeights())
                                    .build());
                });
    }

    @Override
    public Mono<Void> delete(Long categoryId) {
        return categoryRepo.deleteById(categoryId);
    }
}
