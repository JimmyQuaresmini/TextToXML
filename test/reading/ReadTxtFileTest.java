package reading;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.*;

class ReadTxtFileTest {
    ReadTxtFile readTxtFile = new ReadTxtFile();
    StringBuilder testXMLBuilder = createXML();

    @Test
    void readTextFile() {
        createTestFile("testfile.txt");

        StringBuilder xmlBuilder = readTxtFile.readTextFile("testfile.txt");
        assertEquals(testXMLBuilder.toString(), xmlBuilder.toString());
        System.out.println("Tests if the read method produces the expected XML-string from a test text-string");
    }

    @Test
    void readFileWithNoEnding() {
        createTestFile("testfile2");

        StringBuilder xmlBuilder = readTxtFile.readTextFile("testfile2");
        assertEquals(testXMLBuilder.toString(), xmlBuilder.toString());
        System.out.println("Tests if the read method produces the expected XML-string from a test file with no ending");
    }

    private void createTestFile(String filename) {
        StringBuilder txtBuilder = new StringBuilder();

        txtBuilder.append("P|Test|Er\n");
        txtBuilder.append("T|0123|456789\n");
        txtBuilder.append("F|Check|2000\n");
        txtBuilder.append("A|Check street 1|Checkia|12345\n");

        try {
            PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            writeFile.write(txtBuilder.toString());
            writeFile.close();
        } catch (IOException e) {
            System.out.println("Problem writing to testfile: " + e.getMessage());
        }
    }

    private StringBuilder createXML() {
        StringBuilder xmlBuilder = new StringBuilder("<people>");

        xmlBuilder.append("\n\t<person>\n\t\t<firstname>Test</firstname>");
        xmlBuilder.append("\n\t\t<lastname>Er</lastname>");
        xmlBuilder.append("\n\t\t<phone>\n\t\t\t<mobile>0123</mobile>");
        xmlBuilder.append("\n\t\t\t<landline>456789</landline>\n\t\t</phone>");
        xmlBuilder.append("\n\t\t<family>\n\t\t\t<name>Check</name>");
        xmlBuilder.append("\n\t\t\t<born>2000</born>");
        xmlBuilder.append("\n\t\t\t<address>\n\t\t\t\t<street>Check street 1</street>");
        xmlBuilder.append("\n\t\t\t\t<city>Checkia</city>");
        xmlBuilder.append("\n\t\t\t\t<zip>12345</zip>\n\t\t\t</address>");
        xmlBuilder.append("\n\t\t</family>\n\t</person>");
        xmlBuilder.append("\n</people>");

        return xmlBuilder;
    }
}