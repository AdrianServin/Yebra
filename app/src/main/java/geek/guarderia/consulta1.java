package geek.guarderia;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class consulta1 extends ActionBarActivity implements View.OnClickListener{

    private EditText txtnom,txtedad,txtaux,txtcuidados;
    private String num;
    private Spinner cbxsan,cbxedad, sp;
    public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta1);

        txtnom= (EditText)findViewById(R.id.txtnom);
        txtedad= (EditText)findViewById(R.id.txtedad);
        cbxsan= (Spinner)findViewById(R.id.cbxsan);
        txtaux= (EditText)findViewById(R.id.txtaux);
        txtcuidados= (EditText)findViewById(R.id.txtcuidados);


        bundle = getIntent().getExtras();
        txtnom.setText(bundle.getString("Nombre"));
        txtedad.setText(bundle.getString("Edad"));
        txtaux.setText(bundle.getString("Auxiliar"));
        txtcuidados.setText(bundle.getString("Cuidados"));
        num=bundle.getString("Numero");


        sp = (Spinner) findViewById(R.id.cbxsan);

        for(int i=0;i<9;i++){
            sp.setSelection(i);
            if(sp.getSelectedItem().toString().equals(bundle.getString("Sangre"))){
                i=10;
                sp.setEnabled(false);

            }
        }



        View btnEmerg= findViewById(R.id.btnEmerg);
        btnEmerg.setOnClickListener(this);

    }

    public void onClick(View vista) {

        if(vista.getId()==findViewById(R.id.btnEmerg).getId()){
            call();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         int id = item.getItemId();
      if (id == R.id.action_edit) {
              txtnom.setEnabled(true);
              txtedad.setEnabled(true);
              txtaux.setEnabled(true);
              txtcuidados.setEnabled(true);
              cbxsan.setEnabled(true);
              cbxedad.setEnabled(true);
              return true;

        }else if (id == R.id.action_save) {
          txtnom.setEnabled(false);
          txtedad.setEnabled(false);
          txtaux.setEnabled(false);
          txtcuidados.setEnabled(false);
          cbxsan.setEnabled(false);
          cbxedad.setEnabled(false);
          return true;
      }

        return super.onOptionsItemSelected(item);
    }

    private void call(){
        try {
            Toast.makeText(getBaseContext(), "Realizando Llamada", Toast.LENGTH_SHORT).show();
            Uri numero = Uri.parse( "tel:"+num );
            Intent intent = new Intent(Intent.ACTION_CALL, numero);
            startActivity(intent);


        }
        catch (ActivityNotFoundException activityException) {
            Toast.makeText(getBaseContext(), "Llamada Fallida", Toast.LENGTH_SHORT).show();
        }

    }

}
