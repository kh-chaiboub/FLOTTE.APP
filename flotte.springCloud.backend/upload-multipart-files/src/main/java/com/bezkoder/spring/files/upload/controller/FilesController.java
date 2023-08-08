package com.bezkoder.spring.files.upload.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.bezkoder.spring.files.upload.message.ResponseMessage;
import com.bezkoder.spring.files.upload.model.FileInfo;
import com.bezkoder.spring.files.upload.service.FilesStorageService;

@Controller
@CrossOrigin(origins ="${corsConfiguration.Origins}")
public class FilesController {
//  @Value("${corsConfiguration.Origins}")
//  private String origins;
  @Autowired
  FilesStorageService storageService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile[] files) {

    String message = "";

    try {
      List<String> fileNames = new ArrayList<>();
      Arrays.asList(files).stream().forEach(file -> {
        storageService.save(file);
        fileNames.add(file.getOriginalFilename());
      });
      message = "Uploaded the file successfully: " + fileNames;
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file !";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
              .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
  @GetMapping(
          value = "/getImage/{imageName:.+}",
          produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE}
  )
  public @ResponseBody byte[] getImageWithMediaType(@PathVariable(name = "imageName") String fileName) throws IOException {
    return this.storageService.getImageWithMediaType(fileName);
  }
}
