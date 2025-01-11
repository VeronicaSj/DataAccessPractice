package group.ex1Persistence.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import group.ex1Persistence.entities.Word;

@Repository()
public interface WordRepository extends CrudRepository<Word, String>{
    @Query(value = "SELECT *, "+ "0" +" as clazz_ " + 
    "FROM  words w1_0 " + 
    "left join sustantive w1_1 on w1_0.german_word=w1_1.german_word " + 
    "left join verb w1_2 on w1_0.german_word=w1_2.german_word " + 
    "WHERE w1_0.spanish_word = ?1", nativeQuery = true)
    ArrayList<Word> findBySpanishWord(String spanishWord);

}
