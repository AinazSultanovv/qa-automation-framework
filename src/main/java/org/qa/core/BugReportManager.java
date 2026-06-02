
// Управление коллекцией баг-репортов
package org.qa.core;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BugReportManager {


    public static List<BugReport> deduplicateAndSort(List<BugReport> reports) {

        Set<String> seenIds = new HashSet<>();

        List<BugReport> uniqueReports = new ArrayList<>();

        for (BugReport report : reports) {

            if (seenIds.add(report.getBugId())) {

                uniqueReports.add(report);
            }

        }


        uniqueReports.sort(

                Comparator.comparing((BugReport r) -> {


                            return switch (r.getPriority()) {
                                case "CRITICAL" -> 1;
                                case "MAJOR"    -> 2;
                                case "MINOR"    -> 3;
                                default         -> 999;
                            };
                        })
                        .thenComparing(BugReport::getBugId)
        );

        return uniqueReports;
    }
}