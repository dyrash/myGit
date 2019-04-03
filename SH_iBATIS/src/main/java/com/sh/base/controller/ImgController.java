package com.sh.base.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


public class ImgController extends MultiActionController {

	private ModelAndView modelAndView = null;
	private ModelMap modelMap = new ModelMap();

	public ModelAndView registEmpImg(HttpServletRequest request, HttpServletResponse response) {

		String path="C:/Users/hyun3/workspace_IBATIS/SH_iBATIS/src/main/webapp/Photos/empPhoto/";

        String empCode = request.getParameter("empCode");


        String spath="/Photos/empPhoto/";
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        MultipartFile multipartFile = null;
        while(iterator.hasNext()) {
            multipartFile = multipartHttpServletRequest.getFile((iterator.next()));
            if(!multipartFile.isEmpty()) {
                String fileName = multipartFile.getOriginalFilename();
                String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                File file = new File(path + empCode + fileExtension);
                try {
                    multipartFile.transferTo(file);
                    modelMap.put("url", spath+empCode+fileExtension);
                    modelMap.put("errorCode", 0);
                    modelMap.put("errorMsg", "save complete");
                } catch (IllegalStateException | IOException e) {
                    modelMap.put("errorCode", -1);
                    modelMap.put("errorMsg", "save failure");
                }
            }
        }
        modelAndView = new ModelAndView("jsonView", modelMap);
        return modelAndView;
	}

}