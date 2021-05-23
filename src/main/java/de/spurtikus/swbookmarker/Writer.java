package de.spurtikus.swbookmarker;

import de.spurtikus.swbookmarker.model.Bookmark;

import java.util.List;

/**
 * Write bookmark file in a Gqrx compliant way
 *     # Frequency ; Name                     ; Modulation          ;  Bandwidth; Tags
 *            0; lw_x                     ; AM                  ;       5000; Untagged
 *        77500; DCF77                    ; AM                  ;       5000; Untagged
 */
public class Writer {

    public void write(String target, List<Bookmark> bookmarks) {
        bookmarks.forEach(b -> {
            //System.out.println(b);
            StringBuffer sb = new StringBuffer();
            String freq = String.format("%.0f", 1000 * b.getFreq());;
            sb.append(freq);
            sb.append(";");
            sb.append(b.getStation());
            sb.append(";");
            sb.append(setModulation(b));
            sb.append(";");
            sb.append("5000;"); // Default
            sb.append(appendTags(b));
            // Dump to stdout
            System.out.println(sb.toString());
        });
    }

    /**
     * Translate modulation values to values known by Gqrx
     * @param bookmark
     * @return
     */
    private String setModulation(Bookmark bookmark) {
        if (bookmark.getLanguage().equals("-CW")) {
            return "CW";
        }
        if (bookmark.getLanguage().equals("-EC")) {
            return "CW";
        }
        if (bookmark.getLanguage().equals("-TY")) {
            return "CW";
        }
        return "AM";
    }

    /**
     * Trnaslate some attributes to tags
     * @param b
     * @return
     */
    private StringBuffer appendTags(Bookmark b) {
        StringBuffer sb = new StringBuffer();
        boolean addSeparator = false;
        if (b.getItu() != null && !b.getItu().isEmpty()) {
            sb.append("ITU_" + b.getItu());
            addSeparator = true;
        }
        if (b.getLanguage() != null && !b.getLanguage().isEmpty()) {
            if (addSeparator) {
                sb.append(",");
            }
            sb.append("LNG_" + b.getLanguage());
            addSeparator = true;
        }
        if (b.getTarget() != null && !b.getTarget().isEmpty()) {
            if (addSeparator) {
                sb.append(",");
            }
            sb.append("TRG_" + b.getTarget());
            addSeparator = true;
        }
        /*if (b.getTransmitterSite() != null && !b.getTransmitterSite().isEmpty()) {
            if (addSeparator) {
                sb.append(",");
            }
            sb.append("TRN_" + b.getTransmitterSite());
            addSeparator = true;
        }*/
        return sb;
    }

}
