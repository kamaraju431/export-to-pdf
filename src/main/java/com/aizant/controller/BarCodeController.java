package com.aizant.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
	public @ResponseBody String scanCode(@RequestBody int scannedSampleId) {
		BloodSampleCollection scannedSample = bloodSampleCollectionDao.get(scannedSampleId);		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date timestamp = new Date();

		scannedSample.setDate(dateFormat.format(timestamp));
		scannedSample.setScanTime(timeFormat.format(timestamp));
		System.out.println("date" + scannedSample.getDate());
		System.out.println("time" + scannedSample.getScanTime());

		bloodSampleCollectionDao.saveOrUpdate(scannedSample);
		return "";
	}
}