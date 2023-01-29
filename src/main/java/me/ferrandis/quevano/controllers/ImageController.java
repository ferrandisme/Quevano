package me.ferrandis.quevano.controllers;

import me.ferrandis.quevano.services.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ImageController {

    ImageService service;
    public ImageController(ImageService service){
        this.service = service;
    }

    @PostMapping("/v1/image/convert")
    public Mono<String> convertImage () {
                return  service.convertImage();
    }

}
