package com.aizant.model;

import java.util.HashMap;

public class BarcodeMap {
private HashMap<String ,Object> barcodeMap;

public HashMap<String, Object> getBarcodeMap() {
	return barcodeMap;
}

public void setBarcodeMap(HashMap<String, Object> barcodeMap) {
	this.barcodeMap = barcodeMap;
}



public BarcodeMap(String barcode) {
	super();
	this.barcodeMap = new HashMap<>();
	barcodeMap=new HashMap<>();

	barcodeMap.put("barcode",barcode );
}
}
