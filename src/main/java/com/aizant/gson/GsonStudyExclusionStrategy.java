package com.aizant.gson;

import com.aizant.model.Study;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
 
public class GsonStudyExclusionStrategy implements ExclusionStrategy {
 
    public boolean shouldSkipField(FieldAttributes f) {
        return (f.getDeclaringClass() == Study.class && f.getName().equals("studyVolunteers"));
    }
 
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
 
}