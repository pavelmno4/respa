package ru.pkozlov.respa.service.participant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.Participant;
import ru.pkozlov.respa.db.entity.Team;
import ru.pkozlov.respa.db.repository.ParticipantRepository;
import ru.pkozlov.respa.db.repository.TeamRepository;
import ru.pkozlov.respa.dto.participant.ParticipantCardDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepo;
    private final TeamRepository teamRepo;
    private final ModelMapper modelMapper;

    @Override
    public Mono<ParticipantCardDto> save(ParticipantCardDto participantDto) {
        Participant participantNew = modelMapper.map(participantDto, Participant.class);
        Team teamNew = modelMapper.map(participantDto.getTeam(), Team.class);

        return teamRepo.findByTeamName(teamNew.getTeamName())
                .flatMap(team -> saveNewParticipant(participantNew, team))
                .switchIfEmpty(teamRepo.save(teamNew)
                        .flatMap(savedTeam -> saveNewParticipant(participantNew, savedTeam)));
    }

    @Override
    public Mono<ParticipantCardDto> find(Long participantId) {
        return participantRepo.findById(participantId)
                .map(p -> modelMapper.map(p, ParticipantCardDto.class));
    }

    @Override
    public Flux<Participant> findAllInCategory(Long age, Long weight) {
        return participantRepo.findAllInCategory(age, weight);
    }

    @Override
    public Mono<Void> delete(Long participantId) {
        return participantRepo.deleteById(participantId);
    }

    private Mono<ParticipantCardDto> saveNewParticipant(Participant participantNew, Team team) {
        participantNew.setTeamId(team.getId());
        return participantRepo.save(participantNew)
                .map(savedParticipant -> modelMapper.map(savedParticipant, ParticipantCardDto.class));
    }
}
