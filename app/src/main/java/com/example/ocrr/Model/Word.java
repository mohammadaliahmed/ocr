
package com.example.ocrr.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Word {

    @SerializedName("WordText")
    @Expose
    private String wordText;
    @SerializedName("Left")
    @Expose
    private Float left;
    @SerializedName("Top")
    @Expose
    private Float top;
    @SerializedName("Height")
    @Expose
    private Float height;
    @SerializedName("Width")
    @Expose
    private Float width;

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public Float getLeft() {
        return left;
    }

    public void setLeft(Float left) {
        this.left = left;
    }

    public Float getTop() {
        return top;
    }

    public void setTop(Float top) {
        this.top = top;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

}
