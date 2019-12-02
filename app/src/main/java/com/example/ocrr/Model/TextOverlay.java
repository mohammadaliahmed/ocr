
package com.example.ocrr.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TextOverlay {

    @SerializedName("Lines")
    @Expose
    private List<Line> lines = null;
    @SerializedName("HasOverlay")
    @Expose
    private Boolean hasOverlay;
    @SerializedName("Message")
    @Expose
    private String message;

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Boolean getHasOverlay() {
        return hasOverlay;
    }

    public void setHasOverlay(Boolean hasOverlay) {
        this.hasOverlay = hasOverlay;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
