package UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fatkhul12rpl012018.R;

import static UI.LoginActivity.ID;
import static UI.LoginActivity.ROLE;
import static UI.LoginActivity.SHARED_PREFS;

public class SplashScreen extends AppCompatActivity {
    private int SLEEP_TIMER = 1;
    private String id, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        id = sharedPreferences.getString(ID, "");
        role = sharedPreferences.getString(ROLE, "");
        LogoLuncher logoLuncher = new LogoLuncher();
        logoLuncher.start();
    }

    private class LogoLuncher extends Thread {
        public void run() {
            try {
                sleep(1000 * SLEEP_TIMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (id.isEmpty()) {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                SplashScreen.this.finish();
            } else {
                if (role == "admin"){
                    Intent in = new Intent(SplashScreen.this, Dashboard.class);
                    startActivity(in);
                    finish();
                } else {
                    Intent in = new Intent(SplashScreen.this, Dashboard.class);
                    startActivity(in);
                    finish();
                }
            }

        }
    }
}