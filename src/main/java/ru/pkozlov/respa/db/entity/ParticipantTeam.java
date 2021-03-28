package ru.pkozlov.respa.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("participant_team")
public class ParticipantTeam implements EntityId {
    @Id
    @Column("participant_team_id")
    private Long id;

    @Column("participant_id")
    private Long participantId;

    @Column("team_id")
    private Long teamId;
}
