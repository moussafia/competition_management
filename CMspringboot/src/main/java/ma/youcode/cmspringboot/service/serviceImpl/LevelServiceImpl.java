package ma.youcode.cmspringboot.service.serviceImpl;

import ma.youcode.cmspringboot.model.domain.Level;
import ma.youcode.cmspringboot.repository.LevelRepository;
import ma.youcode.cmspringboot.service.LevelService;

import java.util.List;
import java.util.Optional;

public class LevelServiceImpl implements LevelService {
    private LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level createLevel(Level level) {
        validateLevelIfAlreadyExistForCreate(level);
        validateLevelPointForCreate(level);
        return levelRepository.save(level);
    }

    @Override
    public List<Level> getLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Optional<Level> searchLevel(Integer code) {
        return levelRepository.findByCode(code);
    }
    public void validateLevelIfAlreadyExistForCreate(Level level){
        levelRepository.findByCode(level.getCode()).ifPresent(l->{
            throw new IllegalStateException(l.getCode()+" Already exist and it should superior the last value of Level");
        });
    }
    public void validateLevelPointForCreate(Level level){
        Integer levelPointInserted = level.getPoints();
        Integer levelPointMaxExisted= levelRepository.findMaxPoint();
        if(levelPointInserted < levelPointMaxExisted ){
            throw new IllegalStateException("point that you gave to this code"+ level.getCode()+ "should be bigger than "+ levelPointMaxExisted);
        }
    }

}
