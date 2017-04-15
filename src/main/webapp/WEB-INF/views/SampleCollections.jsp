
<%@  page contentType="application/pdf"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="net.sf.jasperreports.engine.data.*"%>
<%@ page import="com.aizant.model.BloodSampleRecord"%>
<%@ page import="com.aizant.model.BloodSampleCollection"%>
<%@ page import="com.aizant.model.Study"%>



<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springframework.context.annotation.Bean"%>

<%@ page
	import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%
	try {
		BloodSampleCollection bloodCollections = (BloodSampleCollection) request
				.getAttribute("bloodCollection");
		System.out.println("Whats going onnnn");
		double time = bloodCollections.getTime();// (double[]) request.getAttribute("SampleTime");
		String date = bloodCollections.getDate();
		int period = bloodCollections.getPeriod();
		String scanTime = bloodCollections.getScanTime();
		String comments = bloodCollections.getComments();
		String volunteerId = bloodCollections.getVolunteerId();
		List<BloodSampleRecord> record = new ArrayList<BloodSampleRecord>();
		System.out.println("Whats going onnnn 2");

			record.add(new BloodSampleRecord(date, time, period, scanTime, comments, volunteerId));
			/* 	  record.add(new BloodSampleRecord(time));
				 record.add(new BloodSampleRecord(date));
				 record.add(new BloodSampleRecord(period));
				 record.add(new BloodSampleRecord(scanTime));
				 record.add(new BloodSampleRecord(comments));	
				 record.add(new BloodSampleRecord(volunteerId));  */

		JRDataSource jrDataSource = new JRBeanCollectionDataSource(record);
		System.out.println("Whats going onnnn 2");

		String jrxmlFile = session.getServletContext().getRealPath("/reports/SampleCollectionReport.jrxml");
		System.out.println("Whats going onnnn 3");

		HashMap<String, Object> CollectionParameters = new HashMap<String, Object>();
		/*  Study study = (Study) request.getAttribute("studyCollection"); 
		CollectionParameters.put("Client_Id", study.getClientStudyId());
		CollectionParameters.put("Period_Num", study.getPeriods());
		CollectionParameters.put("Subject_Num", study.getStudyVolunteers().get(0).getVolunteerId());  */
		CollectionParameters.put("collectionDataSource", jrDataSource);
		System.out.println("Whats going onnnn 4");

		InputStream input = new FileInputStream(new File(jrxmlFile));
		JasperReport report = JasperCompileManager.compileReport(input);

		System.out.println("Whats going onnnn 5");

		JasperPrint print = JasperFillManager.fillReport(report, CollectionParameters, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
