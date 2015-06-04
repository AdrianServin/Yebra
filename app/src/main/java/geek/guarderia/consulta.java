package geek.guarderia;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class consulta extends ActionBarActivity implements View.OnClickListener{

    private String Nombre,Sangre,Auxiliar,Cuidados,Edad,Numero;
    private EditText ID;
    public String url = "http://10.0.0.99/index.php?data=",urlData = null;
    JSONObject jsonObject;
    JSONArray data;
    HttpAsyncTask httpGetTask;
    String ResulConsulta="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta);

            ID= (EditText)findViewById(R.id.id);
            View btnConsulta= findViewById(R.id.btnConsulta);
            btnConsulta.setOnClickListener(this);


    }

    @Override
    public void onClick(View vista) {

        if(!ID.getText().toString().equals("")) {
            String Consulta="select concat(r.nombre,\" \",r.apellidoP,\" \",r.apellidoM)as Nombre," +
                    "TIMESTAMPDIFF(YEAR, SUBSTRING( r.curp, 5, 6 ) , NOW())as Edad, r.sangre, " +
                    "concat(t.Nombre,\" \",t.ApellidoP,\" \",t.ApellidoM)as Auxiliar,r.cuidadosespeciales as Cuidados,t.Telefono as Telefono from registro r, tutor t where r.id_tutor=t.id_tutor and r.id_registro="+ID.getText();

            Conexion(Consulta);

        }

    }

    public void Conexion(String Consulta){

        jsonObject = new JSONObject();
        data = new JSONArray();
        httpGetTask = new HttpAsyncTask();

        try {

            jsonObject.put("consulta",Consulta);
            data.put(jsonObject);
            urlData = url + URLEncoder.encode(data.toString(), "UTF-8");
            httpGetTask.execute(urlData);


        } catch (JSONException e) { e.printStackTrace();
        } catch (UnsupportedEncodingException e) { e.printStackTrace();}

    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";

        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();

        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            ResulConsulta= "{data:"+result+"}";

            try {

                JSONObject json = new JSONObject(ResulConsulta);
                JSONArray datos = json.getJSONArray("data");

                Nombre =  datos.getJSONObject(0).getString("Nombre");
                Edad= datos.getJSONObject(0).getString("Edad");
                Sangre = datos.getJSONObject(0).getString("sangre");
                Auxiliar = datos.getJSONObject(0).getString("Auxiliar");
                Cuidados = datos.getJSONObject(0).getString("Cuidados");
                Numero = datos.getJSONObject(0).getString("Telefono");


            } catch (JSONException e) {e.printStackTrace();}

            if(!ResulConsulta.equals("{data:[]}")){
            Intent WindowsConsulta = new Intent(getBaseContext(), consulta1.class);
            WindowsConsulta.putExtra("Nombre", Nombre);
            WindowsConsulta.putExtra("Edad", Edad);
            WindowsConsulta.putExtra("Sangre", Sangre);
            WindowsConsulta.putExtra("Auxiliar", Auxiliar);
            WindowsConsulta.putExtra("Cuidados", Cuidados);
            WindowsConsulta.putExtra("Numero", Numero);
            startActivity(WindowsConsulta);
            }else {
                Toast.makeText(getBaseContext(), "Registro No Encontrado", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
