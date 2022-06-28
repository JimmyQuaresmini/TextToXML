package mainPkg;

import org.w3c.dom.Document;
import reading.ReadTxtFile;
import writing.WriteXMLFile;

/**
 * Text to XML file converter.
 * <p>The text file is passed as a command line argument. With or without ending.</p>
 *
 * @author Jimmy Quaresmini
 */
public class TextToXML {

    /**
     * The main method that calls reading the text file and writing the XML.
     * <p>Throws IllegalArgumentException without a file name argument.</p>
     *
     * @param args the command line arguments. A textfile to be converted to an XML file.
     */
    public static void main(String[] args) {
        String filename = args[0];
        if (filename == null || filename.equals("")) {
            throw new IllegalArgumentException(
                    "No filename as argument provided or an empty filename provided. Not allowed");
        }

        int dotIndex = filename.indexOf('.');
        dotIndex = dotIndex > -1 ? dotIndex : filename.length();

        ReadTxtFile readTxtFile = new ReadTxtFile();
        Document document = readTxtFile.readTextFile(filename);

        WriteXMLFile writeXMLFile = new WriteXMLFile();
        writeXMLFile.writeXMLtoFile(filename, dotIndex, document);
    }
}
