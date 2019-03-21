package wiki.app.anew.wikiapp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Warnings {

    @SerializedName("extracts")
    @Expose
    private Extracts extracts;

    public Extracts getExtracts() {
        return extracts;
    }

    public void setExtracts(Extracts extracts) {
        this.extracts = extracts;
    }

}