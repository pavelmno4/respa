package ru.pkozlov.respa.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;

    /**
     * Диапазон возрастов формата 9-11
     */
    private String ages;
    private List<Integer> weights;
}
