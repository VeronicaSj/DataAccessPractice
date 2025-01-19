package group.ex1Persistence.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="SentencedWord")
@IdClass(SentencedWordId.class)
public class SentencedWord {
    @Id
    private int wordPosition;

    @ManyToOne(cascade=CascadeType.ALL)
    private Word word;

    @Id
    @ManyToOne(cascade=CascadeType.ALL)
    private Sentence sentence;

    
    

    public SentencedWord(Sentence sentence, int wordPosition, Word word) {
        this.sentence = sentence;
        this.wordPosition = wordPosition;
        this.word = word;
    }

    public SentencedWord() {
    }

    public Sentence getSentenceId() {
        return sentence;
    }

    public void setSentenceId(Sentence sentence) {
        this.sentence = sentence;
    }

    public int getWordPosition() {
        return wordPosition;
    }

    public void setWordPosition(int wordPosition) {
        this.wordPosition = wordPosition;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sentence{");
        sb.append("sentenceId=").append(sentence.toString());
        sb.append(", wordPosition=").append(wordPosition);
        sb.append(", word=").append(word);
        sb.append('}');
        return sb.toString();
    }
}
