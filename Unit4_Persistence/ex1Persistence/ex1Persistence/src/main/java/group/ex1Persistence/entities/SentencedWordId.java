package group.ex1Persistence.entities;

import java.io.Serializable;
import java.util.Objects;

public class SentencedWordId implements Serializable {
    
    private Sentence sentence;
    private int wordPosition;

    public SentencedWordId() {
    }

    public SentencedWordId(Sentence sentence, int wordPosition) {
        this.sentence = sentence;
        this.wordPosition = wordPosition;
    }



    public Sentence getSentenceId() {
        return sentence;
    }

    public void setSentenceId(Sentence sentenceId) {
        this.sentence = sentenceId;
    }

    public int getWordPosition() {
        return wordPosition;
    }

    public void setWordPosition(int wordPosition) {
        this.wordPosition = wordPosition;
    }

    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SentencedWordId{");
        sb.append("sentenceId=").append(sentence);
        sb.append(", wordPosition=").append(wordPosition);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.sentence);
        hash = 59 * hash + this.wordPosition;
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
        final SentencedWordId other = (SentencedWordId) obj;
        if (this.wordPosition != other.wordPosition) {
            return false;
        }
        return Objects.equals(this.sentence, other.sentence);
    }

    
}
