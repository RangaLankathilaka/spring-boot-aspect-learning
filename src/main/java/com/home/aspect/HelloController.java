package com.home.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.aspect.DTO.TestDTO;
import com.home.aspect.DTO.TestDTO2;
import com.home.aspect.aspects.TestAnontation;
import com.home.aspect.aspects.testService;

@RestController
public class HelloController {
	@Autowired
	private testService testService;

    @RequestMapping("/")
    @TestAnontation(subjects = {"maths"}, name = "Ranga")
    public String index(String sampleName) {
    	
        return testService.createSample(sampleName);
    }
    
    
    @RequestMapping("/test")
   
    public TestDTO sample1(String sampleName) {
    	
        return testService.createSample1("test");
    }
    
    @RequestMapping("/test-two")
    @TestAnontation(subjects = {"maths"}, name = "Ranga")
    public TestDTO sample2(String sampleName, String age) {
    	
        return testService.createSample2(sampleName, age);
    }
    
    @RequestMapping("/test-obj")
    
    public TestDTO2 sample3(String sampleName) {
    	TestDTO testDTO = new TestDTO();
    	testDTO.setAge("4566777");
        return testService.createSample3(testDTO, "Ingiriya");
    }

    @RequestMapping("/throwException")
    @TestAnontation(subjects = {"maths", "science"}, name = "Ranga")
    public String throwAnException() throws Exception {
        throw new Exception("Hello from exception!");
    }
    
    public String createSample(String sampleName) {

        

        return "test";
    }
}