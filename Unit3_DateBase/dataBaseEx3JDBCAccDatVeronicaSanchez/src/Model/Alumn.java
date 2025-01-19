package Model;

/**
 *
 * @author veron
 */
public class Alumn {
    private int enrollment;//clave primaria e indice
    private String name;
    private float mark1Ev, mark2Ev;
    
    public Alumn(){
        enrollment = Integer.MIN_VALUE;
        name = "";
        mark1Ev = mark2Ev = 0;
    }

    @Override
    public String toString() {
        return "Alumn{" + "enrollment=" + enrollment + ", name=" + name + ", mark1Ev=" + mark1Ev + ", mark2Ev=" + mark2Ev + '}';
    }
    
    public Alumn (int enrollment, String name, float mark1Ev, float mark2Ev){
        this.enrollment = enrollment;
        this.name = name;
        this.mark1Ev = mark1Ev;
        this.mark2Ev = mark2Ev;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMark1Ev() {
        return mark1Ev;
    }

    public void setMark1Ev(float mark1Ev) {
        this.mark1Ev = mark1Ev;
    }

    public float getMark2Ev() {
        return mark2Ev;
    }

    public void setMark2Ev(float mark2Ev) {
        this.mark2Ev = mark2Ev;
    }
}
