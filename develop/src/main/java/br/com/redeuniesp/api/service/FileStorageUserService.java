package br.com.redeuniesp.api.service;

import br.com.redeuniesp.api.config.FileStorageConfig;
import br.com.redeuniesp.api.exceptions.FileNotFoundException;
import br.com.redeuniesp.api.exceptions.FileStorageException;
import br.com.redeuniesp.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageUserService {

    @Autowired
    private FileStorageConfig storageConfig;

    @Autowired
    private UserService userService;

    private Path fileStorageLocation;

    @Autowired
    public FileStorageUserService(FileStorageConfig fileStorageConfig) {


        Path path = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();


        this.fileStorageLocation = path;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException(
                    "Could not create the directory where the uploaded files will be stored!", e);
        }
    }

    public Path crateFileStorage (String nameEstablishment){

        Path path = Paths.get(storageConfig.getUploadDir())
                .toAbsolutePath().normalize();

        this.fileStorageLocation = Path.of(path + "/" + nameEstablishment);

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException(
                    "Could not create the directory where the uploaded files will be stored!", e);
        }
        return fileStorageLocation;
    }

    public String storeFile(MultipartFile file, String username) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Filename..txt
            if (filename.contains("..")) {
                throw new FileStorageException(
                        "Sorry! Filename contains invalid path sequence " + filename);
            }
            var storageLocation = crateFileStorage(username);
            Path targetLocation = storageLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (Exception e) {
            throw new FileStorageException(
                    "Could not store file " + filename + ". Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String filename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) return resource;
            else throw new FileNotFoundException("File not found");
        } catch (Exception e) {
            throw new FileNotFoundException("File not found" + filename, e);
        }
    }

    public Path getFileStorageLocation() {
        return fileStorageLocation;
    }
}