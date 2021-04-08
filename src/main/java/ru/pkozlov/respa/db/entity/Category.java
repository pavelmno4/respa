package ru.pkozlov.respa.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table("category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements EntityId {
    @Id
    @Column("category_id")
    private Long id;

    @Column("category_name")
    private String categoryName;

    @Column("ages")
    private List<Integer> ages;

    @Column("weights")
    private List<Integer> weights;
}
