package com.sh.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sh.common.util.FileUploader;

import net.sf.json.JSONObject;

public class ImgFileController extends MultiActionController{

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try{
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(diskFactory);
	       RequestContext rc = new ServletRequestContext(request);

            String empCode = null; 
            String imgUrl = null; 

            List<FileItem> items = upload.parseRequest(rc); 
            System.out.println("items.size :"+items.size());	
 
            for(FileItem item : items){
            	if(item.isFormField()){
                    if("empCode".equals(item.getFieldName())){
                    	empCode = item.getString();
                    	System.out.println(item.getFieldName()+"="+item.getString());
                    }
                }else{
                    String fileName = item.getName(); 
                    System.out.println(item.getFieldName()+"="+item.getName());
                    if((fileName != null) && (item.getSize() > 0)){  
                        imgUrl = FileUploader.doFileUpload(item, empCode);
                    }		
                }
            }
            json.put("url", imgUrl);
            json.put("errorCode", 1);
            json.put("errorMsg", "success to save"); 
			
		}catch(Exception error){
        	
        	json.put("errorCode", -1);
            json.put("errorMsg", "fail to save");
            error.printStackTrace();
        }
        return null;
   }

}
