package zti.project.f1fantasy.api.race;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.project.f1fantasy.api.season.Season;
import zti.project.f1fantasy.api.season.SeasonService;

import java.util.List;

@Service
public class RaceService {
    private final RaceRepository raceRepository;
    private final SeasonService seasonService;

    @Autowired
    public RaceService(RaceRepository raceRepository, SeasonService seasonService) {
        this.raceRepository = raceRepository;
        this.seasonService = seasonService;
    }

    public List<Race> getAllRaces(){
        return raceRepository.findAll();
    }

    public Race getRaceById(Long raceId){
        return raceRepository.findById(raceId).orElseThrow(() -> new RaceNotFoundException(raceId));
    }

    public List<Race> getRacesByName(String raceName){
        return raceRepository.findByName(raceName);
    }

    public List<Race> getRacesByCity(String city){
        return raceRepository.findByCity(city);
    }

    public List<Race> getRacesByCountry(String country){
        return raceRepository.findByCountry(country);
    }

    public List<Race> getRacesByTrack(String track){
        return raceRepository.findByTrack(track);
    }

    public List<Race> getRacesFromSeason(Long seasonId){
        return raceRepository.findBySeasonId(seasonId);
    }

    public Race addRace(Race race, Long seasonId){
        Season season = seasonService.getSeasonById(seasonId);
        race.setSeason(season);
        return raceRepository.save(race);
    }

    public Race updateRaceById(Race newRace, Long oldRaceId){
        return raceRepository.findById(oldRaceId).map(race -> {
            race.setName(newRace.getName());
            race.setCity(newRace.getCity());
            race.setCountry(newRace.getCountry());
            race.setTrack(newRace.getTrack());
            race.setFp1(newRace.getFp1());
            race.setFp2(newRace.getFp2());
            race.setFp3(newRace.getFp3());
            race.setQuali(newRace.getQuali());
            race.setSprint(newRace.getSprint());
            race.setRace(newRace.getRace());

            return raceRepository.save(race);
        }).orElseGet(() -> {
            newRace.setId(oldRaceId);

            return raceRepository.save(newRace);
        });
    }

    public void deleteRaceById(Long raceId){
        Race raceToDelete = raceRepository.findById(raceId).get();
        raceRepository.delete(raceToDelete);
    }
}
