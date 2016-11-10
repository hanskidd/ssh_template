package com.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user" , catalog = "test")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5370796872638524212L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 11 ,unique = true)
	private Integer id;
	
	@Column(name = "name" , nullable = false , length = 50) 
	private String name;
	
	@Column(name = "sex")
	private Integer sex;
	
	@Column(name = "birthday" , columnDefinition="timestamp")
	private String birthday;

	
	
	public User() {
	}

	public User(Integer id, String name, Integer sex, String birthday) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + "]";
	}
	
}
