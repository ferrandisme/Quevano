package me.ferrandis.quevano.services;

import me.ferrandis.quevano.utils.ImageUtils;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ImageService {
    public String convertImage(File file, String language) throws TesseractException {
        return ImageUtils.readImage(file, language);
    }
}
