package com.example.demo.controller;

import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.FilmDTO;
import com.example.demo.dto.ProductionDTO;
import com.example.demo.dto.ShowDTO;
import com.example.demo.model.Film;
import com.example.demo.model.Production;
import com.example.demo.model.Show;
import com.example.demo.service.ProductionService;
import com.example.demo.service.impl.ProductionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/productions")
public class ProductionController {


    private ProductionService productionService;

    @Autowired
    public ProductionController(ProductionService productionService){
        this.productionService = productionService;
    }


    @PostMapping("/addFilm")
    public ResponseEntity<FilmDTO> addFilm(@RequestBody FilmDTO film) {
        return ResponseEntity.ok(productionService.addFilm(film));
    }

    @PostMapping("/addShow")
    public ResponseEntity<ShowDTO> addShow(@RequestBody ShowDTO show) {
        return ResponseEntity.ok(productionService.addShow(show));
    }

    @GetMapping("/allFilms")
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        return ResponseEntity.ok(productionService.getAllFilms());
    }

    @GetMapping("/allShows")
    public ResponseEntity<List<ShowDTO>> getAllShows() {
        return ResponseEntity.ok(productionService.getAllShows());
    }


    @GetMapping("/allFilmsAndShows")
    public ResponseEntity<List<Object>> getAllFilmsAndShows(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Double rating,
            @RequestParam(required = false) Integer year) {

        return ResponseEntity.ok(productionService.getAllFilmsAndShows(genre, rating, year));
    }


    @PutMapping("film/{id}")
    public ResponseEntity<ProductionDTO> updateFilm(@PathVariable Long id,@RequestBody FilmDTO filmDTO) {
        return ResponseEntity.ok(productionService.updateFilm(filmDTO, id));
    }

    @PutMapping("show/{id}")
    public ResponseEntity<ProductionDTO> updateShow(@PathVariable Long id,@RequestBody ShowDTO showDTO) {
        return ResponseEntity.ok(productionService.updateShow(showDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductionDTO> deleteProduction(@PathVariable Long id) {
      productionService.deleteProduction(id);
      return ResponseEntity.noContent().build();
    }
}
