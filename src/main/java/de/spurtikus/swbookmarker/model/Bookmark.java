package de.spurtikus.swbookmarker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
kHz:75;
Time(UTC):93;
Days:59;
ITU:49;
Station:201;
Lng:49;
Target:62;
Remarks:135;
P:35;
Start:60;
Stop:60;
 */
@Data
public class Bookmark {
    double freq;
    String time;
    String days; /* ? */
    String itu;
    String station;
    String language;
    String target;
    String transmitterSite;
    String persistence;
    String start;
    String stop;
}
