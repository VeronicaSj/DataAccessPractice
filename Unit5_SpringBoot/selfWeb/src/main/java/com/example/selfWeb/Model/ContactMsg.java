package com.example.selfWeb.Model;

import java.util.Date;

import org.springframework.lang.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class ContactMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String email;

    @NonNull
    private String subject;

    @NonNull
    @Lob
    private String msg;

    private Date date;

    public ContactMsg() {
    }

    public ContactMsg(String email, String subject, String msg) {
        this.email = email;
        this.subject = subject;
        this.msg = msg;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("contactMsg{");
        sb.append("id=").append(id);
        sb.append(", email=").append(email);
        sb.append(", subject=").append(subject);
        sb.append(", msg=").append(msg);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }

    
    
}
