package reading;

import java.io.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.*;

/**
 * Read text file, interpret the contents and create an XML file based on it.
 *
 * @author Jimmy  Quaresmini
 */
public class ReadTxtFile {
    BufferedReader readFile;
    boolean familyXMLbegun;

    Document document = null;

    /**
     * Reads the text file, prepares processing and calls the processing.
     *
     * @param filename The text file to be processed.
     * @return the DOM document variable containing the XML.
     */
    public Document readTextFile(String filename) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            readFile = new BufferedReader(new FileReader(filename));

            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " was not found. Quitting.");
            System.exit(0);
        } catch (ParserConfigurationException parserConfigurationException) {
            System.out.println("Problem parsing document: " + parserConfigurationException.getMessage());
        }

        readLinesFromFile();

        return document;
    }

    /**
     * Processes the file and creates the XML.
     * <p>The XML is represented by a DOM document object. All elements and text nodes are created here.</p>
     */
    private void readLinesFromFile() {
        Element root = document.createElement("people");
        document.appendChild(root);
        Element person = null;
        Element family = null;

        while (true) {
            String fileLine = null;
            try {
                fileLine = readFile.readLine();
            } catch (IOException ioException) {
                System.out.println("Problem reading the file: " + ioException.getMessage());
                System.exit(0);
            }

            if (fileLine == null) {
                break;
            }
            String[] linesText = fileLine.split("\\|");
            String firstLetter = linesText[0];
            switch (firstLetter) {
                case "P":
                    person = document.createElement("person");
                    root.appendChild(person);

                    Element firstName = document.createElement("firstname");
                    firstName.appendChild(document.createTextNode(linesText[1]));
                    person.appendChild(firstName);

                    Element lastName = document.createElement("lastname");
                    lastName.appendChild(document.createTextNode(linesText[2]));
                    person.appendChild(lastName);

                    if (familyXMLbegun == true) {
                        familyXMLbegun = false;
                    }
                    break;
                case "T":
                    Element phone = document.createElement("phone");

                    Element mobile = document.createElement("mobile");
                    mobile.appendChild(document.createTextNode(linesText[1]));
                    phone.appendChild(mobile);

                    Element landline = document.createElement("landline");
                    landline.appendChild(document.createTextNode(linesText[2]));
                    phone.appendChild(landline);

                    if (familyXMLbegun == true) {
                        family.appendChild(phone);
                    }
                    else {
                        person.appendChild(phone);
                    }
                    break;
                case "A":
                    Element address = document.createElement("address");

                    Element street = document.createElement("street");
                    street.appendChild(document.createTextNode(linesText[1]));
                    address.appendChild(street);

                    Element city = document.createElement("city");
                    city.appendChild(document.createTextNode(linesText[2]));
                    address.appendChild(city);
                    if (familyXMLbegun == true) {
                        if (linesText.length == 4) {
                            Element zip = document.createElement("zip");
                            zip.appendChild(document.createTextNode(linesText[3]));
                            address.appendChild(zip);
                        }

                        family.appendChild(address);
                    }
                    else {
                        if (linesText.length == 4) {
                            Element zip = document.createElement("zip");
                            zip.appendChild(document.createTextNode(linesText[3]));
                            address.appendChild(zip);
                        }

                        person.appendChild(address);
                    }
                    break;
                case "F":
                    familyXMLbegun = true;
                    family = document.createElement("family");

                    Element name = document.createElement("name");
                    name.appendChild(document.createTextNode(linesText[1]));
                    family.appendChild(name);

                    Element born = document.createElement("born");
                    born.appendChild(document.createTextNode(linesText[2]));
                    family.appendChild(born);

                    person.appendChild(family);
                    break;
                default:
                    System.out.println("Incorrect first letter of the line. P, T, A or F expected");
            }
        }
    }
}
