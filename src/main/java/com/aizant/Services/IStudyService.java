package com.aizant.Services;

import com.aizant.model.Study;

public interface IStudyService {
    void saveOrUpdate(Study study);
    Study get(String id);
    void delete(String id);
/*    void delete(Study deleteStudy);*/
}    
