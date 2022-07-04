package zti.project.f1fantasy.api.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.season.SeasonService;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final SeasonService seasonService;

    @Autowired
    public TeamService(TeamRepository teamRepository, SeasonService seasonService) {
        this.teamRepository = teamRepository;
        this.seasonService = seasonService;
    }

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public Team getTeamById(Long teamId){
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    public List<Team> getTeamByName(String teamName){
        return teamRepository.findByName(teamName);
    }

    public List<Team> getTeamsFromSeason(Long seasonId) {
        return teamRepository.findBySeasonId(seasonId);
    }

    public Team addTeam(Team team, Long seasonId){
        Season season = seasonService.getSeasonById(seasonId);
        team.setSeason(season);

        return teamRepository.save(team);
    }

    public Team updateTeamById(Team newTeam, Long oldTeamId) {
        return teamRepository.findById(oldTeamId).map(team -> {
            team.setName(newTeam.getName());
            team.setPoints(newTeam.getPoints());

            return teamRepository.save(team);
        }).orElseGet(() -> {
            newTeam.setId(oldTeamId);
            return teamRepository.save(newTeam);
        });
    }

    public void deleteTeamById(Long teamId){
        Team teamToDelete = teamRepository.findById(teamId).get();
        teamRepository.delete(teamToDelete);
    }
}
