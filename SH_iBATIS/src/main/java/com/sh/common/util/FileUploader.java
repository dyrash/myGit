package com.sh.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;

public class FileUploader {


    public static String doFileUpload(FileItem fileItem, String empId) throws IOException{
        InputStream in = fileItem.getInputStream();
      
        String realFileName = fileItem.getName().substring(fileItem.getName().lastIndexOf("//") + 1);
      System.out.println(realFileName);
        String fileExt = realFileName.substring(realFileName.lastIndexOf("."));
        System.out.println(fileExt);
   
        String saveFileName = empId + fileExt;

        System.out.println(saveFileName);
        //String uploadPath ="C:/Apache Software Foundation/Tomcat 9.0_Tomcat_9/webapps/sh/ImgServer/empPhoto/";
        String uploadPath ="C:/Users/hyun3/workspace_IBATIS/SH_iBATIS/src/main/webapp/Photos/empPhoto";
       
        FileOutputStream fout = new FileOutputStream(uploadPath + saveFileName);
     
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = in.read(buffer, 0, 8192)) != -1)
            fout.write(buffer, 0, bytesRead);

        in.close();
        fout.close();
     
        return "/Photos/empPhoto/" + saveFileName;
       
    }
}
