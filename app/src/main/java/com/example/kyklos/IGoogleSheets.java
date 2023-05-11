package com.example.kyklos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IGoogleSheets {
    @POST("exec")
    Call<String> getStringRequestBody (@Body String body);
}
