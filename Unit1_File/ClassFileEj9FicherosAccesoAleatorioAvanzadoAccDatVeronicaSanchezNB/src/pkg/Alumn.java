package pkg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Date;

/**
 *
 * @author veron
 */
public class Alumn {
    private int enrollment;//clave primaria e indice
    private String name;
    private float mark1Ev, mark2Ev, markFinal, markExtra;
    private Date birthD;
    
    public Alumn(){
        enrollment = Integer.MIN_VALUE;
        name = "";
        mark1Ev = mark2Ev = markFinal = markExtra = 0;
        birthD = new Date();
    }

    @Override
    public String toString() {
        return "Alumn{" + "enrollment=" + enrollment + ", name=" + name + ", mark1Ev=" + mark1Ev + ", mark2Ev=" + mark2Ev + ", markFinal=" + markFinal + ", markExtra=" + markExtra + ", birthD=" + birthD + '}';
    }
    
    public Alumn (int enrollment, String name, float mark1Ev, float mark2Ev, 
        float markFinal, float markExtra, Date birthD){
        this.enrollment = enrollment;
        this.name = name;
        this.mark1Ev = mark1Ev;
        this.mark2Ev = mark2Ev;
        this.markFinal = markFinal;
        this.markExtra = markExtra;
        this.birthD= birthD;
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

    public float getMarkFinal() {
        return markFinal;
    }

    public void setMarkFinal(float markFinal) {
        this.markFinal = markFinal;
    }

    public float getMarkExtra() {
        return markExtra;
    }

    public void setMarkExtra(float markExtra) {
        this.markExtra = markExtra;
    }

    public Date getBirthD() {
        return birthD;
    }

    public void setBirthD(Date birthD) {
        this.birthD = birthD;
    }
    
    
}
