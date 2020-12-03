package UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fatkhul12rpl012018.R;

import static UI.LoginActivity.ID;
import static UI.LoginActivity.ROLE;
import static UI.LoginActivity.SHARED_PREFS;

public class SplashScreen extends AppCompatActivity {
    private int SLEEP_TIMER = 1;
    private String id, role;
    private boolean isFormFilled = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        id = sharedPreferences.getString(ID, "");
        role = sharedPreferences.getString(ROLE, "");
        isFormFilled = true;
        if (role.isEmpty()) {
            isFormFilled = false;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isFormFilled) {
                    if (role.equalsIgnoreCase("Customer")) {
                        Intent intent = new Intent(getApplicationContext(), DashboardUser.class);
                        startActivity(intent);
                        Toast.makeText(SplashScreen.this, role, Toast.LENGTH_SHORT).show();
                        finish();
                        finishAffinity();
                    } else if (role.equalsIgnoreCase("Admin")) {
                        Intent intent = new Intent(getApplicationContext(), DashboardAdmin.class);
                        startActivity(intent);
                        Toast.makeText(SplashScreen.this, role, Toast.LENGTH_SHORT).show();
                        finish();
                        finishAffinity();
                    }

                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }
        }, 3000);


    }


}