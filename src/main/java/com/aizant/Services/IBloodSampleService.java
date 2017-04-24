package com.aizant.Services;

import com.aizant.model.BloodSampleCollection;

public interface IBloodSampleService {

	BloodSampleCollection get(int id, boolean showBloodSamples);

	void deleteFromStudyVolunteer(int BloodSampleId);
}
