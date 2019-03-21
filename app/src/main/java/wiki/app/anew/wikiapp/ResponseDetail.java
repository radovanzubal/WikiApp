package wiki.app.anew.wikiapp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ResponseDetail {

    @SerializedName("batchcomplete")
    @Expose
    private String batchcomplete;

    @SerializedName("warnings")
    @Expose
    private Warnings warnings;

    @SerializedName("query")
    @Expose
    private Query query;

    public String getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public Warnings getWarnings() {
        return warnings;
    }

    public void setWarnings(Warnings warnings) {
        this.warnings = warnings;
    }
    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
