package group.ex1Persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Sentence")
public class Sentence {
    @Id
    private int sentenceId;

    
    @Column(name="userNotes", length = 500)
    private String userNotes;

    /* I COULD PUT HERE THE OTHER PART OF THE RELATION, BUT THAT WOULD 
     * CREATE A LOT OF DATA REPETITION, MAYBE THAT DATA REPETITION COULD BE 
     * USEFULL TO REDUCE LOADING TIME, BUT I CARE MORE ABOUT DISK SPACE, 
     * 
     *      @OneToMany(fetch=FetchType.LAZY,  cascade = CascadeType.ALL)
     *      private List<SentencedWord> sentencedWordList;
     */


    

    public Sentence(int sentenceId, String userNotes) {
        this.sentenceId = sentenceId;
        setUserNotes(userNotes);
    }

    public Sentence() {
    }

    public int getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(int sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        if(userNotes==null){
            userNotes="";
        }
        this.userNotes = userNotes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sentence{");
        sb.append("sentenceId=").append(sentenceId);
        sb.append(", userNotes=").append(userNotes);
        sb.append('}');
        return sb.toString();
    }


}
