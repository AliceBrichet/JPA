package monprojet.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    // Calcul de la population d'un pays en additionnant la population des villes de ce pays 
    @Query (value = "SELECT SUM(POPULATION) "
        + "FROM CITY "
        + "WHERE ID = :id",
        nativeQuery = true)
    public int getPopulation(Integer id);

    public interface PaysParPopulation {
        String getName();
        int getPopulation();
    }

    // Renvoie une liste Nom du pays, population 
    @Query (value = "SELECT NAME, SUM(POPULATION) "
        + "FROM CITY "
        + "WHERE COUNTRY_ID = CITY.ID "
        + "GROUP BY NAME",
        nativeQuery = true)
    public List<PaysParPopulation> PopulationOfEachCountry();

    
}
