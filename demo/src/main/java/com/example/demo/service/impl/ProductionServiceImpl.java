package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.FilmDTO;
import com.example.demo.dto.ProductionDTO;
import com.example.demo.dto.ShowDTO;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.ProductionAlreadyExistsException;
import com.example.demo.exception.ProductionNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.Film;
import com.example.demo.model.Production;
import com.example.demo.model.Show;
import com.example.demo.repository.ProductionRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProductionService;
import com.example.demo.utils.ConversionUtils;
import com.example.demo.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductionServiceImpl implements ProductionService {


    private final ProductionRepository productionRepository;
    private final EmployeeService employeeService;


    @Autowired
     public ProductionServiceImpl(ProductionRepository productionRepository, EmployeeService employeeService){
        this.productionRepository = productionRepository;
        this.employeeService = employeeService;
    }



    public List<FilmDTO> getAllFilms() {
        return productionRepository.findAllFilms().stream()
                .map(ConversionUtils::entityToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<ShowDTO> getAllShows() {
        return productionRepository.findAllShows().stream()
                .map(ConversionUtils::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Object> getAllFilmsAndShows(String genre, Double score, Integer year) {
        List<Production> productions =  productionRepository.findAllFilmsAndShows(genre,score,year);
        return ConversionUtils.entityToDTO(productions);
    }

    @Override
    public ProductionDTO updateProduction(ProductionDTO productionDTO, long id) {
        return null;
    }


    public EmployeeResponseDTO returnEmployeeWithHighestScoreProposed() {

        return ConversionUtils.entityToDTO(productionRepository.findEmployeeWithHighestScoringProduction(PageRequest.of(0, 1)).stream().findFirst().orElse(null));
    }


    public FilmDTO updateFilm(FilmDTO filmDTO,long id) {
        this.getProductionById(id);
        ValidatorUtils.validateFilm(filmDTO);
        this.checkIfFilmExists(filmDTO.getTitle().trim(),id);
        Film film = productionRepository.findFilmById(id);
        film.setLength(filmDTO.getLength());
        film.setGenre(filmDTO.getGenre());
        film.setDirector(filmDTO.getDirector());
        film.setYear(filmDTO.getYear());
        film.setTitle(filmDTO.getTitle());
        return ConversionUtils.entityToDTO(productionRepository.save(film));
    }

    public ShowDTO updateShow(ShowDTO showDTO,long id) {
        this.getProductionById(id);
        ValidatorUtils.validateShow(showDTO);
        this.checkIfShowExists(showDTO.getTitle().trim(),id);
        Show show = productionRepository.findShowById(id);
        show.setSeasons(showDTO.getSeasons());
        show.setGenre(showDTO.getGenre());
        show.setDirector(showDTO.getDirector());
        show.setYear(showDTO.getYear());
        show.setTitle(showDTO.getTitle());
        return ConversionUtils.entityToDTO(productionRepository.save(show));
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



    // Method to save a new Show
    public ShowDTO addShow(ShowDTO showDTO) {
        ValidatorUtils.validateShow(showDTO);
        this.checkIfProductionExists(showDTO.getTitle().trim(),"SHOW");
        Show show = ConversionUtils.dtoToEntity(showDTO);
        show.setProposer(employeeService.getEmployeeById(showDTO.getProposerId()));
        show.setRegister(employeeService.getEmployeeById(showDTO.getRegisterId()));
        this.productionRepository.save(show);
        return  showDTO;
    }

    public void checkIfProductionExists(String title, String prodType){

        if (title != null && productionRepository.existsProductionByName(title,prodType)){
            throw new ProductionAlreadyExistsException(title,prodType);
        }
    }

    public void checkIfFilmExists(String title,long id){

        if (title != null && productionRepository.filmExistsByTitleAndIdNot(title,id)){
            throw new ProductionAlreadyExistsException(title,"film");
        }
    }

    public void checkIfShowExists(String title,long id){

        if (title != null && productionRepository.showExistsByTitleAndIdNot(title,id)){
            throw new ProductionAlreadyExistsException(title,"show");
        }
    }

    public Production getProductionById(Long id) {
        Employee result;
        Optional<Production> production = productionRepository.findById(id);
        if (production.isPresent()){
            return  production.get();
        }else{
            throw new ProductionNotFoundException(id);
        }

    }


    @Transactional
    @Override
    public void deleteProduction(long id) {
        //this method already checks if the employee exists
        Production production = this.getProductionById(id);
        productionRepository.deleteById(id);
    }
}
