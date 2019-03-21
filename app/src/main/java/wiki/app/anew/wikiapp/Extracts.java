package wiki.app.anew.wikiapp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extracts {

    @SerializedName("*")
    @Expose
    private String p;

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

}