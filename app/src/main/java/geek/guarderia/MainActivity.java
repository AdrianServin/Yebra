package geek.guarderia;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.net.Uri;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View btnConsulta= findViewById(R.id.btnConsulta);
        btnConsulta.setOnClickListener(this);

        View btnEmerg= findViewById(R.id.btnEmerg);
        btnEmerg.setOnClickListener(this);

        View btnconsulpago= findViewById(R.id.btnconsulpago);
        btnconsulpago.setOnClickListener(this);

        View btnnecesidad= findViewById(R.id.btnNecesidad);
        btnnecesidad.setOnClickListener(this);

        View btnpago= findViewById(R.id.btnPagos);
        btnpago.setOnClickListener(this);

    }

    @Override
    public void onClick(View vista) {
        if(vista.getId()==findViewById(R.id.btnConsulta).getId()){
            Intent WindowsConsulta1= new Intent(this,consulta.class);
            startActivity(WindowsConsulta1);
        }

        if(vista.getId()==findViewById(R.id.btnconsulpago).getId()){
            Intent WindowsConsulpago= new Intent(this,consulpago.class);
            startActivity(WindowsConsulpago);
        }

        if(vista.getId()==findViewById(R.id.btnNecesidad).getId()){
            Intent WindowsConsulpago= new Intent(this,necesidad.class);
            startActivity(WindowsConsulpago);
        }

        if(vista.getId()==findViewById(R.id.btnPagos).getId()){
            Intent WindowsConsulpago= new Intent(this,pagos.class);
            startActivity(WindowsConsulpago);
        }

        if(vista.getId()==findViewById(R.id.btnEmerg).getId()){
            call();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void call(){
        try {
            Toast.makeText(getBaseContext(), "Realizando Llamada", Toast.LENGTH_SHORT).show();
            Uri numero = Uri.parse( "tel:6862105281" );
            Intent intent = new Intent(Intent.ACTION_CALL, numero);
            startActivity(intent);


        }
        catch (ActivityNotFoundException activityException) {
            Toast.makeText(getBaseContext(), "Llamada Fallida", Toast.LENGTH_SHORT).show();
        }

    }


}
