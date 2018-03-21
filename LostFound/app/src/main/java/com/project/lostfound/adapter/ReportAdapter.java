package com.project.lostfound.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.lostfound.R;
import com.project.lostfound.model.Report;
import com.project.lostfound.view.ItemDetails;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private List<Report> reportList;
    private Context mContext = null;

    public ReportAdapter(Context context, List<Report> list) {
        mContext = context;
        this.reportList = list;
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_row, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        final Report report = reportList.get(holder.getAdapterPosition());

        holder.bind(report);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Toast.makeText(mContext, ""+t , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, ItemDetails.class);
                intent.putExtra("id", report.getReportid());
                mContext.startActivity(intent);
//                if (mContext instanceof Activity) {
//
//                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public void setFilter(List<Report> reports) {
        reportList = new ArrayList<>();
        reportList.addAll(reports);
        notifyDataSetChanged();
    }

    class ReportViewHolder extends RecyclerView.ViewHolder {

        TextView itemID;
        TextView itemName;
        TextView itemDetails;

        ReportViewHolder(View itemView) {
            super(itemView);
            itemID = itemView.findViewById(R.id.reportid);
            itemName = itemView.findViewById(R.id.itemName);
            itemDetails = itemView.findViewById(R.id.itemDescription);
        }

        void bind(Report report) {
            itemID.setText(report.getReportid());
            itemName.setText(report.getItemname());
            itemDetails.setText(report.getDetails());
        }
    }
}