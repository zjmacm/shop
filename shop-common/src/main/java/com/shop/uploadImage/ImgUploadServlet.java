package com.shop.uploadImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ImgUploadServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	public static final String IMGROOT = "/uploads/";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String userWebAppPath = getWebAppPath();
		checkImageDir(userWebAppPath);
		
		String imgUploadPath = null;
		String imgWebAppPath = null;
		String imgFileExt = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String imgFileId = formatter.format(new Date());
		

		String width = null;
		String height = null;
		
		int imgWidth = 0;
		int imgHeight = 0;

		try {
			
			SmartUpload smartUpload = new SmartUpload();
			smartUpload.initialize(getServletConfig(), request, response);
			smartUpload.upload();
			Request rqest = smartUpload.getRequest();
			
			width = rqest.getParameter("width");
			if(null == width){
				width = "700";
			}
			height= rqest.getParameter("height");	
			if(null == height){
				height = "600";
			}
			
			imgWidth = Integer.parseInt(width);
			imgHeight = Integer.parseInt(height);
			
			

			int fileCounts =  smartUpload.getFiles().getCount();	
		
			for (int i = 0; i <fileCounts; i++) {
				com.shop.uploadImage.File myFile = smartUpload.getFiles().getFile(i);
				
				if (!myFile.isMissing()) {
					
					imgFileExt = myFile.getFileExt();
					imgFileId += i + System.currentTimeMillis() + "." + imgFileExt;
					

					imgWebAppPath = userWebAppPath + imgFileId;
					
					myFile.saveAs(imgWebAppPath);
					imgUploadPath = IMGROOT + imgFileId;
					
					BufferedImage src = ImageIO.read(new File(imgWebAppPath)); // �����ļ�
					int imgSrcWidth = src.getWidth(); // �õ�Դͼ��							 
					imgWidth = imgSrcWidth > imgWidth ? imgWidth : imgSrcWidth;
					
					int imgSrcHeight = src.getHeight(); // �õ�Դͼ��
					imgHeight = imgSrcHeight > imgHeight ? imgHeight : imgSrcHeight;
				
					ImageCut.scale(imgWebAppPath, imgWebAppPath,imgWidth,imgHeight);
					 File f = new File(imgWebAppPath);								
					 if(f.exists()){						
						 System.out.println("����"+imgWidth+"*"+imgHeight+"ͼƬ�ɹ�");
					 }					
				}
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		String path = "/imgcrop.jsp?tag=0&oldImgPath="+imgUploadPath+"&imgFileExt="+imgFileExt+"&imgRoot="+IMGROOT+"&width="+imgWidth+"&height="+imgHeight;
		System.out.println("path: "+path);
		request.getRequestDispatcher(path).forward(request,response);
		
	}
	
	private String getWebAppPath(){
		String webAppPath = this.getServletContext().getRealPath("/");		
		String userWebAppPath = webAppPath+IMGROOT;
		return userWebAppPath;
	}

	private void checkImageDir(String userWebAppPath) {		
		 File file = new File(userWebAppPath);
		 if(!file.exists()){
			 file.mkdir();
		 }
	}

}
