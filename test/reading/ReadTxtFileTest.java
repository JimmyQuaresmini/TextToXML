package reading;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

class ReadTxtFileTest {
    ReadTxtFile readTxtFile = new ReadTxtFile();

    Document document = null;

    @Test
    void readTextFile() {
        createTestFile("testfile.txt");
        createXML();

        Document doc = readTxtFile.readTextFile("testfile.txt");
        assertEquals(document.toString(), doc.toString());
        System.out.println("Tests if the read method produces the expected XML-string from a test text-string");
    }

    @Test
    void readFileWithNoEnding() {
        createTestFile("testfile2");
        createXML();

        Document doc  = readTxtFile.readTextFile("testfile2");
        assertEquals(document.toString(), doc.toString());
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

    private void createXML() { //StringBuilder
        /*StringBuilder xmlBuilder = new StringBuilder("<people>");

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
        xmlBuilder.append("\n</people>");*/

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException parserConfigurationException) {
            System.out.println("Problem parsing document: " + parserConfigurationException.getMessage());
        }

        Element root = document.createElement("people");
        document.appendChild(root);

        Element person = document.createElement("person");
        root.appendChild(person);

        Element firstName = document.createElement("firstname");
        firstName.appendChild(document.createTextNode("Test"));
        person.appendChild(firstName);
        Element lastName = document.createElement("lastname");
        lastName.appendChild(document.createTextNode("Er"));
        person.appendChild(lastName);

        Element phone = document.createElement("phone");
        Element mobile = document.createElement("mobile");
        mobile.appendChild(document.createTextNode("0123"));
        phone.appendChild(mobile);
        Element landline = document.createElement("landline");
        landline.appendChild(document.createTextNode("456789"));
        phone.appendChild(landline);
        person.appendChild(phone);

        Element family = document.createElement("phone");
        Element name = document.createElement("name");
        name.appendChild(document.createTextNode("Check"));
        family.appendChild(name);
        Element born = document.createElement("born");
        born.appendChild(document.createTextNode("2000"));
        family.appendChild(born);
        Element address = document.createElement("address");
        Element street = document.createElement("street");
        street.appendChild(document.createTextNode("Check street 1"));
        address.appendChild(street);
        Element city = document.createElement("city");
        city.appendChild(document.createTextNode("Checkia"));
        address.appendChild(city);
        Element zip = document.createElement("zip");
        zip.appendChild(document.createTextNode("12345"));
        address.appendChild(zip);
        family.appendChild(address);
        person.appendChild(family);

        //return xmlBuilder;
    }
}