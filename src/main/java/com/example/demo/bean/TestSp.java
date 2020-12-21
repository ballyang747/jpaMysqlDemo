package com.example.demo.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "checkInputCarDetail", procedureName = "checkInputCarDetail", parameters = { //name是JPA中的存储过程的名字, procedureName是数据库存储过程的名字
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "carModelName", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "timesPerDay", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "toolManageNo", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "name", type = Integer.class)}
        )
})
@Data
@Table(name = "t_user1")
public class TestSp {
    @Id
    @GeneratedValue//表示自增
    private String name;
}
