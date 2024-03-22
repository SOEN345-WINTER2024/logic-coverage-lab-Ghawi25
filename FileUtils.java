public String stripExtension(String fileNameWithExt) {
    // Handle null case specially.
    if (fileNameWithExt == null) return null;

    // Get position of last '.'.
    int pos = fileNameWithExt.lastIndexOf(".");

    // If there wasn't any '.' just return the string as is.
    if (pos == -1) return fileNameWithExt;

    // Otherwise return the string, up to the dot.
    return fileNameWithExt.substring(0, pos);
}

//Junit test

import org.junit.Test;
import static org.junit.Assert.*;

public class FileNameUtilsTest {

    @Test
    public void stripExtension_NullFileName_ReturnsNull() {
        assertNull(FileNameUtils.stripExtension(null));
    }

    @Test
    public void stripExtension_NoExtension_ReturnsOriginal() {
        assertEquals("filename", FileNameUtils.stripExtension("filename"));
    }

    @Test
    public void stripExtension_WithExtension_ReturnsBaseName() {
        assertEquals("androidDev", FileNameUtils.stripExtension("androidDev.jpg"));
    }

    @Test
    public void stripExtension_EmptyString_ReturnsEmpty() {
        assertEquals("", FileNameUtils.stripExtension(""));
    }
}
