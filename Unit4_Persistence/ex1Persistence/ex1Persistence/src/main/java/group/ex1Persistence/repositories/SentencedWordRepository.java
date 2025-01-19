package group.ex1Persistence.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group.ex1Persistence.entities.SentencedWord;
import group.ex1Persistence.entities.SentencedWordId;

@Repository
public interface SentencedWordRepository extends JpaRepository<SentencedWord, SentencedWordId> {
    /**
     * Returns an Object array ArrayList where each object array is composed by the 
     * word position, the german word and the spanish word
     * @param id sentence id
     * @return 
     */
    @Query(value = "select " 
                + "        sw1_0.word_position, w1_0.german_word," 
                + "        w1_0.spanish_word," 
                + "        0" +" as clazz_"
                + "    from" 
                + "        sentenced_word sw1_0" 
                + "    join" 
                + "        sentence s1_0" 
                + "            on s1_0.sentence_id=sw1_0.sentence_sentence_id" 
                + "    left join" 
                + "        words w1_0" 
                + "            on w1_0.german_word=sw1_0.word_german_word" 
                + "    left join" 
                + "        sustantive w1_1" 
                + "            on w1_0.german_word=w1_1.german_word" 
                + "    left join" 
                + "        verb w1_2" 
                + "            on w1_0.german_word=w1_2.german_word" 
                + "    where" 
                + "        s1_0.sentence_id = ?1", nativeQuery = true)
    ArrayList<Object[]> findFullSentenceArrayListById(int id);
}