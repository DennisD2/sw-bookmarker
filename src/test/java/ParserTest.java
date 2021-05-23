import de.spurtikus.swbookmarker.Parser;
import de.spurtikus.swbookmarker.Processor;
import de.spurtikus.swbookmarker.Writer;
import de.spurtikus.swbookmarker.model.Bookmark;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ParserTest {

    @Test
    public void testE2E() throws IOException {
        Parser p = new Parser();

        List<Bookmark> bookmarks = p.load("/home/dennis/IdeaProjects/sw-bookmarker/sked-a21.csv");

        Processor proc = new Processor(bookmarks);
        String time = "1800";
        List<Bookmark> byTime = proc.searchByTime(time);
        System.out.println("Selected: " + byTime.size());
        //byTime.forEach(System.out::println);

        Writer writer = new Writer();
        writer.write("byTime-" + time + ".txt", byTime);
    }
}