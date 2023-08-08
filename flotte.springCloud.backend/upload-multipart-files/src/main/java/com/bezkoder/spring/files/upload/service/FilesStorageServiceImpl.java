package com.bezkoder.spring.files.upload.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
  @Value("${storage.DirectoryPath}")
  public  String storageDirectoryPath;

  //private final Path root = Paths.get(storageDirectoryPath);

  @Override
  public void init() {
    Path root = Paths.get(storageDirectoryPath);
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    Path storageDirectory = Paths.get(storageDirectoryPath);
    if(!Files.exists(storageDirectory)){ // if the folder does not exist
      try {
        Files.createDirectories(storageDirectory); // we create the directory in the given storage directory path
      }catch (Exception e){
        e.printStackTrace();// print the exception
      }
    }

    Path destination = Paths.get(storageDirectory.toString() + "\\" + fileName);

    try {
      Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);// we are Copying all bytes from an input stream to a file

    } catch (IOException e) {
      e.printStackTrace();
    }
    // the response will be the download URL of the image



//    try {
//      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
//    } catch (Exception e) {
//      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
//    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path root = Paths.get(storageDirectoryPath);
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
   Path root = Paths.get(storageDirectoryPath);
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
  Path root = Paths.get(storageDirectoryPath);
    try {
      return Files.walk(root, 1).filter(path -> !path.equals(root)).map(root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

  @Override
  public byte[] getImageWithMediaType(String fileName) throws IOException {
//    return new byte[0];
    Path destination = Paths.get(storageDirectoryPath+"\\"+fileName);// retrieve the image by its name

    return IOUtils.toByteArray(destination.toUri());
  }

}
