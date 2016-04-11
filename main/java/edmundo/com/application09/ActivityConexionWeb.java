package edmundo.com.application09;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Properties;

public abstract class ActivityConexionWeb extends Activity implements WebConnectable{

    public abstract void usaDatosDeLaWeb(Properties datosDeLaWeb);

    public boolean hayConexion(String url){
        boolean siHayconexion = false;
        ConnectivityManager connectivityManager;
        NetworkInfo networkInfo;

        connectivityManager =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            siHayconexion = true;
        }
        return siHayconexion;
    }

}
