import de.spurtikus.swbookmarker.Processor;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProcessorTest {

    @Test
    public void testIsIn() {
        Processor p = new Processor(null);

        assertTrue(p.isIn("0800-1000", "0900"));
        assertFalse(p.isIn("0800-1000", "0700"));
        assertFalse(p.isIn("0800-1000", "1700"));

        assertTrue(p.isIn("1000-0600", "1100"));
        assertTrue(p.isIn("1000-0600", "0500"));
        assertFalse(p.isIn("1000-0600", "0900"));
    }
}
