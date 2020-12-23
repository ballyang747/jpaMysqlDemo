package com.example.demo.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "testaaa", procedureName = "testaaa", parameters = { //name是JPA中的存储过程的名字, procedureName是数据库存储过程的名字
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "t11", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "t22", type = String.class)

               },resultClasses = {TestSp.class}
        )
})
@Data
@Table(name = "car_model")
public class TestSp {
    @Id
    @GeneratedValue//表示自增
    private Integer id;
    @Column(name="car_model")
    private String carModel;
    private  String remark;
    private  int status;
    @Column(name="create_time")
    private Date createTime;
    @Column(name="update_time")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
