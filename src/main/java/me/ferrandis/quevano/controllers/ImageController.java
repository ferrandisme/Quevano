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
import reactor.core.publisher.Mono;

import java.io.*;
import java.util.UUID;

@RestController
public class ImageController {

    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @PostMapping(value = "/v1/image/convert/{language}")
    public Mono<String> convertImage(@PathVariable String language, @RequestPart(value = "file") final FilePart rawFile) {
        File file;
        try {
            file = convertImage(rawFile);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Can't convert image");
        }
        try {
            return service.convertImage(file, language);
        } catch (TesseractException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(505), "Error reading image: " + e.getLocalizedMessage());
        }
    }


    private static File convertImage(FilePart rawImage) throws IOException, IOException {
        //TODO add  UUID.randomUUID()
        //TODO temp dir not works
        // File file = new File(System.getProperty("java.io.tmpdir") + rawImage.filename());
        File file = new File("src/resources/" + rawImage.filename());
        rawImage.transferTo(file);
        return file;
    }
}
