package group.ex1Persistence.entities;

import group.ex1Persistence.entities.EntitiesEnum.W_Number;
import group.ex1Persistence.entities.EntitiesEnum.W_Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity(name="Verb")
@Table(name="Verb")
public class Verb extends Word{

    @Enumerated(EnumType.STRING)
    @Column(name="number", length = 20)
    private W_Number number;

    @Enumerated(EnumType.STRING)
    @Column(name="person", length = 20)
    private W_Person person;

    //TODO 1: create more specific verbal time in verb Entity
    @Column(name="time", length = 200)
    String time;

    public Verb(Word word, W_Number number, W_Person person,
            String time) {
        super(word);
        this.number = number;
        this.person = person;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Verb [number=" + number + ", person=" + person + ", time=" + time + ", getGermanWord()="
                + getGermanWord() + ", getSpanishWord()=" + getSpanishWord() + "]";
    }

    public W_Number getNumber() {
        return number;
    }

    public void setNumber(W_Number number) {
        this.number = number;
    }

    public W_Person getPerson() {
        return person;
    }

    public void setPerson(W_Person person) {
        this.person = person;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Verb() {
    }
    
    

}
