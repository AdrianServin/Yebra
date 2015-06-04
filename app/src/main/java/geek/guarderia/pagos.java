package geek.guarderia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class pagos extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagos);

        View btnadd= findViewById(R.id.btnAgregar);
        btnadd.setOnClickListener(this);

        View btnview= findViewById(R.id.btnVer);
        btnview.setOnClickListener(this);

    }

    @Override
    public void onClick(View vista) {

        if(vista.getId()==findViewById(R.id.btnAgregar).getId()){
            Intent WindowsAgregar= new Intent(this,pagosadd.class);
            startActivity(WindowsAgregar);
        }

        if(vista.getId()==findViewById(R.id.btnVer).getId()){
            Intent WindowsView= new Intent(this,pagosview.class);
            startActivity(WindowsView);
        }


    }

}
