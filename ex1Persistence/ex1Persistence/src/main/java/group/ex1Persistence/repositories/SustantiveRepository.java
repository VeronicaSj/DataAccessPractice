package group.ex1Persistence.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import group.ex1Persistence.entities.Sustantive;

@Repository
public interface SustantiveRepository extends CrudRepository<Sustantive, String>{
    @Query(value = "SELECT *, "+ "2" +" as clazz_ " + 
    "FROM  sustantive w1_0 " + 
    "left join words w1_1 on w1_0.german_word=w1_1.german_word " + 
    "WHERE w1_0.spanish_gender = ?1", nativeQuery = true)
    ArrayList<Sustantive> findBySpanishGender(String spanishGender);
}
