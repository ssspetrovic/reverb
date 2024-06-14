package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
