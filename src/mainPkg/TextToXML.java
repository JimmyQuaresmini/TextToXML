package mainPkg;

import reading.ReadTxtFile;
import writing.WriteXMLFile;

public class TextToXML {

    public static void main(String[] args) {
        String filename = args[0];
        if (filename == null || filename.equals("")) {
            throw new IllegalArgumentException(
                    "No filename as argument provided or an empty filename provided. Not allowed");
        }

        int dotIndex = filename.indexOf('.');
        dotIndex = dotIndex > -1 ? dotIndex : filename.length();

        ReadTxtFile readTxtFile = new ReadTxtFile();
        StringBuilder xmlBuilder = readTxtFile.readTextFile(filename);

        WriteXMLFile writeXMLFile = new WriteXMLFile();
        writeXMLFile.writeXMLtoFile(filename, dotIndex, xmlBuilder);
    }
}
