package com.example.demo.controller;

import com.example.demo.dto.FilmDTO;
import com.example.demo.dto.ProductionDTO;
import com.example.demo.model.Film;
import com.example.demo.model.Show;
import com.example.demo.service.impl.ProductionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productions")
public class ProductionController {

    @Autowired
    private ProductionServiceImpl productionServiceImpl;


    @PostMapping("/addFilm")
    public ResponseEntity<FilmDTO> addFilm(@RequestBody FilmDTO film) {
        return ResponseEntity.ok(productionServiceImpl.addFilm(film));
    }

    @PostMapping("/addShow")
    public ResponseEntity<Show> addShow(@RequestBody Show show) {
        return ResponseEntity.ok(productionServiceImpl.addShow(show));
    }
}
