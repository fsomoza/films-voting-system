package com.example.demo.service;

import com.example.demo.dto.FilmDTO;
import com.example.demo.dto.ShowDTO;
import com.example.demo.model.Show;

public interface ProductionService {
    FilmDTO addFilm(FilmDTO film);

    ShowDTO addShow(ShowDTO show);
}
