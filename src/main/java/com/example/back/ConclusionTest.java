package com.example.back;

import java.io.Serializable;

public class ConclusionTest implements Serializable {
    private static final long serialVersionUID = 1L;
    private Test test;
    private String conclusion;

    public ConclusionTest(String conclusion,Test test){
        this.conclusion = conclusion;
        this.test = test;
        test.setConclusionTest(this);
    }

    public String getConclusionTest(){
        return conclusion;
    }
    
}
