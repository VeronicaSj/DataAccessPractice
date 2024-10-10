/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author veron
 */
public class Alumn {
    private int enrollment;//clave primaria e indice
    private String nombre;
    private float not1Ev, nota2Ev, notaFinal, notaExtra;
    private Date birthD;
    
    public Alumn(){
        enrollment = Integer.MIN_VALUE;
        nombre = null;
        not1Ev = nota2Ev = notaFinal = notaExtra = 0;
        birthD = null;
    }
    
    public Alumn (int nMatricula, String nombre, float not1Ev, float nota2Ev, 
        float notaFinal, float notaExtra, Date birthD){
        this.enrollment = nMatricula;
        this.nombre = nombre;
        this.not1Ev = not1Ev;
        this.nota2Ev = nota2Ev;
        this.notaFinal = notaFinal;
        this.notaExtra = notaExtra;
        this.birthD= birthD;
    }
 
    public void setNMatricula(int nMatricula){this.enrollment=nMatricula;}
    public void setNombre(String nombre){this.nombre=nombre;}
    public void setNot1Ev(float not1Ev){this.not1Ev=not1Ev;}
    public void setNota2Ev(float nota2Ev){this.nota2Ev=nota2Ev;}
    public void setNotaFinal(float notaFinal){this.notaFinal=notaFinal;}
    public void setNotaExtra(float notaExtra){this.notaExtra=notaExtra;}
    public void setbirthD(Date birthD){this.birthD=birthD;}
    

    public int getNMatricula(){ return this.enrollment;}
    public String getNombre(){ return this.nombre;}
    public float getNot1Ev(){ return this.not1Ev;}
    public float getNot2Ev(){ return this.nota2Ev;}
    public float getNotaFinal(){ return this.notaFinal;}
    public float getNotaExtra(){ return this.notaExtra;}
    public Date getbirthD(){return this.birthD;}
    
    @Override
    public String toString() {
        return "Alumn{" + "nMatricula=" + enrollment + ", nombre=" + nombre+'}';
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
        final Alumn other = (Alumn) obj;
        if (this.enrollment != other.enrollment) {
            return false;
        }
        return true;
    }
}
