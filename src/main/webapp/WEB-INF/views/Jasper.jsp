<%@  page contentType="application/pdf"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="net.sf.jasperreports.engine.data.*"%>
<%@ page import="com.aizant.model.Sample"%>
<%@ page import="com.aizant.model.Study"%>
<%@ page import="com.aizant.model.Aliquot"%>
<%@ page import="com.aizant.model.StudyVolunteer"%>
<%@ page import="com.aizant.model.VolunteerParameters"%>
<%@ page import="com.aizant.model.SampleTime"%>


<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springframework.context.annotation.Bean"%>

<%@ page
	import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%
	try {
		String jrxmlFile = session.getServletContext().getRealPath("/reports/AizantStudyVolunteerReport.jrxml");
		String volunteerReportFile = session.getServletContext()
				.getRealPath("/reports/AizantVolunteerReport.jasper");
		String baseReportFile = session.getServletContext().getRealPath("/reports/AizantBaseReport.jasper");

		Study study = (Study) request.getAttribute("study");

		List<StudyVolunteer> studyVolunteers = study.getStudyVolunteers();
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
	

		volunteerParameters.setSubreportPath(baseReportFile);
		volunteerParameters.setBaseParameters(baseParameters);

		List<Sample> sam = new ArrayList<Sample>();
		List<Sample> sam1 = new ArrayList<Sample>();
		List<Sample> sam2 = new ArrayList<Sample>();
		List<Sample> sam3 = new ArrayList<Sample>();
		List<Sample> sam4 = new ArrayList<Sample>();
		System.out.println("hi hello");

		List<SampleTime> sampleTimes =(List<SampleTime>) request.getAttribute("sampleTimes");
		System.out.println("after hello" + sampleTimes);
		for (int j = 0; j < sampleTimes.size(); j++) {
			int remainder = j % 5;
			System.out.println("SAMPLE TIME" + sampleTimes.get(j));
			if (remainder == 0) {
				sam.add(new Sample(sampleTimes.get(j).getTimePoint()));
			} else if (remainder == 1) {
				sam1.add(new Sample(sampleTimes.get(j).getTimePoint()));
			} else if (remainder == 2) {
				sam2.add(new Sample(sampleTimes.get(j).getTimePoint()));
			} else if (remainder == 3) {
				sam3.add(new Sample(sampleTimes.get(j).getTimePoint()));
			} else {
				sam4.add(new Sample(sampleTimes.get(j).getTimePoint()));
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
		
		System.out.println("totalAliquots"+study.getAliquot());
		
		studyParameters.put("totalAliquots", study.getAliquot());
		
		
		System.out.println("iam in jasper");
		InputStream input = new FileInputStream(new File(jrxmlFile));
		JasperReport report = JasperCompileManager.compileReport(input);
		JasperPrint print = JasperFillManager.fillReport(report, studyParameters, studyDataSource);
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>