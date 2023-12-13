package ma.youcode.cmspringboot.service;

import ma.youcode.cmspringboot.model.domain.Fish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FishService {
    Fish createFish(Fish fish);
    public Fish updateFish(Fish fish);
    public Fish getFishById(Fish fish);
    Fish findFishByName(String fishName);
    Page<Fish> getAllFish(Pageable pageable);
}
