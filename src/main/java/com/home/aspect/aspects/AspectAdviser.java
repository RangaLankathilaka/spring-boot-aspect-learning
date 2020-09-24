package com.home.aspect.aspects;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.home.aspect.DTO.TestDTO;
import com.home.aspect.DTO.TestDTO2;

@Aspect
@Component
public class AspectAdviser {
	
	//return string
	@Around("execution(* com.home.aspect.HelloController.index (java.lang.String)) && args(sampleName)")
    public String aroundTestUsingMethod(ProceedingJoinPoint proceedingJoinPoint,String sampleName) throws Throwable {

        //LOGGER.info("A request was issued for a sample name: "+sampleName);

        sampleName = sampleName+"!dsdd";

        String sample = (String) proceedingJoinPoint.proceed(new Object[] {sampleName});

        return sample;
    }
	
	

	//return object for multi args
		@Around("execution(* com.home.aspect.aspects.testService.createSample2 (java.lang.String, java.lang.String)) "
				+ "&& args(sampleName, age)")
	    public TestDTO aroundTestUsingMethod(ProceedingJoinPoint proceedingJoinPoint,String sampleName, String age) throws Throwable {

	        //LOGGER.info("A request was issued for a sample name: "+sampleName);

			sampleName = "aaaaaaaaaaaaa";
			age = "32";
			//Object[] args = proceedingJoinPoint.getArgs();
	        TestDTO sample = (TestDTO) proceedingJoinPoint.proceed(new Object[] {sampleName, age});
	        sample.setName(sample.getName().toUpperCase());
	        sample.setAge("434");
	        return sample;
	    }
	
	@Before("execution(* com.home.aspect.HelloController.index (java.lang.String)) && args(sampleName)")
    public String beforeSampleCreation(String sampleName) {

		System.out.println("A request was issued for a sample name: "+sampleName);
		return "aspect";
    }

  @Before("@annotation(com.home.aspect.aspects.TestAnontation)")
  public void testSomethingBefore(JoinPoint joinPoint) {
	  
    System.out.println("Before execution - Subjects: " + Arrays.toString(getSujects(joinPoint)));
  }

  @AfterReturning(pointcut = "@annotation(com.home.aspect.aspects.TestAnontation)")
  public void testSomethingAfter(JoinPoint joinPoint) {
    System.out.println("After execution - Subjects: " + Arrays.toString(getSujects(joinPoint)));

  }

  @AfterThrowing(pointcut = "@annotation(com.home.aspect.aspects.TestAnontation)", throwing = "ex")
  public void testAfterThrowingAnException(JoinPoint joinPoint, Exception ex) {
    System.out.println("After throwing an exception - Subjects:" + Arrays.toString(getSujects(joinPoint)) + ex);
  }

  /**
   * TestAnontation is being used many times in testService
   * so annotation's name() is being used to identify the method need to be executed
   * @param proceedingJoinPoint
   * @return
   * @throws Throwable
   */
  @Around("@annotation(com.home.aspect.aspects.TestAnontation)")
  public Object testAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("Before in Around execution. -Name "+getName(proceedingJoinPoint)+" Subjects: " + Arrays.toString(getSujects(proceedingJoinPoint)));
    String sampleName = "Rangaaa";
    
    Object[] args = proceedingJoinPoint.getArgs();
    TestDTO testDTO = null;
    TestDTO2 testDTO2 = null;
    String name = getName(proceedingJoinPoint);
    if (name.equals("one")) {
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + args[0]);
        
    	testDTO = (TestDTO) proceedingJoinPoint.proceed(new Object[] {sampleName});
    	testDTO.setName(testDTO.getName().toUpperCase());
    	System.out.println("After in arround execution");
    	return testDTO;
    } else if (name.equals("three")){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + args[0]);
        TestDTO test = (TestDTO)args[0];
        String name2 = test.getName() + "asdf";
        test.setName(name2);
        testDTO2 = (TestDTO2) proceedingJoinPoint.proceed(new Object[] {test, args[1]});
        System.out.println("After in arround execution");
        return testDTO2;
    } else {
    	return null;
    }
    
 
    
  }
  
 
  
	@Around("execution(* com.home.aspect.aspects.testService.createSample1 (java.lang.String)) && args(sampleName)")
public TestDTO aroundTestUsingMethod1(ProceedingJoinPoint proceedingJoinPoint,String sampleName) throws Throwable {

    //LOGGER.info("A request was issued for a sample name: "+sampleName);

	sampleName = "Ranga";

    TestDTO sample = (TestDTO) proceedingJoinPoint.proceed(new Object[] {sampleName});
    sample.setName(sample.getName().toUpperCase());

    return sample;
}

 

  public String[] getSujects(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    System.out.println(method);
    TestAnontation myAnnotation = method.getAnnotation(TestAnontation.class);
    myAnnotation.name();
   
    
    return myAnnotation.subjects();
  }
  
  public String getName(JoinPoint joinPoint) {
	    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	    Method method = signature.getMethod();
	    TestAnontation myAnnotation = method.getAnnotation(TestAnontation.class);
	    
	    
	    return myAnnotation.name();
	  }
}
