package model;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private Integer age;

	public Person() {
		super();
	}

	public Person(String name, String surname, Integer age) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
