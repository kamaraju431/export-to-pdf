package com.aizant.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.DAO.BloodSampleCollectionDAO;
import com.aizant.DAO.SampleTimeDAO;
import com.aizant.DAO.StudyDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.BloodSampleCollection;
import com.aizant.model.SampleTime;
import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;

@Transactional
@Service
public class StudyService implements IStudyService {
	@Autowired
	private StudyDAO studyDao;

	@Autowired
	private SampleTimeDAO sampleTimeDao;

	@Autowired
	private StudyVolunteerDAO studyVolunteerDao;
	
	@Autowired
	private BloodSampleCollectionDAO bloodSampleCollectionDao;

	@Autowired
	private IStudyVolunteerService studyVolunteerService;

	@Transactional
	public void add(Study study) {
		studyDao.saveOrUpdate(study);
		for (StudyVolunteer volunteer : study.getStudyVolunteers()) {
			volunteer.setStudy(study);
			addVolunteerToStudy(study, volunteer);
		}
	}
	
	@Transactional
	public void update(Study study) {
		studyDao.saveOrUpdate(study);
		for (StudyVolunteer volunteer : study.getStudyVolunteers()) {
			volunteer.setStudy(study);
			if (volunteer.getId() == null) {
				addVolunteerToStudy(study, volunteer);
			} else {
				studyVolunteerDao.saveOrUpdate(volunteer);
			}
		}
	}

	@Transactional
	public void addVolunteerToStudy(Study study, StudyVolunteer volunteer) {
		List<BloodSampleCollection> bloodSamples = new ArrayList<BloodSampleCollection>();
		for (int period = 0; period < study.getPeriods(); period++) {
			for (int aliquot = 0; aliquot <= study.getAliquot(); aliquot++) {
				for (SampleTime sampleTime : study.getSampleTime()) {
					BloodSampleCollection bloodSample = new BloodSampleCollection();
					bloodSample.setAliquot(aliquot);
					bloodSample.setPeriod(period + 1);
					bloodSample.setTime(sampleTime.getTimePoint());
					bloodSampleCollectionDao.saveOrUpdate(bloodSample);
					bloodSamples.add(bloodSample);
				}
			}
		}
		volunteer.setBloodSampleCollection(bloodSamples);
		studyVolunteerDao.saveOrUpdate(volunteer);
	}

	@Transactional
	public Study get(String id) {
		Study study = studyDao.get(id);
		study.getStudyVolunteers();
		study.getSampleTime();
		return study;
	}

	@Transactional
	public void delete(String id) {
		Study study = studyDao.get(id);
		System.out.println("study delete id" + study.getId());
		for (SampleTime sampleTime : study.getSampleTime()) {
			System.out.println(" SampleTime delete id" + sampleTime.getId());
		}

		for (StudyVolunteer studyVolunteer : study.getStudyVolunteers()) {
			studyVolunteerService.delete(studyVolunteer);
			System.out.println("volunteer delete" + studyVolunteer.getId());
		}
		System.out.println("at last");
		studyDao.delete(study);
		System.out.println("at last delete" + study.getId());
	}
}
