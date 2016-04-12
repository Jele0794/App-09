package edmundo.com.application09;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Edmundo on 4/11/16.
 */
public class TareaAsincronaConexionWeb extends AsyncTask<String, Void, String>{

    Properties datos;
    ActivityConexionWeb activityConexionWeb;

    public TareaAsincronaConexionWeb(ActivityConexionWeb activityConexionWeb){
        this.activityConexionWeb = activityConexionWeb;
    }

    @Override
    protected String doInBackground(String[] urls){
        String mensaje;
        try{
            obtieneDatosDeLaWeb(urls[0]);
            mensaje = "Se obtuvieron los datos";
        }catch (Exception e){
            mensaje = "No se tuvo acceso a la URL. Quizás la URL no es válida";
        }
        return mensaje;
    }

    @Override
    protected void onPostExecute(String mensajeDeDoInBackground){
        System.out.println("@@@@@ " + mensajeDeDoInBackground);
        activityConexionWeb.usaDatosDeLaWeb(datos);
    }

    private Properties lectura(InputStream inputStream) throws IOException{
        Properties properties;
        properties = new Properties();
        properties.loadFromXML(inputStream);
        return properties;
    }

    private void obtieneDatosDeLaWeb(String urlEntrada) throws IOException{
        InputStream inputStream;
        URL url;
        HttpURLConnection conexion;
        int codigoDeRespuesta;

        inputStream = null;
        try{
            url = new URL(urlEntrada);
            conexion = (HttpURLConnection) url.openConnection();
            conexion.setReadTimeout(10000);
            conexion.setConnectTimeout(15000);
            conexion.setRequestMethod("GET");
            conexion.setDoInput(true);
            conexion.connect();

            codigoDeRespuesta = conexion.getResponseCode();
            System.out.println("++++++++++++ Código de respuesta: " + codigoDeRespuesta);
            inputStream = conexion.getInputStream();
            datos = lectura(inputStream);

        } finally {
            if (inputStream != null){
                inputStream.close();
            }
        }

    }

}
