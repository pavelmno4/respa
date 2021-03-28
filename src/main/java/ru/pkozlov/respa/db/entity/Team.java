package ru.pkozlov.respa.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("team")
public class Team implements EntityId {
    @Id
    @Column("team_id")
    private Long id;

    @Column("team_name")
    private String teamName;

    @Column("city")
    private String city;
}
