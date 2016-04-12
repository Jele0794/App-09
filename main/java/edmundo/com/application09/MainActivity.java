package edmundo.com.application09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Properties;

public class MainActivity extends ActivityConexionWeb {

    private EditText campoNombre;
    private EditText campoEdad;
    private EditText campoUniversidad;
    String url;
    TareaAsincronaConexionWeb tareaAsincrona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relacionaViews();
    }

    public void relacionaViews(){
        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoEdad = (EditText) findViewById(R.id.campo_edad);
        campoUniversidad = (EditText) findViewById(R.id.campo_universidad);
    }

    public void usaDatosDeLaWeb(Properties datosDeLaWeb){
        campoNombre.setText(datosDeLaWeb.getProperty("nombre"));
        campoEdad.setText(datosDeLaWeb.getProperty("edad"));
        campoUniversidad.setText(datosDeLaWeb.getProperty("universidad"));
    }

    public void onClick(View view){
        url = "http://192.168.43.247:80/android/mis_properties.html";
        //url = "https://sites.google.com/site/gerardoayalasandata/data/mis_properties.html";
        if(hayConexion()){
            tareaAsincrona = new TareaAsincronaConexionWeb(this);
            tareaAsincrona.execute(url);
        } else {
            Monitor.showMessage(this, "No hay conexión a la red.");
        }
    }

}
