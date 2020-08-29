package UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fatkhul12rpl012018.R;

import java.util.ArrayList;

import ADAPTER.AdapterDashboard;
import ADAPTER.Isidash;

public class Dashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterDashboard adapter;
    private ArrayList<Isidash> warungArrayList;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ImageView profile = findViewById(R.id.userpict);
        final TextView tuser = findViewById(R.id.textViewUser);

        adapter = new AdapterDashboard(warungArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Dashboard.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in =  new Intent(Dashboard.this, User.class);
//                startActivity(in);
            }
        });

//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        String username = sharedPreferences.getString(USERNAME, "");
//        tuser.setText( "Hello " + username);

    }

    void addData(){
        warungArrayList = new ArrayList<>();
        warungArrayList.add(new Isidash("Bu Tam"));
        warungArrayList.add(new Isidash("Bu Wati"));
        warungArrayList.add(new Isidash("Kantin Koperasi"));
        warungArrayList.add(new Isidash("Rus Mart"));
        warungArrayList.add(new Isidash("Teh Poci"));

    }

}