package com.example.shppyad16.PostOffice;

import com.example.shppyad16.Departure.Departure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/postoffices")
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

    @Autowired
    public PostOfficeController(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }

    @GetMapping
    public ResponseEntity<List<PostOffice>> getAllPostOffices() {
        return ResponseEntity.ok(postOfficeService.getAllPostOffices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostOffice> getPostOfficeById(@PathVariable("id") Long id) {
        PostOffice postOffice = postOfficeService.getPostOfficeById(id);
        if (postOffice == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(postOffice);
    }

    @GetMapping("/{id}/departures")
    public ResponseEntity<List<Departure>> getDeparturesByPostOffice(@PathVariable("id") Long id) {
        List<Departure> departures = postOfficeService.getDeparturesByPostOffice(id);
        if (postOfficeService.getPostOfficeById(id) == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(departures);
    }

    @PostMapping
    public ResponseEntity<PostOffice> createPostOffice(@RequestBody PostOffice postOffice) {
        return ResponseEntity.ok(postOfficeService.createPostOffice(postOffice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostOfficeById(@PathVariable("id") Long id) {
        if (postOfficeService.getPostOfficeById(id) == null) {
            return ResponseEntity.badRequest().body("Invalid id");
        }
        postOfficeService.deletePostOfficeById(id);
        return ResponseEntity.ok("PostOffice deleted.");
    }
}
