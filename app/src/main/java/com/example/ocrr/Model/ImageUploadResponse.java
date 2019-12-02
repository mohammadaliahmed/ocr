package com.example.ocrr.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageUploadResponse {
    @SerializedName("uploaded")
    @Expose
    private Boolean uploaded;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("value2")
    @Expose
    private String value2;

    public Boolean getUploaded() {
        return uploaded;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}
