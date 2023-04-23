package com.example.shppyad16.Departure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DepartureController {
    private final DepartureService departureService;

    @Autowired
    public DepartureController(DepartureService departureService) {
        this.departureService = departureService;
    }

    @GetMapping("/departures")
    public ResponseEntity<List<Departure>> getAllDepartures() {
        return ResponseEntity.ok(departureService.getAllDepartures());
    }

    @GetMapping("/departures/{id}")
    public ResponseEntity<Departure> getDepartureById(@PathVariable("id") Long id) {
        Departure departure = departureService.getDepartureById(id);
        if (departure == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(departure);
    }

    @PostMapping("/postoffices/{officeId}/departures")
    public ResponseEntity<Departure> addDeparture(@PathVariable("officeId") Long officeId, @RequestBody Departure departure) {
        return ResponseEntity.ok(departureService.addDeparture(officeId, departure));
    }

    @DeleteMapping("/departures/{id}")
    public ResponseEntity<String> deleteDepartureById(@PathVariable("id") Long id) {
        if (departureService.getDepartureById(id) == null) {
            return ResponseEntity.badRequest().body("Invalid id");
        }
        departureService.deleteDepartureById(id);
        return ResponseEntity.ok("Departure deleted.");
    }
}