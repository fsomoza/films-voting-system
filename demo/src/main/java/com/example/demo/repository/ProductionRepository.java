package com.example.demo.repository;

import com.example.demo.model.Film;
import com.example.demo.model.Production;
import com.example.demo.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
    @Query("SELECT p FROM Production p WHERE prod_type(p) = FILM")
    List<Film> findAllFilms();

    @Query("SELECT p FROM Production p WHERE prod_type(p) = SHOW")
    List<Show> findAllShows();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Production p WHERE prod_type = 'FILM' AND p.title = :title")
    boolean existsFilmByName(@Param("title") String title);


}
