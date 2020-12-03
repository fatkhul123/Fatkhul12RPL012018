package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fatkhul12rpl012018.R;

import java.util.List;

public class SepedaAdapter extends RecyclerView.Adapter<SepedaAdapter.JadwalViewHolder> {
    private Context mContext;


    private List<Sepeda> dataList;

    public SepedaAdapter(Context mContext, List<Sepeda> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;

    }


    @Override
    public JadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.mainlist_sepeda, parent, false);
        return new JadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalViewHolder holder, int position) {
        final Sepeda user = dataList.get(position);
        holder.merk.setText(user.getMerk());
        holder.jenis.setText(user.getJenis());
        holder.harga.setText(user.getHarga());


    }

    @Override
    public int getItemCount() {
        return dataList.size();

    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class JadwalViewHolder extends RecyclerView.ViewHolder {

        private TextView merk, jenis, harga;


        public JadwalViewHolder(View itemView) {
            super(itemView);
            merk = (TextView) itemView.findViewById(R.id.merk);
            jenis = (TextView) itemView.findViewById(R.id.jenis);
            harga = (TextView) itemView.findViewById(R.id.harga);
//            image = itemView.findViewById(R.id.image);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(mContext, Main2Activity.class);
//                    i.putExtra("events", dataList.get(getAdapterPosition()).getStrEvent());
//                    i.putExtra("events2", dataList.get(getAdapterPosition()).getStrThumb());
//                    i.putExtra("events3", dataList.get(getAdapterPosition()).getStrHomeforward());
//                    i.putExtra("events4", dataList.get(getAdapterPosition()).getStrAwayLineupDefense());
//                    i.putExtra("events5", dataList.get(getAdapterPosition()).getStrAwayforward());
//                    i.putExtra("events6", dataList.get(getAdapterPosition()).getStrHomeLineupMidfield());
//                    i.putExtra("events7", dataList.get(getAdapterPosition()).getStrAwayLineupGoalkeeper());
//                    i.putExtra("events8", dataList.get(getAdapterPosition()).getStrAwayLineupMidfield());
//                    i.putExtra("events9", dataList.get(getAdapterPosition()).getStrHomeLineupGoalkeeper());
//                    i.putExtra("events11", dataList.get(getAdapterPosition()).getStrHomeLineupDefense());
//                    i.putExtra("events12", dataList.get(getAdapterPosition()).getStrHomeTeam());
//                    i.putExtra("events13", dataList.get(getAdapterPosition()).getStrAwayTeam());
//                    i.putExtra("events14", dataList.get(getAdapterPosition()).getStrDate());
//                    i.putExtra("events15", dataList.get(getAdapterPosition()).getStrTime());
//                    i.putExtra("events16", dataList.get(getAdapterPosition()).getIntHomeScore());
//                    i.putExtra("events17", dataList.get(getAdapterPosition()).getIntAwayScore());
//
//                    mContext.startActivity(i);
//                }
//            });
        }

    }
}
