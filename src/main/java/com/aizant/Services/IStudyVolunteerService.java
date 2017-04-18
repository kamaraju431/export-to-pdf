package com.aizant.Services;

import com.aizant.model.StudyVolunteer;

public interface IStudyVolunteerService {
    StudyVolunteer get(String id, boolean showBloodSamples);
    void delete(StudyVolunteer volunteer);
}    
