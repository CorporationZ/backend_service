package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.backend_service.service.S3Service;

import java.io.InputStream;

@RestController
@RequestMapping("/api/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final S3Service s3Service;

    @GetMapping("/view")
    public ResponseEntity<byte[]> viewResource(
            @RequestParam(value = "key", required = false) String key) {
        try (InputStream inputStream = s3Service.downloadFile(key)) {

            byte[] fileBytes = inputStream.readAllBytes();
            String contentType = s3Service.getContentType(key);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(fileBytes);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadResource(
            @RequestParam(value = "key", required = false) String key) {
        try {
            InputStream inputStream = s3Service.downloadFile(key);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + key)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
