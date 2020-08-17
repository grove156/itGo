package kr.co.fastcampus.Eatgo.interfaces;

import kr.co.fastcampus.Eatgo.application.RegionService;
import kr.co.fastcampus.Eatgo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list(){

        return regionService.getRegions();
    }

    @PostMapping("/regions")
    public ResponseEntity<Object> create(@RequestBody Region resource) throws URISyntaxException {

        regionService.addRegion(resource);

        String url = "region/1";
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
