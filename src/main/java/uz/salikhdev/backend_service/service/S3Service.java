package uz.salikhdev.backend_service.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.salikhdev.backend_service.entity.Resource;
import uz.salikhdev.backend_service.entity.ResourceStatus;
import uz.salikhdev.backend_service.entity.ResourceType;

import java.io.InputStream;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${services.s3.bucket-name}")
    private String bucketName;


    public Resource uploadFile(MultipartFile file, String folder) {
        long fileSize = file.getSize();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(fileSize);
        objectMetadata.setContentType(file.getContentType());

        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }


        String key = String.format("%s/%s.%s", folder, UUID.randomUUID(), extension);
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName, key, file.getInputStream(), objectMetadata
            );
            s3Client.putObject(putObjectRequest);
        } catch (Exception e) {
            log.error("Save error : {}", e.getMessage());
            throw new RuntimeException("Save error !");
        }

        Resource resource = new Resource();
        resource.setKey(key);
        resource.setSize(BigInteger.valueOf(fileSize));
        resource.setCreatedAt(LocalDateTime.now());
        resource.setStatus(ResourceStatus.ACTIVE);
        resource.setType(ResourceType.getResourceType(extension));
        resource.setName(file.getOriginalFilename());

        return resource;
    }

    public InputStream downloadFile(String fileKey) {
        try {
            S3Object s3Object = s3Client.getObject(bucketName, fileKey);
            return s3Object.getObjectContent();

        } catch (Exception e) {
            log.error("Error while downloading file from S3", e);
            throw new RuntimeException("Error while downloading file from S3");
        }
    }

    public String getContentType(String fileKey) {
        try {
            ObjectMetadata metadata = s3Client.getObjectMetadata(bucketName, fileKey);
            return metadata.getContentType();

        } catch (Exception e) {
            log.error("Error while getting content type from S3", e);
            throw new RuntimeException("Error while getting content type from S3");
        }
    }

    public void deleteFile(String fileKey) {
        s3Client.deleteObject(bucketName, fileKey);
    }


}
