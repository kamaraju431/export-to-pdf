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
		String jrxmlFile = session.getServletContext().getRealPath("/reports/VolunteerReport.jrxml");
		String subreportFile = session.getServletContext().getRealPath("/reports/BaseReport.jasper");

		
		Study study = (Study) request.getAttribute("study");
		List<Aliquot> aliquots = new ArrayList<Aliquot>();
		for (int i = 0; i <study.getAliquot(); i++) {
			aliquots.add(new Aliquot(i));
		}
		System.out.println("length"+study.getAliquot());
		JRDataSource volunteerDataSource = new JRBeanCollectionDataSource(aliquots);

		

		HashMap<String, Object> volunteerParameters = new HashMap<String, Object>();
		
		HashMap<String, Object> baseParameters = new HashMap<String, Object>();
		baseParameters.put("Client_Id", study.getClientStudyId());
		baseParameters.put("Period_Num", study.getPeriods());
		baseParameters.put("Subject_Num", study.getStudyVolunteers().get(0).getVolunteerId());
		baseParameters.put("totalAliquots", study.getAliquot());
		volunteerParameters.put("subreportPath", subreportFile);
		volunteerParameters.put("baseParameters", baseParameters);
		
		
		List<Sample> sam = new ArrayList<Sample>();
		double[] sampleTimes = { 1.0, 2.0, 4.5 };
		for (int j = 0; j < sampleTimes.length; j++) {
			sam.add(new Sample(sampleTimes[j]));
		}		
		volunteerParameters.put("baseDataSource", new JRBeanCollectionDataSource(sam));

		System.out.println("GOT THIS FARRR");
		InputStream input = new FileInputStream(new File(jrxmlFile));
		System.out.println("GOT THIS FARRR 0");
		JasperReport report = JasperCompileManager.compileReport(input);
		System.out.println("GOT THIS FARRR 1");
		JasperPrint print = JasperFillManager.fillReport(report, volunteerParameters, volunteerDataSource);
		System.out.println("GOT THIS FARRR 2");
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		System.out.println("GOT THIS FARRR 3");
	} catch (Exception e) {
		e.printStackTrace();
	}
%>