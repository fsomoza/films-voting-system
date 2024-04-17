package com.example.demo.validator;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.FilmDTO;
import com.example.demo.dto.ProductionDTO;
import com.example.demo.dto.ShowDTO;
import com.example.demo.model.Film;
import com.example.demo.model.Production;
import com.example.demo.model.Show;

import java.util.ArrayList;
import java.util.List;

public class ValidatorUtils {
    public static void validateEmployee(EmployeeDTO employee, boolean isUpdate) {
        List<String> errors = new ArrayList<>();

        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            errors.add("Name cannot be null or empty");
        }

        if (employee.getEmail() == null || !employee.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("Email is not in a valid format");
        }

        if (employee.getAge() < 16) {
            errors.add("Age must be 16 or higher");
        }

        if (!isUpdate) {
            if (employee.getPassword() == null || employee.getPassword().trim().isEmpty()) {
                errors.add("Password cannot be null or empty");
            } else {
                String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
                if (!employee.getPassword().matches(passwordRegex)) {
                    errors.add("Password must be at least 8 characters long and include a number, an uppercase letter, a lowercase letter, and a special character");
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }

    // Validate common production fields
    public static void validateProduction(ProductionDTO production) {
        List<String> errors = new ArrayList<>();
        if (production.getTitle() == null || production.getTitle().trim().isEmpty()) {
            errors.add("Title cannot be empty.");
        }
        if (production.getYear() <= 0) {
            errors.add("Year must be greater than 0.");
        }
        if (production.getDirector() == null || production.getDirector().trim().isEmpty()) {
            errors.add("Director cannot be empty.");
        }
        if (production.getGenre() == null || production.getGenre().trim().isEmpty()) {
            errors.add("Genre cannot be empty.");
        }

        if (production.getRegisterDate() == null) {
            errors.add("Register date cannot be null.");
        }
        if (production.getProposerId() == null) {
            errors.add("Proposer cannot be null.");
        }
        if (production.getRegisterId() == null) {
            errors.add("Register cannot be null.");
        }
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }


    public static void validateShow(ShowDTO show) {
        validateProduction(show);
        List<String> errors = new ArrayList<>();
        if (show.getSeasons() <= 0) {
            errors.add("Seasons must be greater than 0.");
        }
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }

    public static void validateFilm(FilmDTO film) {
        validateProduction(film);
        List<String> errors = new ArrayList<>();
        if (film.getLength() <= 0) {
            errors.add("Film length must be greater than 0 minutes.");
        }
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }
}
