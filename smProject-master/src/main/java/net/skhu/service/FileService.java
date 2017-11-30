package net.skhu.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import net.skhu.dto.FileDTO;
import net.skhu.mapper.FileMapper;

@Service
public class FileService {
	@Autowired
	FileMapper fileMapper;
	FileDTO fdto = new FileDTO();

	public int fileUpload(MultipartFile uploadedFile) {
		String relPath = "img/upload/";
		File upDirectory = new File(relPath);
		if (!upDirectory.exists()) {
			upDirectory.mkdirs();
		}

		String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();

		relPath += fileName;

		final File uploadFile = new File(relPath);
		if (uploadFile.exists()) {
			uploadFile.delete();
		}

		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile))) {
			FileCopyUtils.copy(uploadedFile.getInputStream(), stream);
		} catch (FileNotFoundException e) {
			return 0;
		} catch (IOException ioe) {
			return 0;
		}

		fdto.setPath(relPath);
		fileMapper.fileUpload(fdto);

		return fdto.getId();
	}

	public void fileDownload(int id, HttpServletResponse response)throws Exception {
		FileDTO uploadedfile = fileMapper.findOne(id);
	      if (uploadedfile == null)
	         return;
	      String fileName=(uploadedfile.getPath()).substring(11);

	      String filePath = (uploadedfile.getPath()).substring(0, 11);

	      filePath+=fileName;
	      System.out.println(filePath);

	      Path path = Paths.get(filePath);

	      uploadedfile.setData(Files.readAllBytes(path));

	      response.setContentType("application/octet-stream");
	      response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8") + ";");
	      try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
	         output.write(uploadedfile.getData());
	      }
	}

	public void excelDownload(HttpServletResponse response)throws Exception {
		FileDTO uploadedfile = new FileDTO();

	      String filePath = "WEB-INF/views/manager/m_excel.jsp";

	      Path path = Paths.get(filePath);

	      uploadedfile.setData(Files.readAllBytes(path));

	      response.setContentType("application/octet-stream");
	      response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("m_excel.jsp","UTF-8") + ";");
	      try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
	         output.write(uploadedfile.getData());
	      }
	}

	public String getFileName(int id) {
		FileDTO uploadedfile = fileMapper.findOne(id);
	    if (uploadedfile == null)
	    	return null;
		String fileName=(uploadedfile.getPath()).substring(11);
		return fileName;
	}

}
