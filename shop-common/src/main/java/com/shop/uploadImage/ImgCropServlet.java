package com.shop.uploadImage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImgCropServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("x: " + request.getParameter("x") + "," + request.getParameter("y") + "," + request.getParameter("w") + "," + request.getParameter("h"));

		Integer x = Integer.parseInt(request.getParameter("x"));
		Integer y = Integer.parseInt(request.getParameter("y"));
		Integer w = Integer.parseInt(request.getParameter("w"));
		Integer h = Integer.parseInt(request.getParameter("h"));
		
		String oldImgPath = request.getParameter("oldImgPath");

		String imgFileExt = request.getParameter("imgFileExt");
		String imgRoot =  request.getParameter("imgRoot");
		
		Integer width = Integer.parseInt(request.getParameter("width"));
		Integer height = Integer.parseInt(request.getParameter("height"));
		

		String webAppPath = getServletContext().getRealPath("/");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String imgFileId = formatter.format(new Date());
		String imgName =  imgRoot + imgFileId + System.currentTimeMillis() + "." + imgFileExt;			
		String createImgPath = webAppPath + imgName;
		

		webAppPath += oldImgPath;
		
		System.out.println("ԭͼƬ·��: " + webAppPath + ",��ͼƬ·��: " + createImgPath);
		

		ImageCut.abscut(webAppPath, createImgPath, x,y,w, h);
		
		 File f = new File(createImgPath);								
		 if(f.exists()){						
			 System.out.println("����ͼƬ��С: "+w+"*"+h+"ͼƬ�ɹ�!");
		 }	
		 
		String path = "/imgcrop.jsp?tag=1&oldImgPath="+oldImgPath+"&imgFileExt="+imgFileExt+"&imgRoot="+imgRoot + "&imgName="+imgName+"&height=" + height + "&width=" + width;
		System.out.println("imgCrop: " + path);
		request.getRequestDispatcher(path).forward(request,response);
	}

}
