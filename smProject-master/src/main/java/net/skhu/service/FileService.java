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
   @Autowired FileMapper fileMapper;
   @Autowired private ServletContext servletContext;
   FileDTO fdto = new FileDTO();

   public int fileUpload(MultipartFile file){
      String relPath = "/img/upload/";
      String filePath = servletContext.getRealPath(relPath);
      File upDirectory = new File(filePath);
      if (!upDirectory.exists()) {
         upDirectory.mkdirs();
      }

      String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

      filePath += fileName;

      final File uploadFile = new File(filePath);
      System.out.println(uploadFile);
      if (uploadFile.exists()) {
         uploadFile.delete();
      }

      try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile))) {
         FileCopyUtils.copy(file.getInputStream(), stream);
      } catch (FileNotFoundException e) {
         return 0;
      } catch (IOException ioe) {
         return 0;
      }

      fdto.setPath(relPath + fileName);
      fileMapper.fileUpload(fdto);

      return fdto.getId();
   }

}