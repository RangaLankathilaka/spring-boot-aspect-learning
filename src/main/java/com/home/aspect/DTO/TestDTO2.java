package com.home.aspect.DTO;

public class TestDTO2 {
    private String hello;
    private TestDTO testDTO;
    
	public String getHello() {
		return hello;
	}


	public void setHello(String hello) {
		this.hello = hello;
	}

	

	public TestDTO getTestDTO() {
		return testDTO;
	}


	public void setTestDTO(TestDTO testDTO) {
		this.testDTO = testDTO;
	}


	@Override
	public String toString() {
		return "TestDTO2 [hello=" + hello + ", testDTO=" + testDTO + "]";
	}


	
    
}
