package writing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;


class WriteXMLFileTest {

    @Test
    void writeXMLtoFile() {
        WriteXMLFile writeXMLFile = new WriteXMLFile();

        String filename = "testfile";
        int nameLength = filename.length();
        Document document = null;
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException parserConfigurationException) {
            System.out.println("Problem parsing document: " + parserConfigurationException.getMessage());
        }

        Element root = document.createElement("test");
        document.appendChild(root);
        root.appendChild(document.createTextNode("testing writing file"));

        writeXMLFile.writeXMLtoFile(filename, nameLength, document);

        File testFile = new File(filename + ".xml");

        assertTrue(testFile.exists());
        assertTrue(testFile.length() > 0);
    }
}