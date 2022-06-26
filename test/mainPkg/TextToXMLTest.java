package mainPkg;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.File;

/*
* Normally one does not test the main method as nothing is returned.
* In this case I think it's motivated to test the input and...
* ...the creation of the xml file.
* */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TextToXMLTest {
    @Test
    @Order(1)
    void throwsExceptionWithEmptyFilename() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TextToXML.main(new String[]{""});
        });

        String expectedMessage = "empty filename";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Order(2)
    void main() {
        TextToXML.main(new String[]{"people.txt"});
        File testFile = new File("people.xml");
        assertTrue(testFile.exists());
        System.out.println("Testing that the file people.xml exists after running the main method");
    }
}