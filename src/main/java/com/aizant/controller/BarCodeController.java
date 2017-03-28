package com.aizant.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletResponse;

import org.krysalis.barcode4j.impl.datamatrix.DataMatrixBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class BarCodeController {

	
	@RequestMapping(value="/barcode", method = RequestMethod.GET)
	public void getCarById(HttpServletResponse  response) throws ServletException, IOException {
	    //get the car
		 DataMatrixBean bean = new DataMatrixBean();

		final int dpi = 150;
		OutputStream out = response.getOutputStream();
		response.setContentType("image/jpeg");
		
		try {
			
		    //Set up the canvas provider for monochrome PNG output 
		    BitmapCanvasProvider canvas = new BitmapCanvasProvider(
		            out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

		    //Generate the barcode
		    bean.generateBarcode(canvas, "123456");

		    //Signal end of generation
		    canvas.finish();
		} finally {
		    out.close();
		}
	}
	@RequestMapping("jasper")
	public ModelAndView display2() {
		ModelAndView jas = new ModelAndView("jasper");
		return jas;
	}
}
