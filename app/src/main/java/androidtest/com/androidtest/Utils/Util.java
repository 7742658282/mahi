package androidtest.com.androidtest.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by e071367 on 2/22/2018.
 */

public class Util {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String dateFormat(String date) {
        SimpleDateFormat formatDate;
        formatDate= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date result;
        try {
            result = formatDate.parse(date);
            System.out.println("date:" + result); //prints date in current locale
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            date = sdf.format(result);
            System.out.println(sdf.format(result)); //prints date in the format sdf
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}