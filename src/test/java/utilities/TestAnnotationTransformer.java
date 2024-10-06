package utilities;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestAnnotationTransformer implements IAnnotationTransformer {





    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
       // IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }




}


