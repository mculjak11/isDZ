package com.is.infsusdz.ads;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdsController {

    private final AdsService as;

    public AdsController(AdsService as) {
        this.as = as;
    }

    @GetMapping(path="/api/ads/all", produces = "application/json")
    public ResponseEntity getAllAds() {
        return as.getAll();
    }

    @GetMapping(path="/api/ads/id={id}", produces = "application/json")
    public ResponseEntity getAdById(@PathVariable String id) {
        return as.getById(id);
    }

    @GetMapping(path="/api/ads/owner={owner}", produces = "application/json")
    public ResponseEntity getAdsByOwner(@PathVariable String owner) {
        return as.getByOwner(owner);
    }


    @GetMapping(path="/api/ads/title={title}")
    public ResponseEntity getAdByTitle(@PathVariable String title) {
        return as.getByTitle(title);
    }

    @PostMapping(path="/api/ads/action", produces = "application/json")
    public ResponseEntity createAd(@RequestBody CarFindAd adCreate) {
        return as.create(adCreate);
    }

    @PutMapping(path="/api/ads/action")
    public ResponseEntity updateAd(@RequestBody CarFindAd adUpdate) {
        return as.update(adUpdate);
    }

    @DeleteMapping(path="/api/ads/action")
    public ResponseEntity deleteAd(@RequestBody DeleteAd delAd) {
        return as.delete(delAd);
    }
}
