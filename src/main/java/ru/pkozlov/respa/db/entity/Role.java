package ru.pkozlov.respa.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("role")
public class Role implements EntityId {
    @Id
    @Column("role_id")
    private Long id;

    @Column("role_name")
    private String roleName;

    @Column("description")
    private String description;

    @Column("create_date")
    private LocalDateTime createDate;
}
