package labs.lab1.bmpinfo;

import java.io.*;

class BitmapReader {
    // Big Endian by default, need to read in Little Endian
    private final RandomAccessFile raf;
    private final BitmapHeader header = new BitmapHeader();

    BitmapReader(final File file) throws Exception {
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException("File \"" + file.getPath() + "\" not found!");
        }
        if (file.length() == 0) {
            throw new EOFException("File is empty!");
        }
        this.raf = new RandomAccessFile(file, "r");
        init();
    }

    private static int readIntLittleEndian(final RandomAccessFile file) throws IOException {
        int a = file.readByte() & 0xFF;
        int b = file.readByte() & 0xFF;
        int c = file.readByte() & 0xFF;
        int d = file.readByte() & 0xFF;
        return (d << 24) | (c << 16) | (b << 8) | a;
    }

    private static char readCharLittleEndian(final RandomAccessFile file) throws IOException {
        int a = file.readByte() & 0xFF;
        int b = file.readByte() & 0xFF;
        return (char) ((b << 8) | a);
    }

    BitmapHeader getHeader() {
        return header;
    }

    // http://www.fastgraph.com/help/bmp_header_format.html
    private void init() throws IOException {
        int type = BitmapReader.readCharLittleEndian(raf);
        if (type != BitmapHeader.BMP_TYPE) {
            raf.close();
            throw new UnsupportedEncodingException("File is not supported!");
        }
        raf.seek(BitmapHeader.OFFSET);
        header.setHeaderSize(BitmapReader.readIntLittleEndian(raf));
        header.setWidth(BitmapReader.readIntLittleEndian(raf));
        header.setHeight(BitmapReader.readIntLittleEndian(raf));
        header.setPlanes(BitmapReader.readCharLittleEndian(raf));
        header.setBitsPerPixel(BitmapReader.readCharLittleEndian(raf));
        header.setCompressionType(BitmapReader.readIntLittleEndian(raf));
        header.setImageSize(BitmapReader.readIntLittleEndian(raf));
        header.setHorizontalResolution(BitmapReader.readIntLittleEndian(raf));
        header.setVerticalResolution(BitmapReader.readIntLittleEndian(raf));
        header.setNumOfColors(BitmapReader.readIntLittleEndian(raf));
        header.setNumOfImportantColors(BitmapReader.readIntLittleEndian(raf));
        raf.close();
    }
}
