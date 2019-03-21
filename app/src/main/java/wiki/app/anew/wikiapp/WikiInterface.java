package wiki.app.anew.wikiapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



//action=query&list=search&srwhat=text&srprop=snippet&srsearch=London&sroffset=10
public interface WikiInterface {
    @GET("api.php")
    Call<Page> get10Pages(@Query("action") String action,@Query("list") String list,@Query("srwhat") String srwhat,@Query("srprop") String srprop,@Query("srsearch") String srsearch,@Query("sroffset") String sroffset,@Query("format") String format);
    //&action=query&prop=extracts&pageids=39834209&explaintext&excontinue=0&format=json
    @GET("api.php")
    Call<ResponseDetail> getDetail(@Query("action") String action,@Query("prop") String prop,@Query("pageids") String pageids,@Query("explaintext") String explaintext,@Query("excontinue") String excontinue,@Query("format") String format);

}