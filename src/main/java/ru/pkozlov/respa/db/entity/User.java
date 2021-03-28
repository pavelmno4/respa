package ru.pkozlov.respa.db.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("usr")
public class User implements EntityId {
    @Id
    @Column("user_id")
    private Long id;

    @Column("login")
    private String login;

    @Column("password")
    private String password;

    @Column("status")
    private Boolean status;

    @Column("last_update_date")
    private LocalDateTime lastUpdateDate;

    @Transient
    @ToString.Exclude
    private List<Role> userRoles;

}
