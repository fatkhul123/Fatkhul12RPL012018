package UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.fatkhul12rpl012018.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.Sepeda;
import Adapter.SepedaAdapter;
import Adapter.UserAdapter;

import static UI.LoginActivity.ID;
import static UI.LoginActivity.ROLE;
import static UI.LoginActivity.SHARED_PREFS;

public class ListSepeda extends AppCompatActivity {


    private RecyclerView user;
    private SepedaAdapter usadap;
    private List<Sepeda> sepedaList = new ArrayList<Sepeda>();
    private ProgressDialog mProgress;
    SwipeRefreshLayout swipeLayout;
    private Toolbar toolbar;
    private String id, role;
    private Button add;
    private EditText kode, nama, jenis, harga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sepeda);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("List Sepeda");
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        id = sharedPreferences.getString(ID, "");
        role = sharedPreferences.getString(ROLE, "");
        user = findViewById(R.id.recycler_view);
        add = findViewById(R.id.adddata);
        swipeLayout = findViewById(R.id.swipe_container);



        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                sepedaList.clear();
                userapi();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 1000);
                Toast.makeText(getApplicationContext(), "DATA SIAP", Toast.LENGTH_SHORT).show();// Delay in millis
            }
        });
        setupRecycler();
        userapi();

    }

    private void setupRecycler() {
        usadap = new SepedaAdapter(this, sepedaList);
        user.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        user.setHasFixedSize(true);
        user.setAdapter(usadap);
    }

    private void userapi() {
        AndroidNetworking.get("http://192.168.1.4/API1_FATKHUL_12RPL1/show_sepeda.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.optString("STATUS");
                            String message = response.optString("MESSAGE");
                            String sender = response.optString("SENDER");
                            if (status.equalsIgnoreCase("SUCCESS")) {
                                JSONArray hasilList = response.optJSONObject("PAYLOAD").optJSONArray("DATA");

                                if (hasilList == null) return;

                                for (int i = 0; i < hasilList.length(); i++) {
                                    JSONObject hasil = hasilList.getJSONObject(i);
                                    Sepeda item = new Sepeda();
                                    item.setMerk(hasil.getString("merk"));
                                    item.setJenis(hasil.getString("jenis"));
                                    item.setHarga(hasil.getString("hargasewa"));
                                    sepedaList.add(item);
                                }

                            }
                            usadap.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("", "onError: " + anError.getErrorBody());
                        Toast.makeText(getApplicationContext(), "periksa koneksi anda", Toast.LENGTH_SHORT).show();
                    }
                });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ListSepeda.this, AddSepeda.class);
                startActivity(in);
            }
        });
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}