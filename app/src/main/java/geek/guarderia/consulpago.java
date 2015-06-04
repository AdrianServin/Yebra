package geek.guarderia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class consulpago extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulpago);

        View btnConsulta= findViewById(R.id.btnConsulta);
        btnConsulta.setOnClickListener(this);

    }

    @Override
    public void onClick(View vista) {

            Intent WindowsConsulpago = new Intent(this, consulpago1.class);
            startActivity(WindowsConsulpago);

    }

}
