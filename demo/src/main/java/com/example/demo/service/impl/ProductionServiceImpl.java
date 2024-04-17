package com.example.demo.service.impl;

import com.example.demo.dto.FilmDTO;
import com.example.demo.dto.ShowDTO;
import com.example.demo.exception.ProductionAlreadyExistsException;
import com.example.demo.model.Film;
import com.example.demo.model.Show;
import com.example.demo.repository.ProductionRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProductionService;
import com.example.demo.utils.ConversionUtils;
import com.example.demo.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionServiceImpl implements ProductionService {


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


    @Override
    public FilmDTO addFilm(FilmDTO filmDTO) {
        ValidatorUtils.validateFilm(filmDTO);
        this.checkIfProductionExists(filmDTO.getTitle().trim(),"FILM");
       Film film = ConversionUtils.dtoToEntity(filmDTO);
       film.setProposer(employeeService.getEmployeeById(filmDTO.getProposerId()));
       film.setRegister(employeeService.getEmployeeById(filmDTO.getRegisterId()));
        this.productionRepository.save(film);
       return  filmDTO;
    }

    @Override
    public ShowDTO addShow(ShowDTO show) {
        return null;
    }

    // Method to save a new Show
    public Show addShow(Show show) {
        return (Show) productionRepository.save(show);
    }

    public void checkIfProductionExists(String title, String prodType){

        if (title != null && productionRepository.existsProductionByName(title,prodType)){
            throw new ProductionAlreadyExistsException(title,prodType);
        }
    }
}
