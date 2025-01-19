package group.ex1Persistence.entities;

import group.ex1Persistence.entities.EntitiesEnum.W_Gender;
import group.ex1Persistence.entities.EntitiesEnum.W_Number;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name="Sustantive")
public class Sustantive extends Word{

    @Enumerated(EnumType.STRING)
    @Column(name="number", length = 20)
    private W_Number number;

    @Enumerated(EnumType.STRING)
    @Column(name="germanGender", length = 20)
    private W_Gender germanGender;

    @Enumerated(EnumType.STRING)
    @Column(name="spanishGender", length = 20)
    private W_Gender spanishGender;

    

    public Sustantive(Word word, W_Number number, W_Gender germanGender,
            W_Gender spanishGender) {
        super(word);
        this.number = number;
        this.germanGender = germanGender;
        this.spanishGender = spanishGender;
    }

    public Sustantive() {
    }

    public W_Number getNumber() {
        return number;
    }

    public void setNumber(W_Number number) {
        this.number = number;
    }

    public W_Gender getGermanGender() {
        return germanGender;
    }

    public void setGermanGender(W_Gender germanGender) {
        this.germanGender = germanGender;
    }

    public W_Gender getSpanishGender() {
        return spanishGender;
    }

    public void setSpanishGender(W_Gender spanishGender) {
        this.spanishGender = spanishGender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sustantive{");
        sb.append("number=").append(number);
        sb.append(", germanGender=").append(germanGender);
        sb.append(", spanishGender=").append(spanishGender);
        sb.append('}');
        return sb.toString();
    }

}
