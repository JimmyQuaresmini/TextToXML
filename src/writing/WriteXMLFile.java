package writing;

import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Writes the XML to file.
 * <p>The DOM document object is transformed to an XML file through a stream.</p>
 *
 * @author Jimmy Quaresmini
 */
public class WriteXMLFile {

    public void writeXMLtoFile(String filename, int fileLenSubEnding, Document document) {
        String xmlFilename = filename.substring(0, fileLenSubEnding) + ".xml";
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");//pretty print XML
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilename));
            transformer.transform(domSource, streamResult);
        } catch (TransformerException te) {
            System.out.println("Problem creating the XML file: " + te.getMessage());
        }
    }
}
