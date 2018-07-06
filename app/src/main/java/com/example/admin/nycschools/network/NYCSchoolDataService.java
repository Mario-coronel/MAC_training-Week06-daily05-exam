package com.example.admin.nycschools.network;

import com.example.admin.nycschools.model.SATResponse;
import com.example.admin.nycschools.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYCSchoolDataService {

    @GET("resource/734v-jeq5.json")
    Call<List<SATResponse>> getSATResultsCall(@Query("dbn") String dbn);

    @GET("resource/97mf-9njv.json")
    Call<List<School>> getSchools();

}
