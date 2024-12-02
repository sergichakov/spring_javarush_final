package com.javarush.jira.bugtracking.attachment;

import com.javarush.jira.common.error.IllegalRequestDataException;
import com.javarush.jira.common.error.NotFoundException;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@UtilityClass
public class FileUtil {
    public static class MyMultipartFile implements MultipartFile{
        private Path path;
        public MyMultipartFile(Path path){
            this.path=path;
        }
        @Override
        public String getName(){
            return path.getFileName().toString();
        }
        @Override
        public String getOriginalFilename() {
            return getName();
        }
        @Override
        public String getContentType() {
            try{
                //not ideal as mentioned in the comments of https://stackoverflow.com/a/8973468/10871900
                return Files.probeContentType(path);
            }catch(IOException e){
                return null;
            }
        }
        @Override
        public long getSize() {
            try {
                return Files.size(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public boolean isEmpty() {
            return getSize()==0;
        }
        @Override
        public byte[] getBytes() throws IOException {
            return Files.readAllBytes(path);
        }
        @Override
        public InputStream getInputStream() throws IOException {
            return Files.newInputStream(path);
        }
        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            transferTo(dest.toPath());
        }
        @Override
        public void transferTo(Path dest) throws IOException, IllegalStateException {
            Files.copy(path, dest);
        }
    }
    private static final String ATTACHMENT_PATH = "./attachments/%s/";

    public static void upload(MultipartFile multipartFile, String directoryPath, String fileName) {
        if (multipartFile.isEmpty()) {
            throw new IllegalRequestDataException("Select a file to upload.");
        }
        Path dir=Paths.get(directoryPath);
        if (Files.exists(dir) ){
            Path file = Paths.get(directoryPath+fileName);

            try {
                Files.write(file,
                        multipartFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public static Resource download(String fileLink) {
        Path path = Paths.get(fileLink);
        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new IllegalRequestDataException("Failed to download file " + resource.getFilename());
            }
        } catch (MalformedURLException ex) {
            throw new NotFoundException("File" + fileLink + " not found");
        }
    }

    public static void delete(String fileLink) {
        Path path = Paths.get(fileLink);
        try {
            Files.delete(path);
        } catch (IOException ex) {
            throw new IllegalRequestDataException("File" + fileLink + " deletion failed.");
        }
    }

    public static String getPath(String titleType) {
        return String.format(ATTACHMENT_PATH, titleType.toLowerCase());
    }
}
