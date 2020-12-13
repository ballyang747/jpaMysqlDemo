package com.example.demo.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name="test1")
@Entity
@Data
public class Test1Bean {
     @Id
     @GeneratedValue(strategy =  GenerationType.IDENTITY)
     private  Integer  id;
     private  String content;
     @Column(name="CreateDate")
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

    @Override
    public String toString() {
        return "Test1Bean{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", CreateDate=" + CreateDate +
                '}';
    }
}
