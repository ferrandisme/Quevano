package me.ferrandis.quevano.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class ImageUtils {

    /**
     * Local directory where you have tessdata_best data (github clone)
     */
    private static final String LOCAL_TESS_DIRECTORY = "src/main/resources/data/tessdata";


    public static String readImage(File image) throws TesseractException {
        return readImage(image, "eng");
    }

    public static String readImage(File image, String language) throws TesseractException {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(LOCAL_TESS_DIRECTORY);
        tesseract.setLanguage(language);
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        return tesseract.doOCR(image);
    }
}
