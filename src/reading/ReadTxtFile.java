package reading;

import java.io.*;

public class ReadTxtFile {
    BufferedReader readFile;
    StringBuilder xmlBuilder = new StringBuilder("<people>");
    boolean personXMLbegun, familyXMLbegun;

    public StringBuilder readTextFile(String filename) {
        try {
            readFile = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " was not found. Quitting.");
            System.exit(0);
        }

        readLinesFromFile();

        return xmlBuilder;
    }

    private void readLinesFromFile() {
        while (true) {
            String fileLine = null;
            try {
                fileLine = readFile.readLine();
            } catch (IOException ioException) {
                System.out.println("Problem reading the file: " + ioException.getMessage());
                System.exit(0);
            }

            if (fileLine == null) {
                if (personXMLbegun == true) {
                    xmlBuilder.append("\n\t</person>");
                }
                xmlBuilder.append("\n</people>");
                break;
            }
            String[] linesText = fileLine.split("\\|");
            String firstLetter = linesText[0];
            switch (firstLetter) {
                case "P":
                    //checks if we have started to write on the XML of a person
                    if (personXMLbegun == true) {
                        xmlBuilder.append("\n\t</person>");
                    }
                    else {
                        personXMLbegun = true;
                    }
                    xmlBuilder.append("\n\t<person>\n\t\t<firstname>" + linesText[1] + "</firstname>");
                    xmlBuilder.append("\n\t\t<lastname>" + linesText[2] + "</lastname>");
                    break;
                case "T":
                    if (familyXMLbegun == true) {
                        xmlBuilder.append("\n\t\t\t<phone>\n\t\t\t\t<mobile>" + linesText[1] + "</mobile>");
                        xmlBuilder.append("\n\t\t\t\t<landline>" + linesText[2] + "</landline>\n\t\t\t</phone>");
                        xmlBuilder.append("\n\t\t</family>");
                        familyXMLbegun = false;
                    }
                    else {
                        xmlBuilder.append("\n\t\t<phone>\n\t\t\t<mobile>" + linesText[1] + "</mobile>");
                        xmlBuilder.append("\n\t\t\t<landline>" + linesText[2] + "</landline>\n\t\t</phone>");
                    }
                    break;
                case "A":
                    if (familyXMLbegun == true) {
                        xmlBuilder.append("\n\t\t\t<address>\n\t\t\t\t<street>" + linesText[1] + "</street>");
                        xmlBuilder.append("\n\t\t\t\t<city>" + linesText[2] + "</city>");
                        if (linesText.length == 4) {
                            xmlBuilder.append("\n\t\t\t\t<zip>" + linesText[3] + "</zip>\n\t\t\t</address>");
                        }
                        else {
                            xmlBuilder.append("\n\t\t\t</address>");
                        }
                        xmlBuilder.append("\n\t\t</family>");
                        familyXMLbegun = false;
                    }
                    else {
                        xmlBuilder.append("\n\t\t<address>\n\t\t\t<street>" + linesText[1] + "</street>");
                        xmlBuilder.append("\n\t\t\t<city>" + linesText[2] + "</city>");
                        if (linesText.length == 4) {
                            xmlBuilder.append("\n\t\t\t<zip>" + linesText[3] + "</zip>\n\t\t</address>");
                        }
                        else {
                            xmlBuilder.append("\n\t\t</address>");
                        }
                    }
                    break;
                case "F":
                    familyXMLbegun = true;
                    xmlBuilder.append("\n\t\t<family>\n\t\t\t<name>" + linesText[1] + "</name>");
                    xmlBuilder.append("\n\t\t\t<born>" + linesText[2] + "</born>");
                    break;
                default:
                    System.out.println("Incorrect first letter of the line. P, T, A or F expected");
            }
        }
    }
}
