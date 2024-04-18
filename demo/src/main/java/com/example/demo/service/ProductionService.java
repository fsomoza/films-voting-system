package com.example.demo.service;

import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.FilmDTO;
import com.example.demo.dto.ProductionDTO;
import com.example.demo.dto.ShowDTO;
import com.example.demo.model.Show;

import java.util.ArrayList;
import java.util.List;

public interface ProductionService {
    FilmDTO addFilm(FilmDTO film);

    ShowDTO addShow(ShowDTO show);

    List<FilmDTO> getAllFilms();

    List<ShowDTO> getAllShows();

    List<Object> getAllFilmsAndShows(String genre, Double rating, Integer year);

    ProductionDTO updateProduction(ProductionDTO productionDTO, long id);

    FilmDTO updateFilm(FilmDTO filmDTO, long id);

    ShowDTO updateShow(ShowDTO showDTO, long id);

    void deleteProduction(long id);

    EmployeeResponseDTO returnEmployeeWithHighestScoreProposed();

}
