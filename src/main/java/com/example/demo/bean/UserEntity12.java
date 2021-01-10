package com.example.demo.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "car_model")
public class UserEntity12 {


	@Column(name="id")
 	private Integer id;
	@Column(name="car_model")
	private String carModel;
	@Column(name="create_time")
	private Date createTime;
    @Id
	@Column(name="status")
    private Integer status;
	@Column(name="remark")
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "UserEntity1{" +
				"id=" + id +
				", carModel='" + carModel + '\'' +
				", createTime=" + createTime +
				", status=" + status +
				'}';
	}
}
