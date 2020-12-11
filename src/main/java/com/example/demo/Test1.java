package com.example.demo;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;

@Table(name="Test1")
@Entity
public class Test1 {
     @Id
     @GeneratedValue(strategy =  GenerationType.IDENTITY)
     private  int  id;
     private  String content;
     private  Date CreateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
}
