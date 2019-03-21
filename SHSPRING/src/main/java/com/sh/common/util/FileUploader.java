package com.sh.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;

public class FileUploader {


    public static String doFileUpload(FileItem fileItem, String empId) throws IOException{
        InputStream in = fileItem.getInputStream();
      
        String realFileName = fileItem.getName().substring(fileItem.getName().lastIndexOf("//") + 1);
      
        String fileExt = realFileName.substring(realFileName.lastIndexOf("."));
   
        String saveFileName = empId + fileExt;

        //String uploadPath ="C:/Apache Software Foundation/Tomcat 9.0_Tomcat_9/webapps/YumSpring/ImgServer/empPhoto/";
        String uploadPath ="C:/Users/MeatRain/2nd_project_account/YumSpring/src/main/webapp/ImgServer/empPhoto/";
       
        FileOutputStream fout = new FileOutputStream(uploadPath + saveFileName);
     
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = in.read(buffer, 0, 8192)) != -1)
            fout.write(buffer, 0, bytesRead);

        in.close();
        fout.close();
     
        return "/ImgServer/empPhoto/" + saveFileName;
       
    }
}
