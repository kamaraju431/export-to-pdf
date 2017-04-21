package com.aizant.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
	    System.out.println("NUM TIMEPOINTS" + sampleTimes.size() + " " + sampleTimeId);
		for(SampleTime sampleTime : sampleTimes) {
			System.out.println("TIMEPOINT ID" + sampleTime.getId());
	        if(sampleTime != null && sampleTime.getId().equals(sampleTimeId)) {
	        	System.out.println("FOUND A TIMEPOINT" + sampleTime.getTimePoint());
	            return sampleTime;
	        }
	    }
	    
	    System.out.println("NO TIMEPOINT FOUND");
	    return null;
	}
	
	@RequestMapping(value = "/barcode", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody String scanCode(@RequestBody String scannedCode) {
		System.out.println("SCANNED CODE" + scannedCode);
		String[] parts = scannedCode.split("_");
		System.out.println("part" + scannedCode);
		String volunteerId = parts[0];
		System.out.println("volunteerId" + volunteerId);
		String period = parts[1];
		System.out.println("period" + period);
		String aliquot = parts[2];
		System.out.println("aliquot" + aliquot);
		String timepointId = parts[3];
		System.out.println("timepoint" + timepointId);
		StudyVolunteer studyVolunteer = studyVolunteerService.get(volunteerId, true);
		SampleTime timepointConfig = findSampleTime(studyVolunteer.getStudy().getSampleTime(),timepointId);
		
		BloodSampleCollection scannedSample = new BloodSampleCollection();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date timestamp = new Date();

		scannedSample.setRegisterNumber(volunteerId);
		scannedSample.setDate(dateFormat.format(timestamp));
		scannedSample.setScanTime(timeFormat.format(timestamp));
		scannedSample.setAliquot(Integer.parseInt(aliquot));
		scannedSample.setPeriod(Integer.parseInt(period));
		scannedSample.setTime(timepointConfig.getTimePoint());
		System.out.println("date" + scannedSample.getDate());
		System.out.println("time" + scannedSample.getScanTime());

		bloodSampleCollectionDao.saveOrUpdate(scannedSample);
		
		if (studyVolunteer.getBloodSampleCollection().size() > 0) {
			studyVolunteer.getBloodSampleCollection().add(scannedSample);

		} else {
			List<BloodSampleCollection> bloodSamples = new ArrayList<BloodSampleCollection>();
			bloodSamples.add(scannedSample);
			studyVolunteer.setBloodSampleCollection(bloodSamples);
		}
		studyVolunteerDao.saveOrUpdate(studyVolunteer);
		return "";
	}
}