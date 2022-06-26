package writing;

import java.io.*;

public class WriteXMLFile {

    public void writeXMLtoFile(String filename, int fileLenSubEnding, StringBuilder xmlBuilder) {
        String xmlFilename = filename.substring(0, fileLenSubEnding) + ".xml";
        try {
            PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(xmlFilename)));
            writeFile.write(xmlBuilder.toString());
            writeFile.close();
        } catch (IOException e) {
            System.out.println("Problem writing to xml file: " + e.getMessage());
        }
    }
}
