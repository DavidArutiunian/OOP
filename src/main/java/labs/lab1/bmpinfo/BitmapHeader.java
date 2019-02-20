package labs.lab1.bmpinfo;

class BitmapHeader {
    static int OFFSET = 14;
    static int BMP_TYPE = 0x4D42;
    private int headerSize;
    private int width;
    private int height;
    private char planes;
    private char bitsPerPixel;
    private int compressionType;
    private int imageSize;
    private int horizontalResolution;
    private int verticalResolution;
    private int numOfColors;
    private int numOfImportantColors;

    int getHeaderSize() {
        return headerSize;
    }

    void setHeaderSize(int headerSize) {
        this.headerSize = headerSize;
    }

    int getWidth() {
        return width;
    }

    void setWidth(int width) {
        this.width = width;
    }

    int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    char getPlanes() {
        return planes;
    }

    void setPlanes(char planes) {
        this.planes = planes;
    }

    char getBitsPerPixel() {
        return bitsPerPixel;
    }

    void setBitsPerPixel(char bitsPerPixel) {
        this.bitsPerPixel = bitsPerPixel;
    }

    int getCompressionType() {
        return compressionType;
    }

    void setCompressionType(int compressionType) {
        this.compressionType = compressionType;
    }

    int getImageSize() {
        return imageSize;
    }

    void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    int getHorizontalResolution() {
        return horizontalResolution;
    }

    void setHorizontalResolution(int horizontalResolution) {
        this.horizontalResolution = horizontalResolution;
    }

    int getVerticalResolution() {
        return verticalResolution;
    }

    void setVerticalResolution(int verticalResolution) {
        this.verticalResolution = verticalResolution;
    }

    int getNumOfColors() {
        return numOfColors;
    }

    void setNumOfColors(int numOfColors) {
        this.numOfColors = numOfColors;
    }

    int getNumOfImportantColors() {
        return numOfImportantColors;
    }

    void setNumOfImportantColors(int numOfImportantColors) {
        this.numOfImportantColors = numOfImportantColors;
    }

    enum ECompressionType {
        RLE_8(1),
        RLE_4(2);

        private final int value;

        ECompressionType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
