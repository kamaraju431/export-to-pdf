package com.aizant.Services;

import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;

public interface IStudyService {
    void update(Study study);
    void addVolunteerToStudy(Study study, StudyVolunteer volunteer);
    Study get(String id);
    void delete(String id);
    void add(Study study);
/*    void delete(Study deleteStudy);*/
}    
