package com.active4j.hr.longche.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/fileUploaderController")
public class FileUploaderController {
	//private File upload; // 文件
	//private String uploadContentType; // 文件类型
	//private String uploadFileName; // 文件名
 
	/**
	 * 图片上传
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/imgUpload")
	public String imgUpload(HttpServletResponse response,HttpServletRequest request,@RequestParam("upload") MultipartFile upload,String uploadContentType) throws IOException {
 
		// 获得response,request
		
 
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// CKEditor提交的很重要的一个参数
		String callback = request.getParameter("CKEditorFuncNum");
		String expandedName = ""; // 文件扩展名
		uploadContentType = upload.getContentType();
		if (uploadContentType.equals("image/pjpeg")
				|| uploadContentType.equals("image/jpeg")) {
			// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			expandedName = ".jpg";
		} else if (uploadContentType.equals("image/png")
				|| uploadContentType.equals("image/x-png")) {
			// IE6上传的png图片的headimageContentType是"image/x-png"
			expandedName = ".png";
		} else if (uploadContentType.equals("image/gif")) {
			expandedName = ".gif";
		} else if (uploadContentType.equals("image/bmp")) {
			expandedName = ".bmp";
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
			out.println("</script>");
			return null;
		}
		if (upload.getSize() > 10000 * 1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",''," + "'文件大小不得大于10MB');");
			out.println("</script>");
			return null;
		}

		InputStream is =upload.getInputStream();
		//图片上传路径
		String uploadPath = request.getRealPath("/img/uploadImg");
		String fileName = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名
		fileName += expandedName;
		File file = new File(uploadPath);
		if (!file.exists()) { // 如果路径不存在，创建
			file.mkdirs();
		}
		File toFile = new File(uploadPath, fileName);
		OutputStream os = new FileOutputStream(toFile);
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();
 
		// 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名 
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",'" + request.getContextPath() + "/img/uploadImg/" + fileName + "','')");
		out.println("</script>");
		return null;
	}
	
	@RequestMapping("/fileupload")
	@ResponseBody
	public String fileupload(HttpServletResponse response,HttpServletRequest request,@RequestParam("file") MultipartFile upload) throws Exception {
		JSONObject json = new JSONObject();
		String expandedName = ""; // 文件扩展名
		String uploadContentType = upload.getContentType();
		if (uploadContentType.equals("image/pjpeg")
				|| uploadContentType.equals("image/jpeg")) {
			expandedName = ".jpg";
		} else if (uploadContentType.equals("image/png")
				|| uploadContentType.equals("image/x-png")) {
			expandedName = ".png";
		} else if (uploadContentType.equals("image/gif")) {
			expandedName = ".gif";
		} else if (uploadContentType.equals("image/bmp")) {
			expandedName = ".bmp";
		} else {
			json.put("msg", "图片上传格式错误");
			return json.toString();
		}
		InputStream is =upload.getInputStream();
		//图片上传路径
		String uploadPath = request.getRealPath("/commodityImg/uploadImg");
		String fileName = java.util.UUID.randomUUID().toString(); // 采用时间+UUID的方式随即命名
		fileName += expandedName;
		File file = new File(uploadPath);
		if (!file.exists()) { // 如果路径不存在，创建
			file.mkdirs();
		}
		File toFile = new File(uploadPath, fileName);
		OutputStream os = new FileOutputStream(toFile);
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();
		
		String imgPath =  request.getContextPath() + "/commodityImg/uploadImg/" + fileName;
		json.put("msg", "图片上传成功");
		json.put("filePath", imgPath);
		json.put("code", 0);
		return json.toString();
	}
 
}
