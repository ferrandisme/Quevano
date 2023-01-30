package me.ferrandis.quevano.utils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ImageUtilsTest {

    /**
     * Local directory where you have tessdata_best data (github clone)
     */
    private static final String LOCAL_TESS_DIRECTORY = "src/main/resources/data/tessdata";


    @Test
    void testIsLibraryConfigured() throws TesseractException {
        File image = new File("src/test/resources/example.png");
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(LOCAL_TESS_DIRECTORY);
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        String result = tesseract.doOCR(image);

        assertTrue(result.contains("Test work OK"));
    }


    @Test
    void testReadImage() throws TesseractException {
        File image = new File("src/test/resources/example.png");
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(LOCAL_TESS_DIRECTORY);
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        String expected = tesseract.doOCR(image);
        String result = ImageUtils.readImage(image);
        assertEquals(expected, result, expected + " != " + result);
    }
}