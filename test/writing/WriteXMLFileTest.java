package writing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;


class WriteXMLFileTest {

    @Test
    void writeXMLtoFile() {
        WriteXMLFile writeXMLFile = new WriteXMLFile();

        String filename = "testfile";
        int nameLength = filename.length();
        StringBuilder xmlbuilder = new StringBuilder("<test>testing writing file</test>");

        writeXMLFile.writeXMLtoFile(filename, nameLength, xmlbuilder);

        File testFile = new File(filename + ".xml");

        assertTrue(testFile.exists());
        assertTrue(testFile.length() > 0);
    }
}