package com.example.ocrr;

import com.example.ocrr.Model.ImageUploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserClient {


    @POST("ocr.php")
    @Multipart
    Call<ImageUploadResponse> uploadFile(
            @Part MultipartBody.Part file, @Part("file") RequestBody name

    );


}
