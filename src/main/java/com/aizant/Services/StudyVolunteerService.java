package com.aizant.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.DAO.BloodSampleCollectionDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.BloodSampleCollection;
import com.aizant.model.SampleTime;
import com.aizant.model.StudyVolunteer;

@Transactional
@Service
public class StudyVolunteerService implements IStudyVolunteerService {
	@Autowired
	private StudyVolunteerDAO studyVolunteerDao;

	@Autowired
	private BloodSampleCollectionDAO bloodSampleCollectionDao;

	@Transactional
	public StudyVolunteer get(String id, boolean showBloodSamples) {
		StudyVolunteer volunteer = studyVolunteerDao.get(id);
		volunteer.getStudy();
		if (showBloodSamples) {
			volunteer.getBloodSampleCollection();
			for (BloodSampleCollection sample : volunteer.getBloodSampleCollection()) {
				System.out.println("BLOOD SAMPLE COLLECTIONS" + sample.getId());
			}
		}
		return volunteer;
	}

	@Transactional
	public void delete(StudyVolunteer volunteer) {

		for (BloodSampleCollection sampleCollection : volunteer.getBloodSampleCollection()) {
			System.out.println("blood collection" + sampleCollection);
			bloodSampleCollectionDao.delete(sampleCollection);
			System.out.println("blood collection delete" + sampleCollection.getId());
		}
		studyVolunteerDao.delete(volunteer);
	}

	@Transactional
	public void deleteBloodSample(String volunteerId, int bloodSampleId) {
		StudyVolunteer volunteer = studyVolunteerDao.get(volunteerId);
		List<BloodSampleCollection> bloodSampleCollectionList = volunteer.getBloodSampleCollection();

		for (BloodSampleCollection bloodSample : bloodSampleCollectionList) {
			System.out.println("bloodSample" + bloodSample.getId());

			if (bloodSample.getId()==(bloodSampleId)) {
				System.out.println("bloodSample" + bloodSample);
				volunteer.getBloodSampleCollection().remove(bloodSample);
				bloodSampleCollectionDao.delete(bloodSample);
				break;
			}
		}
		studyVolunteerDao.saveOrUpdate(volunteer);
	}

	@Transactional
	public void addBloodSample(String volunteerId, BloodSampleCollection bloodSample) {
		StudyVolunteer volunteer = studyVolunteerDao.get(volunteerId);
		System.out.println("volunteers" + studyVolunteerDao.get(volunteerId));
		bloodSampleCollectionDao.saveOrUpdate(bloodSample);
		List<BloodSampleCollection> bloodSamples = volunteer.getBloodSampleCollection();
		if (bloodSamples != null) {
			volunteer.getBloodSampleCollection().add(bloodSample);
		} else {
			List<BloodSampleCollection> newSamples = new ArrayList<BloodSampleCollection>();
			newSamples.add(bloodSample);
			volunteer.setBloodSampleCollection(newSamples);
		}
		studyVolunteerDao.saveOrUpdate(volunteer);
	}

	@Transactional
    public void deleteFromStudy(String volunteerId) {	
    	StudyVolunteer volunteer = studyVolunteerDao.get(volunteerId);
    	System.out.println("Study volunteer to delete" + volunteer.getBloodSampleCollection());
    	for (BloodSampleCollection sampleCollection : volunteer.getBloodSampleCollection()) {
		System.out.println("blood collection" + sampleCollection);
    		bloodSampleCollectionDao.delete(sampleCollection);
    		System.out.println("blood collection delete" + sampleCollection.getId());
    	}
		studyVolunteerDao.delete(volunteer);
    }
	
	@Transactional
	public List<BloodSampleCollection> getBloodSamplesForTimePoints(StudyVolunteer volunteer, int period, int aliquot, List<SampleTime> sampleTimes)
	{
		System.out.println("SAMPLEEE" + period + " " + aliquot);
		List<BloodSampleCollection> samplesForTime = new ArrayList<BloodSampleCollection>();
		List<BloodSampleCollection> allSamples = volunteer.getBloodSampleCollection();
		System.out.println("volunteer sample size" + allSamples.size());
		for (SampleTime sampleTime : sampleTimes){
			System.out.println("TIME" + sampleTime.getTimePoint());
			for (BloodSampleCollection bloodSample : allSamples) {
				System.out.println("Blood sample info " + bloodSample.getAliquot() + " " + bloodSample.getPeriod() + " " + bloodSample.getTime());
				if (bloodSample.getAliquot() == aliquot && bloodSample.getPeriod() == period && bloodSample.getTime() == sampleTime.getTimePoint()) {
					System.out.println("EQUALLLLLL" + bloodSample.getId());
					samplesForTime.add(bloodSample);
				}
			}
		}
		
		System.out.println("SIZE OF SAMPLES" + samplesForTime.size());
		return samplesForTime;
	
	}

}
