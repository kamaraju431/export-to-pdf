
<%@  page contentType="application/pdf"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import ="net.sf.jasperreports.engine.*"%>
<%@ page import ="net.sf.jasperreports.engine.data.*"%>
<%@ page import="com.aizant.model.Sample"%>
<%@ page import="com.aizant.model.Study"%>



<%@ page import ="java.io.*"%>
<%@ page import ="java.util.*"%>
<%@ page  import="java.util.Map"%>
<%@ page  import="org.springframework.context.annotation.Bean"%>

<%@ page  import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%

try{
	  Study study=(Study)request.getAttribute("study");

	 double[] sampleTimes={0.00, 1.00, 2.00,4.00};// (double[]) request.getAttribute("SampleTime");
	 String date=study.getDate();
	List<Sample> sam=new ArrayList<Sample>();
	  for (int i=0; i< sampleTimes.length; i++) {
		  sam.add(new Sample(i,date));	 
	  }
	 
	  JRDataSource jrDataSource=new JRBeanCollectionDataSource(sam);
	  System.out.println("date" + study.getDate());
	 
String jrxmlFile=session.getServletContext().getRealPath("/reports/SampleCollectionReport.jrxml");

HashMap<String, Object> CollectionParameters = new HashMap<String, Object>();

CollectionParameters.put("collectionDataSource",jrDataSource);
//CollectionParameters.put("date",study.getDate());
InputStream input = new FileInputStream(new File(jrxmlFile));
JasperReport report = JasperCompileManager.compileReport(input);

JasperPrint print = JasperFillManager.fillReport(report,CollectionParameters,jrDataSource);
JasperExportManager.exportReportToPdfStream(print,response.getOutputStream());
response.getOutputStream().flush();
response.getOutputStream().close();
}
catch(Exception e){
e.printStackTrace();
}

%>
