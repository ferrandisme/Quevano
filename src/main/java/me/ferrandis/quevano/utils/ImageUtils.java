package me.ferrandis.quevano.utils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class ImageUtils {

    /** Local directory where you have tessdata_best data (github clone)*/
    private static final String LOCAL_TESS_DIRECTORY =  "F:\\tess";

    public static String readImage(File image) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(LOCAL_TESS_DIRECTORY);
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        return tesseract.doOCR(image);
    }
}
