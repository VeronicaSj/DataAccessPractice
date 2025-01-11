package group.ex1Persistence.entities;

public class EntitiesEnum {
    public enum W_Gender {
        MASCULINE, FEMENINE, NEUTER;
    }
    public enum W_Number {
        PLURAL, SINGULAR;
    }
    public enum W_Person {
        FIRST, SECOND, THIRD;
    }

    //TODO 1: create more specific verbal time in verb Entity
    public enum W_Sp_Time {}
    
    public enum W_Ge_Time {
        FIRST, SECOND, THIRD;
    }
}
