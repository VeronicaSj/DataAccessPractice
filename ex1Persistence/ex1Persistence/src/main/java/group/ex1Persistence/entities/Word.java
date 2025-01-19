package group.ex1Persistence.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity(name="Word")
@Table(name="Words")
@Inheritance(strategy=InheritanceType.JOINED)
public class Word {
    @Id
    private String germanWord;

    @Column(name="spanishWord", length = 500)
    private String spanishWord;
    
    @Column(name="userNotes", length = 500)
    private String userNotes;

    /* I COULD PUT HERE THE OTHER PART OF THE RELATION, BUT THAT WOULD 
     * CREATE A LOT OF DATA REPETITION, MAYBE THAT DATA REPETITION COULD BE 
     * USEFULL TO REDUCE LOADING TIME, BUT I CARE MORE ABOUT DISK SPACE, 
     * 
     */

    public Word( String spanishWord, String userNotes, String germanWord) {
        this.germanWord = germanWord;
        this.spanishWord = spanishWord;
        this.userNotes = userNotes;
    }

    public Word() {
    }

    public Word(Word word) {
        this.germanWord = word.getGermanWord();
        this.spanishWord = word.getSpanishWord();
        this.userNotes = word.getUserNotes();
    }

    public String getGermanWord() {
        return germanWord;
    }

    public String getSpanishWord() {
        return spanishWord;
    }

    public void setGermanWord(String germanWord) {
        this.germanWord = germanWord;
    }

    public void setSpanishWord(String spanishWord) {
        this.spanishWord = spanishWord;
    }
    
    
    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }


    @Override
    public String toString() {
        return "Word [germanWord=" + germanWord + ", spanishWord=" + spanishWord + "]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.germanWord);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        return Objects.equals(this.germanWord, other.germanWord);
    }
    
}
