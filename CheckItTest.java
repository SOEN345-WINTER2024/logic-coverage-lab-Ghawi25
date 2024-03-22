import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CheckItTest {

    @Test
    public void testPredicateCoverage() {
       
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        
        CheckIt.checkIt(true, true, true);
        assertEquals("P is true\n", output.toString());

    }

    @Test
    public void testClauseCoverage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        CheckIt.checkIt(false, false, true);
    }

    @Test
    public void testCACC() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        CheckIt.checkIt(true, false, true);
        assertEquals("P is true\n", output.toString());

    }

    @Test
    public void testRACC() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        CheckIt.checkIt(true, false, false);
        assertEquals("P is true\n", output.toString());

    }
}
