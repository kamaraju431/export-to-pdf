<%@  page contentType="application/pdf"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="net.sf.jasperreports.engine.data.*"%>
<%@ page import="com.aizant.model.Sample"%>
<%@ page import="com.aizant.model.Study"%>
<%@ page import="com.aizant.model.Aliquot"%>


<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springframework.context.annotation.Bean"%>

<%@ page
	import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%
	try {
		String jrxmlFile = session.getServletContext().getRealPath("/reports/AizantStudyVolunteerReport.jrxml");
		String volunteerReportFile = session.getServletContext().getRealPath("/reports/AizantVolunteerReport.jasper");
		String baseReportFile = session.getServletContext().getRealPath("/reports/AizantBaseReport.jasper");

		Study study = (Study) request.getAttribute("study");
		List<Aliquot> aliquots = new ArrayList<Aliquot>();
		for (int i = 0; i <= study.getAliquot(); i++) {
			aliquots.add(new Aliquot(i));
		}

		System.out.println("length" + aliquots.size());
		JRDataSource volunteerDataSource = new JRBeanCollectionDataSource(aliquots);
		HashMap<String, Object> studyParameters = new HashMap<String, Object>();
		

		HashMap<String, Object> volunteerParameters = new HashMap<String, Object>();

		HashMap<String, Object> baseParameters = new HashMap<String, Object>();
		baseParameters.put("Client_Id", study.getClientStudyId());
		baseParameters.put("Period_Num", study.getPeriods());
		baseParameters.put("Subject_Num", study.getStudyVolunteers().get(0).getVolunteerId());
		baseParameters.put("totalAliquots", study.getAliquot());
		
		volunteerParameters.put("subreportPath",  baseReportFile);
		volunteerParameters.put("baseParameters", baseParameters);

		List<Sample> sam = new ArrayList<Sample>();
		List<Sample> sam1 = new ArrayList<Sample>();
		List<Sample> sam2 = new ArrayList<Sample>();
		List<Sample> sam3 = new ArrayList<Sample>();
		List<Sample> sam4 = new ArrayList<Sample>();

		double[] sampleTimes = { 1.0, 2.0, 4.5,1.0, 2.0, 4.5,1.0, 2.0, 4.5,1.0, 2.0, 4.5 };
		for (int j = 0; j < sampleTimes.length; j++) {
			int remainder = j % 5;
			if (remainder== 0) {
				sam.add(new Sample(sampleTimes[j]));
			} else if (remainder == 1) {
				sam1.add(new Sample(sampleTimes[j]));
			} else if (remainder == 2) {
				sam2.add(new Sample(sampleTimes[j]));
			} else if (remainder == 3) {
				sam3.add(new Sample(sampleTimes[j]));
			} else {
				sam4.add(new Sample(sampleTimes[j]));
			}
		}

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
		volunteerParameters.put("baseDataSource", baseDataSources);
		volunteerParameters.put("baseDataSource1", baseDataSources1);
		volunteerParameters.put("baseDataSource2", baseDataSources2);
		volunteerParameters.put("baseDataSource3", baseDataSources3);
		volunteerParameters.put("baseDataSource4", baseDataSources4);
		
		
		studyParameters.put("volunteerParameters",volunteerParameters);
		studyParameters.put("subreportpath",volunteerReportFile);

		InputStream input = new FileInputStream(new File(jrxmlFile));
		JasperReport report = JasperCompileManager.compileReport(input);
		JasperPrint print = JasperFillManager.fillReport(report, volunteerParameters, volunteerDataSource);
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>