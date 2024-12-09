package com.pucmg.lab03.utils;

import java.util.Base64;

public class ImageUtils {
    public static String convertToBase64(byte[] imageBytes) {
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
    }
}