package zti.project.f1fantasy.api.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService {
    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public List<Season> getSeasons(){
        return seasonRepository.findAll();
    }

    public Season getSeasonById(Long seasonId) {
        return seasonRepository.findById(seasonId).orElseThrow(() -> new SeasonNotFoundException(seasonId));
    }

    public List<Season> getSeasonByYear(Integer year) {
        return seasonRepository.findByYear(year);
    }

    public List<Season> getSeasonByWdc(String wdc) {
        return seasonRepository.findByWdc(wdc);
    }

    public List<Season> getSeasonByWcc(String wcc) {
        return seasonRepository.findByWcc(wcc);
    }

    public Season addSeason(Season season){
        return seasonRepository.save(season);
    }

    public Season updateSeasonById(Season newSeason, Long oldSeasonId) {
        return seasonRepository.findById(oldSeasonId).map(season -> {
            season.setYear(newSeason.getYear());
            season.setWdc(newSeason.getWdc());
            season.setWcc(newSeason.getWcc());

            return seasonRepository.save(season);
        }).orElseGet(() -> {
            newSeason.setId(oldSeasonId);
            return seasonRepository.save(newSeason);
        });
    }

    public void deleteSeasonById(Long seasonId){
        Season seasonToDelete = seasonRepository.findById(seasonId).get();
        seasonRepository.delete(seasonToDelete);
    }
}
