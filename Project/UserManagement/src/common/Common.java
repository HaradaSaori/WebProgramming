package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

	public static Date c_date(String str) {
		Date date = null;
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
			date = sdFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

        return date;
	}

}
