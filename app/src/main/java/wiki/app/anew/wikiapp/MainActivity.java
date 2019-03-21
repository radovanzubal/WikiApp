package wiki.app.anew.wikiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class MainActivity extends Activity {
Button search;
TextView text;
EditText edit;
RelativeLayout relativeLayout;
private int screenSirka,screenVyska;
private Context context;
boolean mamInternet=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        relativeLayout=(RelativeLayout) findViewById(R.id.rel);
        search=(Button) findViewById(R.id.button);
        text=(TextView) findViewById(R.id.text);
        edit=(EditText) findViewById(R.id.edit);

        isOnline();
        screenSirka= this.getResources().getDisplayMetrics().widthPixels;
        screenVyska= this.getResources().getDisplayMetrics().heightPixels;

        text.setY(0);
        text.setTextColor(Color.BLACK);
        edit.setY(screenVyska/3);
        search.setY(screenVyska/2);

        search.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view) {
              if(!mamInternet){
                  isOnline();
              }
              String hladam = edit.getText().toString();
              System.out.println("hladam"+hladam);
              if (!hladam.equals("")) {
                  if(mamInternet) {
                      Intent intent = new Intent(context, Zoznam.class);
                      intent.putExtra("vyhladaj", hladam);
                      startActivity(intent);
                  }else{
                      Toast.makeText(context,"No internet!",Toast.LENGTH_LONG).show();
                  }
              }else{
                  Toast.makeText(context,"Zadaj text na vyhľadanie!",Toast.LENGTH_LONG).show();
              }
          }
        }
        );
    }
    public void isOnline() {
        try{
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int timeoutMs = 1500;
                        Socket sock = new Socket();
                        SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

                        sock.connect(sockaddr, timeoutMs);
                        sock.close();

                        mamInternet = true;
                    } catch (IOException e) {
                        mamInternet = false;
                    }
                }
            });
            thread.start();
            thread.join();
        }catch(InterruptedException ee){mamInternet=false;ee.printStackTrace();}
    }
}
