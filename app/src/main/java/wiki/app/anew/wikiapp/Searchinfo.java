package wiki.app.anew.wikiapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Searchinfo {

    @SerializedName("totalhits")
    @Expose
    private Integer totalhits;

    public Integer getTotalhits() {
        return totalhits;
    }

    public void setTotalhits(Integer totalhits) {
        this.totalhits = totalhits;
    }

}