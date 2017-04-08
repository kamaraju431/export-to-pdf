
<%@  page contentType="application/pdf"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import ="net.sf.jasperreports.engine.*"%>
<%@ page import ="net.sf.jasperreports.engine.data.*"%>
<%@ page import="com.aizant.model.BloodSampleCollection"%>


<%@ page import ="java.io.*"%>
<%@ page import ="java.util.*"%>
<%@ page  import="java.util.Map"%>
<%@ page  import="org.springframework.context.annotation.Bean"%>

<%@ page  import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%

try{
	/* double[] sampleTimes= (double[]) request.getAttribute("SampleTime");
	List<Sample> sam=new ArrayList<Sample>();
	  for (int i=0; i< 5; i++) {
			  sam.add(new Sample(i));
	  } */
	
String jrxmlFile=session.getServletContext().getRealPath("/reports/SampleReport.jrxml");
/* File subreportFile = new File(session.getServletContext().getRealPath("/reports/SubReport.jasper"));
String subreportPath = subreportFile.getAbsolutePath(); */

HashMap<String, Object> SampleParameters = new HashMap<String, Object>();
BloodSampleCollection bloodCollections =(BloodSampleCollection)request.getAttribute(" bloodCollections"); 
SampleParameters.put("date",request.getAttribute("date"));
SampleParameters.put("time",request.getAttribute("time")); 
SampleParameters.put("period",request.getAttribute("period")); 
SampleParameters.put("scan_Time",request.getAttribute("scanTime")); 
SampleParameters.put("volunteer_id",request.getAttribute("volunteerId")); 
SampleParameters.put("comment",request.getAttribute("comments")); 
		//JRDataSource jrDataSource=new JRBeanCollectionDataSource(sam);
InputStream input = new FileInputStream(new File(jrxmlFile));
JasperReport report = JasperCompileManager.compileReport(input);

JasperPrint print = JasperFillManager.fillReport(report,SampleParameters,new JREmptyDataSource());
JasperExportManager.exportReportToPdfStream(print,response.getOutputStream());
response.getOutputStream().flush();
response.getOutputStream().close();
}
catch(Exception e){
e.printStackTrace();
}

%>
