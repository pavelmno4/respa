package ru.pkozlov.respa.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("user_role")
public class UserRole {
    @Id
    @Column("user_role_id")
    private Long id;

    @Column("user_id")
    private Long userId;

    @Column("role_id")
    private Long roleId;
}
