package ru.pkozlov.respa.dto.participant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantCardDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthYear;
    private String sex;
    private String weight;
    private TeamDto team;
}
