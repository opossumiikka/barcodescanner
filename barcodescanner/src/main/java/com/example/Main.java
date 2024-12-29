package com.example;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try {
            // Get the image file from the resources folder
            URL imageUrl = Main.class.getClassLoader().getResource("imagess/barcode.gif");
            if (imageUrl == null) {
                System.out.println("Image not found!");
                return;
            }

            // Handle the URISyntaxException when converting URL to File
            File file = null;
            try {
                file = new File(imageUrl.toURI());
            } catch (URISyntaxException e) {
                System.out.println("Invalid URI syntax: " + e.getMessage());
                return;
            }

            BufferedImage bufferedImage = ImageIO.read(file);

            if (bufferedImage == null) {
                System.out.println("Could not read the image file.");
                return;
            }

            // Create a BinaryBitmap from the image
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // Decode the barcode
            Result result = new MultiFormatReader().decode(bitmap);
            System.out.println("Barcode text: " + result.getText());
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
    }
}
