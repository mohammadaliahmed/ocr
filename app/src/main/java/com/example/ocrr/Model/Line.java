
package com.example.ocrr.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Line {

    @SerializedName("LineText")
    @Expose
    private String lineText;
    @SerializedName("Words")
    @Expose
    private List<Word> words = null;
    @SerializedName("MaxHeight")
    @Expose
    private Float maxHeight;
    @SerializedName("MinTop")
    @Expose
    private Float minTop;

    public String getLineText() {
        return lineText;
    }

    public void setLineText(String lineText) {
        this.lineText = lineText;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Float getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Float maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Float getMinTop() {
        return minTop;
    }

    public void setMinTop(Float minTop) {
        this.minTop = minTop;
    }

}
