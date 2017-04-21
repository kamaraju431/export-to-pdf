package com.aizant.Services;

import com.aizant.model.BloodSampleCollection;

public interface IBloodSampleService {

	BloodSampleCollection get(String id, boolean showBloodSamples);

	void deleteFromStudyVolunteer(String BloodSampleId);
}
