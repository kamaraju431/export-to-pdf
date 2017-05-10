package com.aizant.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aizant.DAO.BloodSampleCollectionDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.DAO.UserDAO;
import com.aizant.Services.IStudyVolunteerService;
import com.aizant.dto.ScanRequestDTO;
import com.aizant.model.BloodSampleCollection;
import com.aizant.model.SampleTime;
import com.aizant.model.Study;
import com.aizant.model.User;
import com.google.gson.Gson;



@Controller
@Transactional
public class BarCodeController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	IStudyVolunteerService studyVolunteerService;
	
	@Autowired
	BloodSampleCollectionDAO bloodSampleCollectionDao;
	
	@Autowired
	StudyVolunteerDAO studyVolunteerDao;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
    static final String TOKEN =  "30652800-6672-41a8-9605-292dbdc95734";
	
	
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
	public @ResponseBody String scanCode(HttpServletRequest request,
			@RequestBody ScanRequestDTO scanRequest, BindingResult result) throws Exception {
		
		String token = scanRequest.getToken();
		if (!TOKEN.equals(token)) {
			throw new Exception("Unauthorized client!");
		}
		
		String username = scanRequest.getUsername();
		String password = scanRequest.getPassword();
		
		User user = userDao.getByUsername(username);
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new Exception("Invalid Credentials!");
		};
		
		int scannedSampleId = Integer.parseInt(scanRequest.getScannedId());
		BloodSampleCollection scannedSample = bloodSampleCollectionDao.get(scannedSampleId);	
		if (scannedSample == null) {
			throw new Exception("Invalid sample!");
		} 
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date timestamp = new Date();

		scannedSample.setDate(dateFormat.format(timestamp));
		scannedSample.setScanTime(timeFormat.format(timestamp));
		scannedSample.setScannedById(user.getId());
		scannedSample.setScannedByName(username);

		bloodSampleCollectionDao.saveOrUpdate(scannedSample);
		return "";
	}
}