package com.example.demo.service.impl;

import com.example.demo.dto.FilmDTO;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.FilmAlreadyExistsException;
import com.example.demo.model.Film;
import com.example.demo.model.Show;
import com.example.demo.repository.ProductionRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.utils.ConversionUtils;
import com.example.demo.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl {


    private final ProductionRepository productionRepository;
    private final EmployeeService employeeService;

    @Autowired
     public ProductionServiceImpl(ProductionRepository productionRepository, EmployeeService employeeService){
        this.productionRepository = productionRepository;
        this.employeeService = employeeService;
    }



    public List<Film> getAllFilms() {
        return productionRepository.findAllFilms();
    }

    public List<Show> getAllShows() {
        return productionRepository.findAllShows();
    }

    // Method to save a new Film
    public FilmDTO addFilm(FilmDTO filmDTO) {
        this.checkIfFilmExists(filmDTO.getTitle().trim());
        ValidatorUtils.validateFilm(filmDTO);
       Film film = ConversionUtils.dtoToEntity(filmDTO);
       film.setProposer(employeeService.getEmployeeById(filmDTO.getProposerId()));
       film.setRegister(employeeService.getEmployeeById(filmDTO.getRegisterId()));
        this.productionRepository.save(film);
       return  filmDTO;
    }

    // Method to save a new Show
    public Show addShow(Show show) {
        return (Show) productionRepository.save(show);
    }

    public void checkIfFilmExists(String title){

        if (title != null && productionRepository.existsFilmByName(title)){
            throw new FilmAlreadyExistsException(title);
        }
    }
}
