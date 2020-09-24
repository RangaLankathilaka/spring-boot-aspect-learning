package com.home.aspect.aspects;

import org.springframework.stereotype.Service;

import com.home.aspect.DTO.TestDTO;
import com.home.aspect.DTO.TestDTO2;

@Service
public class testService {
	public String createSample(String sampleName) {

		return sampleName;

	}
	
	 @TestAnontation(subjects =  {"maths"}, name = "one")
	public TestDTO createSample1(String sampleName) {
		TestDTO testDTO = new TestDTO();
		testDTO.setName(sampleName);
		return testDTO;

	}
	 @TestAnontation(subjects =  {"maths"}, name = "Ranga")
	public TestDTO createSample2(String sampleName, String age) {
		return test(sampleName, "23");

	}
	 
	 @TestAnontation(subjects =  {"maths"}, name = "three")
		public TestDTO2 createSample3(TestDTO testDTO, String address) {
		 TestDTO2 testDTO2 = new TestDTO2();
		 testDTO2.setTestDTO(testDTO);
			return testDTO2;

		}
    public TestDTO test(String sampleName, String age) {
    	TestDTO testDTO = new TestDTO();
		testDTO.setName(sampleName);
		return testDTO;
    }
    
}
