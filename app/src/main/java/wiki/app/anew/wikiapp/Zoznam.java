package wiki.app.anew.wikiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Zoznam extends Activity {
    private ListView list;
    private ArrayList<ZoznamPart> zoznamParts=new ArrayList<>(0);
    private ZoznamAdapter zoznamAdapter;
    private static final String BASE_URL = "https://www.mediawiki.org/w/";
    private int offset=0;
    private boolean loading=false;
    private String hladane;
    private Context context;
    private String pageIdVybrane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoznam);
        list=(ListView)findViewById(R.id.list);
        context=this;
        zoznamAdapter=new ZoznamAdapter(this,zoznamParts);
        list.setAdapter(zoznamAdapter);
        list.setPadding(10,10,10,10);
        list.setOnScrollListener(new AbsListView.OnScrollListener() {

            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount!=0)
                {
                    if(!loading) {
                        offset+=10;
                        nacitajDalsie();
                    }
                }
            }
        });

        AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              System.out.println("onItemClick");
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                WikiInterface wikiInterface = retrofit.create(WikiInterface.class);
                //&action=query&prop=extracts&pageids=39834209&explaintext&excontinue=0&format=json
                pageIdVybrane=""+zoznamAdapter.getItem(position).getPageID();
                Call<ResponseDetail> responseDetail = wikiInterface.getDetail("query","extracts",pageIdVybrane,"true","0","json");
                System.out.println(responseDetail.request().url().toString());
                responseDetail.enqueue(new Callback<ResponseDetail>() {
                    @Override
                    public void onResponse(Call<ResponseDetail> call, retrofit2.Response<ResponseDetail> response) {
                        try {
                            final ResponseDetail body = response.body();
                            body.getQuery().getPages().size();
                            System.out.println(body.getQuery().getPages().get(pageIdVybrane).gettitle()+"onResponseDetail"+body.getQuery().getPages().size());

                             Intent intent = new Intent(context, Detail.class);
                             intent.putExtra("title", body.getQuery().getPages().get(pageIdVybrane).gettitle());
                             intent.putExtra("extract", body.getQuery().getPages().get(pageIdVybrane).getextract());
                             startActivity(intent);
                        }catch(Exception e){
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseDetail> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("onFailureDetail"+t.toString());
                    }
                });

                      }
        };
        list.setOnItemClickListener(myOnItemClickListener);
        Intent intent = getIntent();
        hladane= intent.getStringExtra("vyhladaj");
        hladane=hladane.replace(" ","%20");
        System.out.println("Co idem hladat"+hladane);
        nacitajDalsie();

    }

private void nacitajDalsie(){
    loading=true;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    WikiInterface wikiInterface = retrofit.create(WikiInterface.class);
    Call<Page> page = wikiInterface.get10Pages("query","search","text","size|snippet",hladane,String.valueOf(offset),"json");
    System.out.println(page.request().url().toString());
    page.enqueue(new Callback<Page>() {
        @Override
        public void onResponse(Call<Page> call, retrofit2.Response<Page> response) {
            try {
                System.out.println("onResponse");

                final Page body = response.body();
                Query q= body.getQuery();
                List<Search> pom=q.getSearch();
                for(int i=0;i<pom.size();i++) {
                  //  System.out.println("i"+i+"Title"+pom.get(i).getTitle()+"Snippet"+pom.get(i).getSnippet()+"pageID"+pom.get(i).getPageid());
                    zoznamAdapter.add(new ZoznamPart(pom.get(i).getTitle(),pom.get(i).getSnippet(),false,pom.get(i).getPageid(),pom.get(i).getSize()));
                }
            }catch(Exception e){
            }
            loading=false;
        }
        @Override
        public void onFailure(Call<Page> call, Throwable t) {
            t.printStackTrace();
            loading=false;
            System.out.println("onFailure"+t.toString());
        }
    });
}
    public class ZoznamAdapter extends ArrayAdapter<ZoznamPart> {
        public ZoznamAdapter(Context context, ArrayList<ZoznamPart> zoznamParts) {
            super(context, 0, zoznamParts);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ZoznamPart ZoznamPart = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.zoznam_part, parent, false);
            }
            TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            TextView txtSnippet = (TextView) convertView.findViewById(R.id.txtSnippet);
            TextView txtSaved = (TextView) convertView.findViewById(R.id.txtSaved);

            txtTitle.setText("Title: "+ZoznamPart.getTitle());
            txtTitle.setTextColor(Color.BLACK);
            txtSnippet.setText("Snippet: "+ZoznamPart.getSnippet());
            txtSnippet.setTextColor(Color.DKGRAY);
            txtSaved.setText("Saved: "+String.valueOf(ZoznamPart.getSaved()));
            txtSaved.setTextColor(Color.GRAY);
            return convertView;
        }
    }
}
