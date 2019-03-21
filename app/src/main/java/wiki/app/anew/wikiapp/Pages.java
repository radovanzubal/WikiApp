package wiki.app.anew.wikiapp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Pages {

    @SerializedName("pageid")
    @Expose
    private Integer pageid;

    @SerializedName("ns")
    @Expose
    private Integer ns;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("extract")
    @Expose
    private String extract;

    public void setPageid(int pageid){this.pageid=pageid;}
    public void setns(int ns){this.ns=ns;}
    public void settitle(String title){this.title=title;}
    public void setextract(String extract){this.extract=extract;}
    public int getpageid(){return pageid;}
    public int getns(){return ns;}
    public String gettitle(){return title;}
    public String getextract(){return extract;}

}
