package com.aizant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aizant.DAO.BloodSampleCollectionDAO;
import com.aizant.DAO.StudyDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.Services.IStudyService;
import com.aizant.Services.IStudyVolunteerService;
import com.aizant.model.Aliquot;
import com.aizant.model.BloodSampleCollection;
import com.aizant.model.SampleTime;
import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;
import com.aizant.model.VolunteerParameters;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class JasperController {

	@Autowired
	private StudyDAO studyDAO;

	@Autowired
	IStudyService studyService;
	
	@Autowired
	IStudyVolunteerService studyVolunteerService;

	@Autowired
	private BloodSampleCollectionDAO bloodSampleCollectionDao;
	@Autowired
	private StudyVolunteerDAO study_VolunteerDao;
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping("/Jasper")
	@Transactional
	public ModelAndView Jasper(@RequestParam String id, ModelMap modelMap) {
		String volunteerReportFile = servletContext.getRealPath("/reports/AizantVolunteerReport.jasper");
		String baseReportFile = servletContext.getRealPath("/reports/AizantBaseReport.jasper");
		
		Study study = studyService.get(id);
		List<SampleTime> sampleTimes = study.getSampleTime();		
		List<StudyVolunteer> studyVolunteers = (List<StudyVolunteer>) study.getStudyVolunteers();
		
		JRDataSource studyDataSource = new JRBeanCollectionDataSource(studyVolunteers);
		List<Aliquot> aliquots = new ArrayList<Aliquot>();
		System.out.println("study aliquots" + study.getAliquot());
		for (int i = 0; i <= study.getAliquot(); i++) {
			Aliquot aliquot = new Aliquot(i);
			System.out.println("ADDINGGG aliquot " + i + " " + aliquot.getCurrentAliquot());
			aliquots.add(aliquot);
		}
		System.out.println("length of aliquots" + aliquots.size());
		HashMap<String, JRDataSource> volunteerDataSource = new HashMap<String, JRDataSource>();
		for (StudyVolunteer volunteer : studyVolunteers) {
			System.out.println("ID...." + volunteer.getId());
			volunteerDataSource.put(volunteer.getId(), new JRBeanCollectionDataSource(aliquots));
			System.out.println("Finished java datasource");
		}
		HashMap<String, Object> studyParameters = new HashMap<String, Object>();
		VolunteerParameters volunteerParameters = new VolunteerParameters();
		HashMap<String, Object> baseParameters = new HashMap<String, Object>();
		baseParameters.put("Client_Id", study.getClientStudyId());
		baseParameters.put("Period_Num", study.getPeriods());
		baseParameters.put("totalAliquots", study.getAliquot());
		/*
		 * baseParameters.put("id", study.getStudyVolunteers().get(0).getId());
		 */

		volunteerParameters.setSubreportPath(baseReportFile);
		volunteerParameters.setBaseParameters(baseParameters);
		List<SampleTime> sam = new ArrayList<SampleTime>();
		List<SampleTime> sam1 = new ArrayList<SampleTime>();
		List<SampleTime> sam2 = new ArrayList<SampleTime>();
		List<SampleTime> sam3 = new ArrayList<SampleTime>();
		List<SampleTime> sam4 = new ArrayList<SampleTime>();
		System.out.println("hi hello");
		System.out.println("sampletime ID" + sampleTimes.get(0).getId());
		for (int j = 0; j < sampleTimes.size(); j++) {
			int remainder = j % 5;
			System.out.println("SAMPLE TIME" + sampleTimes.get(j));
			if (remainder == 0) {
				sam.add(sampleTimes.get(j));
			} else if (remainder == 1) {
				sam1.add(sampleTimes.get(j));
			} else if (remainder == 2) {
				sam2.add(sampleTimes.get(j));
			} else if (remainder == 3) {
				sam3.add(sampleTimes.get(j));
			} else {
				sam4.add(sampleTimes.get(j));
			}

		}

		System.out.println("after hello1");
		HashMap<String, List<JRDataSource>> baseDataSourcesMap = new HashMap<String, List<JRDataSource>>();
		HashMap<String, List<JRDataSource>> baseDataSourcesMap1 = new HashMap<String, List<JRDataSource>>();
		HashMap<String, List<JRDataSource>> baseDataSourcesMap2 = new HashMap<String, List<JRDataSource>>();
		HashMap<String, List<JRDataSource>> baseDataSourcesMap3 = new HashMap<String, List<JRDataSource>>();
		HashMap<String, List<JRDataSource>> baseDataSourcesMap4 = new HashMap<String, List<JRDataSource>>();
		for (StudyVolunteer volunteer : studyVolunteers) {
			List<JRDataSource> baseDataSources = new ArrayList<JRDataSource>();
			List<JRDataSource> baseDataSources1 = new ArrayList<JRDataSource>();
			List<JRDataSource> baseDataSources2 = new ArrayList<JRDataSource>();
			List<JRDataSource> baseDataSources3 = new ArrayList<JRDataSource>();
			List<JRDataSource> baseDataSources4 = new ArrayList<JRDataSource>();
			for (int k = 0; k < aliquots.size(); k++) {
				baseDataSources.add(new JRBeanCollectionDataSource(sam));
				baseDataSources1.add(new JRBeanCollectionDataSource(sam1));
				baseDataSources2.add(new JRBeanCollectionDataSource(sam2));
				baseDataSources3.add(new JRBeanCollectionDataSource(sam3));
				baseDataSources4.add(new JRBeanCollectionDataSource(sam4));
			}
			baseDataSourcesMap.put(volunteer.getId(), baseDataSources);
			baseDataSourcesMap1.put(volunteer.getId(), baseDataSources1);
			baseDataSourcesMap2.put(volunteer.getId(), baseDataSources2);
			baseDataSourcesMap3.put(volunteer.getId(), baseDataSources3);
			baseDataSourcesMap4.put(volunteer.getId(), baseDataSources4);
		}
		volunteerParameters.setBaseDataSource(baseDataSourcesMap);
		volunteerParameters.setBaseDataSource1(baseDataSourcesMap1);
		volunteerParameters.setBaseDataSource2(baseDataSourcesMap2);
		volunteerParameters.setBaseDataSource3(baseDataSourcesMap3);
		volunteerParameters.setBaseDataSource4(baseDataSourcesMap4);
		studyParameters.put("volunteerDataSource", volunteerDataSource);
		studyParameters.put("volunteerParameters", volunteerParameters);
		studyParameters.put("subreportPath", volunteerReportFile);
		studyParameters.put("Client_Id", study.getClientStudyId());
		studyParameters.put("Period_Num", study.getPeriods());

		System.out.println("totalAliquots" + study.getAliquot());

		studyParameters.put("totalAliquots", study.getAliquot());

		modelMap.put("studyDataSource", studyDataSource);
		modelMap.put("studyParameters", studyParameters);

		return new ModelAndView("Jasper");
	}

	@RequestMapping("/SampleCollections")
	@Transactional
	public ModelAndView SampleCollections(@RequestParam String id, @RequestParam int period, ModelMap modelMap) {
		// BloodSampleCollection study = bloodSampleCollectionDao.get(id);
		StudyVolunteer studyVolunteer = studyVolunteerService.get(id, true);
		List<BloodSampleCollection> bloodCollections = new ArrayList<BloodSampleCollection>();
		for(BloodSampleCollection sample: studyVolunteer.getBloodSampleCollection()) {
			if (sample.getPeriod() == period && sample.getAliquot() == 0) {
				bloodCollections.add(sample);
			}
		}

		JRDataSource jrDataSource = new JRBeanCollectionDataSource(bloodCollections);

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Period_Num", period);
		parameters.put("Project_Num", studyVolunteer.getStudy().getClientStudyId());
		parameters.put("Subject_Num", studyVolunteer.getVolunteerId());
		parameters.put("Volume", studyVolunteer.getStudy().getSampleCollectionSize_in_ml());
		parameters.put("collectionDataSource", jrDataSource);
		System.out.println("Whats going onnnn 4");
		
		modelMap.put("parameters", parameters);
		modelMap.put("dataSource", jrDataSource);
		return new ModelAndView("SampleCollections");

	}
}
