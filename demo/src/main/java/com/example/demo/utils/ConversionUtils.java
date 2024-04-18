package com.example.demo.utils;

import com.example.demo.dto.*;
import com.example.demo.model.Employee;
import com.example.demo.model.Film;
import com.example.demo.model.Production;
import com.example.demo.model.Show;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.stream.Collectors;

public class ConversionUtils {


    public static Employee dtoToEntity(EmployeeDTO dto) {
        Employee entity = new Employee();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setFrontend(dto.isFrontend());
        entity.setAge(dto.getAge());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static EmployeeResponseDTO entityToDTO(Employee entity) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setFrontend(entity.isFrontend());
        dto.setAge(entity.getAge());
        return dto;
    }

    private static void mapCommonProductionFields(ProductionDTO dto, Production production) {
        production.setTitle(dto.getTitle().trim());
        production.setYear(dto.getYear());
        production.setDirector(dto.getDirector().trim());
        production.setGenre(dto.getGenre().trim());
        Employee proposer = new Employee();
        proposer.setId(dto.getProposerId());
        production.setProposer(proposer);
        Employee register = new Employee();
        register.setId(dto.getRegisterId());
        production.setRegister(register);
        production.setRegisterDate(dto.getRegisterDate());
    }

    // Conversion from FilmDTO to Film entity
    public static Film dtoToEntity(FilmDTO dto) {
        Film film = new Film();
        mapCommonProductionFields(dto, film);
        film.setLength(dto.getLength());
        return film;
    }

    // Conversion from ShowDTO to Show entity
    public static Show dtoToEntity(ShowDTO dto) {
        Show show = new Show();
        mapCommonProductionFields(dto, show);
        show.setSeasons(dto.getSeasons());
        return show;
    }

    private static void mapEntityToCommonDTO(Production production, ProductionDTO dto) {
        dto.setTitle(production.getTitle());
        dto.setYear(production.getYear());
        dto.setDirector(production.getDirector());
        dto.setGenre(production.getGenre());
        dto.setAverageScore(production.getAverageScore());

        if (production.getProposer() != null) {
            dto.setProposerId(production.getProposer().getId());
        }
        if (production.getRegister() != null) {
            dto.setRegisterId(production.getRegister().getId());
        }
        dto.setRegisterDate(production.getRegisterDate());
    }

    public static FilmDTO entityToDTO(Film film) {
        FilmDTO dto = new FilmDTO();
        mapEntityToCommonDTO(film, dto);
        dto.setLength(film.getLength());
        return dto;
    }

    public static ShowDTO entityToDTO(Show show) {
        ShowDTO dto = new ShowDTO();
        mapEntityToCommonDTO(show, dto);
        dto.setSeasons(show.getSeasons());
        return dto;
    }

    public static List<Object> entityToDTO(List<Production> productions) {
        return productions.stream()
                .map(ConversionUtils::convertToDTO)
                .collect(Collectors.toList());
    }

    public static Object entityToDTO(Production production) {
        return convertToDTO(production);
    }

    private static Object convertToDTO(Production production) {
        if (production instanceof Film) {
            return ConversionUtils.entityToDTO((Film) production);
        } else if (production instanceof Show) {
            return ConversionUtils.entityToDTO((Show) production);
        } else {
            throw new IllegalArgumentException("Unsupported production type: " + production.getClass());
        }
    }




}
