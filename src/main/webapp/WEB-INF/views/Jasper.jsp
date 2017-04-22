<%@  page contentType="application/pdf"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="net.sf.jasperreports.engine.data.*"%>

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

		JRDataSource studyDataSource = (JRDataSource)request.getAttribute("studyDataSource");
		HashMap<String, Object> studyParameters = (HashMap<String, Object>)request.getAttribute("studyParameters");
				
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