package de.spurtikus.swbookmarker;

import de.spurtikus.swbookmarker.model.Bookmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Processor {
    private List<Bookmark> all;

    public Processor(List<Bookmark> bookmarks) {
       all = bookmarks;
    }

    /**
     * Get list of all bookmarks where parameter time is in their time range
      * @param time
     * @return
     */
    public List<Bookmark> searchByTime(String time) {
        List<Bookmark> results = new ArrayList<>();
        all.stream().filter(x -> isIn(x.getTime(), time)).forEach(y -> addSingle(results,y));
        return results;
    }

    /**
     * Add single bookmark to result list
     * @param results
     * @param bm
     */
    void addSingle(List<Bookmark> results, Bookmark bm) {
        Bookmark aLike = containsALike(results, bm);
        if (aLike == null) {
            results.add(bm);
        } else {
            // Differ in language -> just inc. tags
            // for now, we just ignore these 'doublettes'
            String lang1 = bm.getLanguage();
            String lang2 = aLike.getLanguage();
            if (!lang1.equals(lang2)) {
                //System.out.println("ignoring (languages).");
                return;
            }

            String days1 = bm.getDays();
            String days2 = aLike.getDays();
            if (!days1.equals(days2)) {
                //System.out.println("ignoring (days).");
                return;
            }

            String time1 = bm.getTime();
            String time2 = aLike.getTime();
            if (!time1.equals(time2)) {
                //System.out.println("ignoring (times).");
                return;
            }

            String transmitters1 = bm.getTransmitterSite();
            String transmitters2 = aLike.getTransmitterSite();
            if (!transmitters1.equals(transmitters2)) {
                //System.out.println("ignoring (transmitters).");
                return;
            }

            String start1 = bm.getStart();
            String start2 = aLike.getStart();
            if (!start1.equals(start2)) {
                //System.out.println("ignoring (start).");
                return;
            }

            // Unknown difference; dump out objects
            System.out.println("UHOH1: " + bm);
            System.out.println("UHOH2: " + aLike);
        }
    }

    /**
     * Returns first bookmark that is equel in Frequency and Station attribute.
     * @param bookmarks
     * @param bm
     * @return
     */
    private Bookmark containsALike(List<Bookmark> bookmarks, Bookmark bm) {
        Bookmark first = bookmarks.stream().filter(
                b -> bm.getFreq() == b.getFreq()
                        && bm.getStation().equals(b.getStation()))
                .findFirst().orElse(null);
        return first;
    }

    /**
     * Check igf a time is intime range
     * @param timerange
     * @param time
     * @return
     */
    public boolean isIn(String timerange, String time) {
        String startTime = startTime(timerange);
        String stopTime = stopTime(timerange);
        if (startTime.compareTo(stopTime) >= 0) {
            // 0800-1000
            return isAfterOrEqual(startTime, time) && isBeforeOrEqual(stopTime, time);
        } else {
            // 1000-0800
            return (isAfterOrEqual(startTime, time) || isBeforeOrEqual(stopTime, time));
        }
    }

    private String stopTime(String timerange) {
        String[] parts = timerange.split("-");
        return parts[0];
    }

    private String startTime(String timerange) {
        String[] parts = timerange.split("-");
        return parts[1];
    }

    private boolean isBeforeOrEqual( String timeA, String timeB) {
        return (timeA.compareTo(timeB) <= 0);
    }

    private boolean isAfterOrEqual( String timeA, String timeB) {
        return (timeA.compareTo(timeB) >= 0);
    }
}
