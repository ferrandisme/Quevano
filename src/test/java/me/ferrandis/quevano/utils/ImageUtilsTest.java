package me.ferrandis.quevano.utils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ImageUtilsTest {

    @Test
    void testIsLibraryConfigured() throws TesseractException {
        File image = new File("src/test/resources/example.png");
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("F:\\tess");
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        String result = tesseract.doOCR(image);

        assertTrue(result.contains("Test work OK"));
    }
}