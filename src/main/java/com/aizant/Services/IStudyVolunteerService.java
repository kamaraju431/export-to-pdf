package com.aizant.Services;

import java.util.List;

import com.aizant.model.BloodSampleCollection;
import com.aizant.model.SampleTime;
import com.aizant.model.StudyVolunteer;

public interface IStudyVolunteerService {
    StudyVolunteer get(String id, boolean showBloodSamples);
    void delete(StudyVolunteer volunteer);
	public void deleteBloodSample(String volunteerId, int bloodSampleId);
    public void addBloodSample(String volunteerId,BloodSampleCollection bloodSample);
    void deleteFromStudy(String volunteerId);
    List<BloodSampleCollection> getBloodSamplesForTimePoints(
    		StudyVolunteer volunteer, int period, int aliquot, List<SampleTime> sampleTimes);
}