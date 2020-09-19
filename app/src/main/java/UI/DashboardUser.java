package UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fatkhul12rpl012018.R;

public class DashboardUser extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String ROLE = "role";
    public static final String NOKTP = "noktp";
    public static final String NOHP = "nohp";
    public static final String EMAIL = "email";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);

        TextView user = findViewById(R.id.username);
        TextView ktp = findViewById(R.id.noktp);
        TextView hp = findViewById(R.id.nohp);
        TextView mail = findViewById(R.id.email);
        Button logout = findViewById(R.id.logout);
        CardView listsepeda = findViewById(R.id.card1);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String username = sharedPreferences.getString(USERNAME, "");
        String noktp = sharedPreferences.getString(NOKTP, "");
        String nohp = sharedPreferences.getString(NOHP, "");
        String email = sharedPreferences.getString(EMAIL, "");
        user.setText(username);
        ktp.setText(noktp);
        hp.setText(nohp);
        mail.setText(email);

        listsepeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(DashboardUser.this, ListSepeda.class);
                startActivity(in);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DashboardUser.this)
                        .setMessage("Anda yakin ingin keluar ?")
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                {
                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.clear();
                                    editor.commit();
                                    Intent in = new Intent(DashboardUser.this, LoginActivity.class);
                                    startActivity(in);
                                    finish();
                                }
                            }

                        }).show();
            }
        });


    }


}