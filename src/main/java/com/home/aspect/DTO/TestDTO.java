package com.home.aspect.DTO;

public class TestDTO extends BasicTestDTO{
	
	private String name;
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "TestDTO [name=" + name + ", age=" + age + "]";
	}
	
	
	

}
