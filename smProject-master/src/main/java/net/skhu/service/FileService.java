package net.skhu.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

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
	@Autowired
	private ServletContext servletContext;
	FileDTO fdto = new FileDTO();

	public int fileUpload(MultipartFile uploadedFile) {
		String relPath = "img/upload/";
		File upDirectory = new File(relPath);
		if (!upDirectory.exists()) {
			upDirectory.mkdirs();
		}

		String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();

		relPath += fileName;

		System.out.println(relPath+" 업로드");

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

}
