package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.fatkhul12rpl012018.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String ROLE = "role";
    public static final String NOKTP = "noktp";
    public static final String NOHP = "nohp";
    public static final String EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button login = findViewById(R.id.btnlogin);
        final EditText txtusername = findViewById(R.id.username);
        final EditText txtpass = findViewById(R.id.password);
        final TextView regis = findViewById(R.id.register);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = txtusername.getText().toString();
                final String password = txtpass.getText().toString();
                AndroidNetworking.post("http://192.168.43.132/API1_FATKHUL_12RPL1/login.php")
                        .addBodyParameter("username", txtusername.getText().toString() )
                        .addBodyParameter("password", txtpass.getText().toString() )
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("cek response", String.valueOf(response));
                                if ((username.isEmpty() || password.isEmpty())) {
                                    Toast.makeText(LoginActivity.this, "semuanya harus di isi", Toast.LENGTH_SHORT).show();
                                } else {

                                    try {
                                        int suksess = response.getInt("suksess");
                                        int id = response.getInt("id");
                                        String role = response.getString("role");
                                        String ktp = response.getString("ktp");
                                        String hp = response.getString("hp");
                                        String mail = response.getString("email");
                                        if (suksess == 1 ){
                                            Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString(ID, String.valueOf(id));
                                            editor.putString(ROLE,role);
                                            editor.putString(NOHP,hp);
                                            editor.putString(NOKTP,ktp);
                                            editor.putString(EMAIL,mail);
                                            editor.putString(USERNAME, String.valueOf(username));
                                            editor.apply();
                                            if (role.equalsIgnoreCase("admin")){
                                                Intent in = new Intent(LoginActivity.this, DashboardAdmin.class);
                                                startActivity(in);
                                                finish();
                                            } else {
                                                Intent in = new Intent(LoginActivity.this, DashboardUser.class);
                                                startActivity(in);
                                                finish();
                                            }

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }


                            }
                            @Override
                            public void onError(ANError error) {
                                Toast.makeText(LoginActivity.this, "gagal",Toast.LENGTH_SHORT).show();
                                Log.d("GZS", "onError: " + error.getErrorBody());
                                Log.d("GZS", "onError: " + error.getLocalizedMessage());
                                Log.d("GZS", "onError: " + error.getErrorDetail());
                                Log.d("GZS", "onError: " + error.getResponse());
                                Log.d("GZS  ", "onError: " + error.getErrorCode());
                            }
                        });

            }
        });

    }
}