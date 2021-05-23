package de.spurtikus.swbookmarker;

import de.spurtikus.swbookmarker.model.Bookmark;

import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<Bookmark> load(String fileName) throws IOException {
        InputStream inputStream = null;
        List<Bookmark> bookmarks = new ArrayList<>();
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return bookmarks;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        long lines = 0;
        while(reader.ready()) {
            String line = reader.readLine();
            if (lines > 0) {
                Bookmark bookmark = parse(line);
                bookmarks.add(bookmark);
            }
            lines++;
        }
        System.out.println("Loaded " + lines + " bookmarks from file");
        return bookmarks;
    }

    /**
     * Materialize bookmark from text line
     * @param line
     * @return
     */
    private Bookmark parse(String line) {
        String[] parts = line.split(";");
        Bookmark bm = new Bookmark();
        Double freq = Double.parseDouble(parts[0]);
        bm.setFreq(freq);
        bm.setTime(parts[1]);
        bm.setDays(parts[2]);
        bm.setItu(parts[3]);
        bm.setStation(parts[4]);
        bm.setLanguage(parts[5]);
        bm.setTarget(parts[6]);
        bm.setTransmitterSite(parts[7]);
        bm.setPersistence(parts[8]);
        if (parts.length>9) {
            bm.setStart(parts[9]);
        }
        if (parts.length>10) {
            bm.setStart(parts[10]);
        }
        return bm;
    }
}
