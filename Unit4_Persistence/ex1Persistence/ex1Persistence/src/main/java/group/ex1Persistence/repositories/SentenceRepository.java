package group.ex1Persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import group.ex1Persistence.entities.Sentence;


@Repository
public interface  SentenceRepository extends CrudRepository<Sentence, Integer >  {
    @Query(value = "SELECT user_notes from sentence where sentence_id = ?1", nativeQuery = true)
    String findUserNoteById(int id);
}
