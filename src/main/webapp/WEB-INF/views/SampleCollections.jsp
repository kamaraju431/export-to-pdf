
<%@  page contentType="application/pdf"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="net.sf.jasperreports.engine.data.*"%>
<%@ page import="com.aizant.model.BloodSampleRecord"%>
<%@ page import="com.aizant.model.BloodSampleCollection"%>
<%@ page import="com.aizant.model.StudyVolunteer"%>
<%@ page import="com.aizant.model.Study"%>



<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springframework.context.annotation.Bean"%>

<%@ page
	import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%
	try {
		String jrxmlFile = session.getServletContext().getRealPath("/reports/SampleCollectionReport.jrxml");
		JRDataSource jrDataSource = (JRDataSource) request.getAttribute("dataSource");
		HashMap<String, Object> parameters = (HashMap<String, Object>) request.getAttribute("parameters");


		InputStream input = new FileInputStream(new File(jrxmlFile));
		JasperReport report = JasperCompileManager.compileReport(input);

		System.out.println("Whats going onnnn 5");

		JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
