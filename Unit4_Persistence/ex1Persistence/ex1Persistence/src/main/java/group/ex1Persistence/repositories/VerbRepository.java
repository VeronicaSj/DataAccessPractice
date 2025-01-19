package group.ex1Persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import group.ex1Persistence.entities.Verb;

@Repository
public interface VerbRepository extends CrudRepository<Verb, String>{

    @Query(value = "SELECT *, "+ "3" +" as clazz_ " + 
    "FROM  words w1_0 " +  
    "left join verb w1_2 on w1_0.german_word=w1_2.german_word " + 
    "WHERE w1_2.person = :person", nativeQuery = true)
    List<Verb> findByNumber(@Param("person")String number);
}