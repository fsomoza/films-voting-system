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
    @Query("SELECT p FROM Production p WHERE TYPE(p) = Film")
    List<Film> findAllFilms();

    @Query("SELECT p FROM Production p WHERE TYPE(p) = Show")
    List<Show> findAllShows();

    @Query("SELECT p FROM Production p WHERE p.id = :id")
    Production findProductionById(@Param("id") long id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Production p WHERE TYPE(p) = Film AND p.title = :title AND p.id <> :id")
    boolean filmExistsByTitleAndIdNot(@Param("title")String title, @Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Production p WHERE TYPE(p) = Show AND p.title = :title AND p.id <> :id")
    boolean showExistsByTitleAndIdNot(@Param("title")String title, @Param("id") Long id);

    @Query("SELECT p FROM Production p WHERE " +
            "(:genre IS NULL OR p.genre = :genre) AND " +
            "(:rating IS NULL OR p.averageScore >= :rating) AND " +
            "(:year IS NULL OR p.year = :year) AND " +
            "TYPE(p) IN (Film, Show)")
    List<Production> findAllFilmsAndShows(@Param("genre") String genre,
                                          @Param("rating") Double rating,
                                          @Param("year") Integer year);



    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Production p WHERE prod_type = :prodType AND p.title = :title")
    boolean existsProductionByName(@Param("title") String title, @Param("prodType") String prodType);

    @Query("SELECT p FROM Production p WHERE TYPE(p) = Film AND p.id = :id")
    Film findFilmById(@Param("id") long id);

    @Query("SELECT p FROM Production p WHERE TYPE(p) = Show AND p.id = :id")
    Show findShowById(@Param("id") long id);


}
