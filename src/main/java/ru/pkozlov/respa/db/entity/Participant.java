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
@Table("participant")
public class Participant implements EntityId {
    @Id
    @Column("participant_id")
    private Long id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("birth_year")
    private Integer birthYear;

    @Column("team_id")
    private Long teamId;

    @Column("weight")
    private Integer weight;

    @Column("weight_category")
    private Integer weightCategory;
}
