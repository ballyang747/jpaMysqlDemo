package com.example.demo.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "checkInputCarDetail", procedureName = "checkInputCarDetail", parameters = { //name是JPA中的存储过程的名字, procedureName是数据库存储过程的名字
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "carModelName", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "timesPerDay", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "toolManageNo", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "nameresult", type = String.class)}
        )
})
@Data
@Table(name = "car_model")
public class TestSp {
    @Id
    @GeneratedValue//表示自增
    private Integer id;
}
