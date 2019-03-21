package wiki.app.anew.wikiapp;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("searchinfo")
    @Expose
    private Searchinfo searchinfo;
    @SerializedName("search")
    @Expose
    private List<Search> search = null;
/*pre zistenie detailu stranky*/
@SerializedName("pages")
@Expose
private Map<String, Pages> pages = null;
public void setPages(Map<String, Pages> pages){this.pages=pages;}
public Map<String, Pages> getPages(){return pages;}


///////////////////////////////
    public Searchinfo getSearchinfo() {
        return searchinfo;
    }

    public void setSearchinfo(Searchinfo searchinfo) {
        this.searchinfo = searchinfo;
    }

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

}