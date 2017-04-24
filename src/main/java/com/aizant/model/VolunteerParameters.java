package com.aizant.model;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

public class VolunteerParameters {
	private String subreportPath;
	HashMap<String, Object> baseParameters;
	HashMap<String, List<JRDataSource>> baseDataSource;
	HashMap<String, List<JRDataSource>> baseDataSource1;
	HashMap<String, List<JRDataSource>> baseDataSource2;
	HashMap<String, List<JRDataSource>> baseDataSource3;
	HashMap<String, List<JRDataSource>> baseDataSource4;
	

	public String getSubreportPath() {
		return subreportPath;
	}

	public void setSubreportPath(String subreportPath) {
		this.subreportPath = subreportPath;
	}

	public HashMap<String, List<JRDataSource>> getBaseDataSource() {
		return baseDataSource;
	}

	public void setBaseDataSource(HashMap<String, List<JRDataSource>> baseDataSource) {
		this.baseDataSource = baseDataSource;
	}

	public HashMap<String, List<JRDataSource>> getBaseDataSource1() {
		return baseDataSource1;
	}

	public void setBaseDataSource1(HashMap<String, List<JRDataSource>> baseDataSource1) {
	this.baseDataSource1 = baseDataSource1;
	}

	public HashMap<String, List<JRDataSource>> getBaseDataSource2() {
		return baseDataSource2;
	}

	public void setBaseDataSource2(HashMap<String, List<JRDataSource>> baseDataSource2) {
		this.baseDataSource2 = baseDataSource2;
	}

	public HashMap<String, List<JRDataSource>> getBaseDataSource3() {
		return baseDataSource3;
	}

	public void setBaseDataSource3(HashMap<String, List<JRDataSource>> baseDataSource3) {
		this.baseDataSource3 = baseDataSource3;
	}

	public HashMap<String, List<JRDataSource>> getBaseDataSource4() {
		return baseDataSource4;
	}

	public void setBaseDataSource4(HashMap<String, List<JRDataSource>> baseDataSource4) {
		this.baseDataSource4 = baseDataSource4;
	}

	public HashMap<String, Object> getBaseParameters() {
		return baseParameters;
	}

	public void setBaseParameters(HashMap<String, Object> baseParameters) {
		this.baseParameters = baseParameters;
	}

}