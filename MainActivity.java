package id.ac.umn.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //TODO Ganti package name sesuai package kalian

    Button btnConfirm;
    EditText txtName;
    EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConfirm = (Button) findViewById(R.id.btnLogin);
        final EditText txtName = (EditText) findViewById(R.id.txtName);
        final EditText txtPass = (EditText) findViewById(R.id.txtPassword);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
                final String name = preferences.getString("NAME","");
                final String pass = preferences.getString("PASSWORD", "");

                if (txtName.getText().toString().equals(name) && txtPass.getText().toString().equals(pass)){
                    Intent i = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getBaseContext(),"Name and/or Password is not filled yet", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
