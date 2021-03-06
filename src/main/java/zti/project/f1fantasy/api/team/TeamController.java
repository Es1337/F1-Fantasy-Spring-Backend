package zti.project.f1fantasy.api.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping(path = "{teamId}")
    public Team getTeamById(@PathVariable Long teamId) {
        return teamService.getTeamById(teamId);
    }

    @GetMapping(path = "season/{seasonId}")
    public List<Team> getTeamsFromSeason(@PathVariable Long seasonId) {
        return teamService.getTeamsFromSeason(seasonId);
    }

    @GetMapping(path = "name/{teamName}")
    public List<Team> getTeamByName(@PathVariable String teamName) {
        return teamService.getTeamByName(teamName);
    }

    @PostMapping(path = "{seasonId}")
    public Team postTeam(@RequestBody Team team, @PathVariable Long seasonId){
        return teamService.addTeam(team, seasonId);
    }

    @PutMapping(path = "{oldTeamId}")
    public Team updateTeamById(@RequestBody Team newTeam, @PathVariable Long oldTeamId){
        return teamService.updateTeamById(newTeam, oldTeamId);
    }

    @DeleteMapping(path = "{teamId}")
    public void deleteTeamById(@PathVariable Long teamId){
        teamService.deleteTeamById(teamId);
    }
}
