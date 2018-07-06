package com.example.admin.nycschools;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.nycschools.activities.MainActivity;
import com.example.admin.nycschools.activities.SchoolDetails;
import com.example.admin.nycschools.model.SATResponse;
import com.example.admin.nycschools.model.School;
import com.example.admin.nycschools.network.NYCSchoolDataService;
import com.example.admin.nycschools.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> implements View.OnClickListener {

    private ArrayList<School> responses;
    private Context mainActivity;
    private List<SATResponse> details;

    public SchoolAdapter(List<School> responses, Context mainActivity) {
        this.responses = new ArrayList<>(responses);
        this.mainActivity = mainActivity;
    }

    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.school_row_layout, parent, false);
        return new SchoolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SchoolViewHolder holder, int position) {
        if (responses.get(position).getSchoolName() != null)
            holder.name.setText(responses.get(position).getSchoolName());
        if (responses.get(position).getPrimaryAddressLine1() != null)
            holder.address.setText(responses.get(position).getPrimaryAddressLine1());
        if (responses.get(position).getSchoolEmail() != null)
            holder.emal.setText(responses.get(position).getSchoolEmail());
        holder.itemView.setId(position);
        holder.itemView.setOnClickListener(this);
        System.out.println(responses.get(position).getSchoolName());

    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    @Override
    public void onClick(final View view) {
        NYCSchoolDataService service = RetrofitInstance.getRetrofitInstance().create(NYCSchoolDataService.class);
        Call<List<SATResponse>> call = service.getSATResultsCall(responses.get(view.getId()).getDbn());
        Log.wtf("URL Called", call.request().url() + "");
        final Intent intent = new Intent(mainActivity, SchoolDetails.class);
        intent.putExtra("name",((TextView)view.findViewById(R.id.tvName)).getText());
        intent.putExtra("address", ((TextView) view.findViewById(R.id.tvAddress)).getText());
        intent.putExtra("email", ((TextView)view.findViewById(R.id.tvEmail)).getText());
        call.enqueue(new Callback<List<SATResponse>>() {
            @Override
            public void onResponse(Call<List<SATResponse>> call, Response<List<SATResponse>> response) {

                details = response.body();
                intent.putExtra("math", details.get(0).getSatMathAvgScore());
                intent.putExtra("read", details.get(0).getSatCriticalReadingAvgScore());
                intent.putExtra("write", details.get(0).getSatWritingAvgScore());
                mainActivity.startActivity(intent);

            }

            @Override
            public void onFailure(Call<List<SATResponse>> call, Throwable t) {

                Toast.makeText(mainActivity, "something whent wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }

    class SchoolViewHolder extends RecyclerView.ViewHolder {
        TextView name, emal, address;

        public SchoolViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.tvName);
            this.emal = itemView.findViewById(R.id.tvEmail);
            this.address = itemView.findViewById(R.id.tvAddress);
        }
    }

}
