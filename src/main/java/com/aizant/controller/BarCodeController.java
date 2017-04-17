package com.aizant.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aizant.DAO.BloodSampleCollectionDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.Services.IStudyVolunteerService;
import com.aizant.model.BloodSampleCollection;
import com.aizant.model.SampleTime;
import com.aizant.model.StudyVolunteer;



@Controller
@Transactional
public class BarCodeController {
	
	@Autowired
	IStudyVolunteerService studyVolunteerService;
	
	@Autowired
	BloodSampleCollectionDAO bloodSampleCollectionDao;
	
	@Autowired
	StudyVolunteerDAO studyVolunteerDao;
	
	public static SampleTime findSampleTime(Collection<SampleTime> sampleTimes, String sampleTimeId) {
	    for(SampleTime sampleTime : sampleTimes) {
	        if(sampleTime != null && sampleTime.getId().equals(sampleTimeId)) {
	            return sampleTime;
	        }
	    }
	    return null;
	}
	
	@RequestMapping(value = "/barcode", method = RequestMethod.POST)
	public @ResponseBody String scanCode(@RequestBody String scannedCode) {
		System.out.println("SCANNED CODE" + scannedCode);
		String[] parts = scannedCode.split("_");
		String volunteerId = parts[0];
		String aliquot = parts[1];
		String period = parts[2];
		String timepointId = parts[3];
		
		StudyVolunteer studyVolunteer = studyVolunteerService.get(volunteerId);
		SampleTime timepointConfig = findSampleTime(studyVolunteer.getStudy().getSampleTime(), timepointId);
		
		BloodSampleCollection scannedSample = new BloodSampleCollection();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date timestamp = new Date();

		scannedSample.setVolunteerId(volunteerId);
		scannedSample.setDate(dateFormat.format(timestamp));
		scannedSample.setScanTime(timeFormat.format(timestamp));
		scannedSample.setAliquot(Integer.parseInt(aliquot));
		scannedSample.setPeriod(Integer.parseInt(timepointId));
		scannedSample.setTime(timepointConfig.getTimePoint());
		
		bloodSampleCollectionDao.saveOrUpdate(scannedSample);
		
		studyVolunteer.getBloodSampleCollection().add(scannedSample);
		studyVolunteerDao.saveOrUpdate(studyVolunteer);
		return "";
	}
}
