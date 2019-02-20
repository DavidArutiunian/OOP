/*
Вариант №2 – bmpinfo – 60 баллов
Разработайте приложение bmpinfo.exe, выполняющее считывание заголовка входного файла и, если, судя по заголовку, формат файла соответствует признакам формата BMP (информацию о структуре BMP файла можно найти в Интернет), то необходимо вывести в output следующую информацию:
    • Разрешение (ширина и высота файла)
    • Количество бит на пиксель
    • В случае, если в BMP файле используется палитра (8 и менее бит), необходимо вывести количество используемых цветов.
    • Размер изображения в байтах
Если входной файл не является файлом формата BMP, то вывести об этом соответствующее сообщение.
Формат командной строки:
    bmpinfo.exe <input file name>
Программа должна корректно обрабатывать ошибки, связанные с файловыми операциями.
Размеры входных и выходных файлов таковы, что в оперативную память программы могут не поместиться целиком.
В комплекте с программой должны обязательно поставляться файлы, позволяющие проверить ее работу в автоматическом режиме.
Примечание: BMP-файлы (редко) могут использовать PNG, RLE или Jpeg-компрессию данных – в этом случае нужно вывести соответствующую информацию
*/

package labs.lab1.bmpinfo;

import lib.io.BaseInputOutput;

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
