package me.ferrandis.quevano.controllers;

import lombok.extern.slf4j.Slf4j;
import me.ferrandis.quevano.services.ImageService;
import net.sourceforge.tess4j.TesseractException;
import org.jboss.vfs.TempDir;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.UUID;

@Slf4j
@RestController
public class ImageController {

    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @PostMapping(value = "/v1/image/convert/{language}")
    public String convertImage(@PathVariable String language, @RequestPart(value = "file") final MultipartFile rawFile) {
        File file = convertImage(rawFile);
        try {
            String result = service.convertImage(file, language);
            log.info("Translated OK origin: {} , result: '{}'", file.getName(), result);
            return result;
        } catch (TesseractException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Error converting image: " + e.getLocalizedMessage());
        }
    }


    private File convertImage(MultipartFile rawImage) {
        String route = System.getProperty("java.io.tmpdir") + getFileName(rawImage);
        File file = new File(route);
        log.info("Saving image in: {}", route);
        try {
            rawImage.transferTo(file);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Error reading image: " + e.getLocalizedMessage());
        }
        return file;
    }

    private String getFileName(MultipartFile rawImage) {
        String format = "." + rawImage.getContentType().split("/")[1];
        return UUID.randomUUID() + format;
    }


}
