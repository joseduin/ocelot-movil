package ocelot.app.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jose on 19/11/2017.
 */

public class Convertidor {

    private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat serverdf = new SimpleDateFormat("yyyy-MM-dd");

    private static int cut = 65;

    public static Date timeStampToDate(String time) {
        try {
            return serverdf.parse(time);
        } catch (ParseException e) { e.printStackTrace();
            return null;
        }
    }

    public static String dateToTimeStamp(Date d) {
        return fechaPrint(d);
    }

    public static String descripcionCut(String d) {
        return d.length() > cut ? d.substring(0, cut) + "[..]" : d;
    }

    public static String descuentoPrint(double d) {
        return "-" + descuentoBeauty( d * 100 ) + " %";
    }

    public static String descuentoBeauty(double d) {
        String dStr = String.valueOf(d);
        String[] decimal = dStr.split("\\.");

        if (decimal[1].equals("0")) {
            return decimal[0];
        }
        return dStr;
    }

    public static String catalogoFechasPrint(Date i, Date c) {
        return fechaPrint(i) + " al " + fechaPrint(c);
    }

    public static String fechaPrint(Date d) {
        return df.format(d);
    }

    public static String telefonoPrint(String t) {
        return t.substring(0, 4) + " " + t.substring(4, 7) + " " + t.substring(7);
    }

}
