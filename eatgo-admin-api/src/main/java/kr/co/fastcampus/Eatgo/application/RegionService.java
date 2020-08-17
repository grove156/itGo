package kr.co.fastcampus.Eatgo.application;

import kr.co.fastcampus.Eatgo.domain.Region;
import kr.co.fastcampus.Eatgo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getRegions() {
        List<Region> regions = regionRepository.findAll();
        return regions;
    }

    public Region addRegion(Region region) {
        Region newRegion = regionRepository.save(region);
        return newRegion;
    }
}
