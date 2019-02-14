package lab1.task4;

import io.BaseInputOutput;

import java.io.File;

class Main {
    public static void main(String[] args) {
        try {
            BaseInputOutput.validate(args);
            var file = new File(args[0]);
            BaseInputOutput.validate(file);
            var reader = new BitmapReader(file);
            var header = reader.getHeader();
            System.out.println("Width: " + header.getWidth());
            System.out.println("Height: " + header.getHeight());
            System.out.println("Number of colors: " + header.getNumOfColors());
            System.out.println("Image size: " + header.getImageSize());
            if (header.getCompressionType() != 0) {
                if (header.getCompressionType() == BitmapHeader.ECompressionType.RLE_8.getValue()) {
                    System.out.println("Compression type: RLE-8");
                }
                if (header.getCompressionType() == BitmapHeader.ECompressionType.RLE_4.getValue()) {
                    System.out.println("Compression type: RLE-4");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
