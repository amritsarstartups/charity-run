package com.example.android.charityrun;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP_PC on 15-06-2017.
 */
public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.MyViewHolder> {


    private List<campaign> campaignList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,desc;
        public Button run;

        public MyViewHolder(View view) {
            super(view);
            name=(TextView)view.findViewById(R.id.Name);
            desc=(TextView)view.findViewById(R.id.Desc);
            run=(Button) view.findViewById(R.id.RUN);
        }
    }


    public CampaignAdapter(List<campaign> campaignList) {
        this.campaignList=campaignList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row_campaign, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        campaign cm=campaignList.get(position);
        holder.name.setText(cm.getName());
        holder.desc.setText(cm.getDescription());
        holder.run.setText(cm.getRun_count());
    }

    @Override
    public int getItemCount() {
        return campaignList.size();
    }
}
