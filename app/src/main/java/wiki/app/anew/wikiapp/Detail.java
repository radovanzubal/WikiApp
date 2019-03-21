package wiki.app.anew.wikiapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.widget.TextView;

public class Detail extends Activity {
    private TextView detailtxt;
    private String title,extract;
    private int screenSirka,screenVyska;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        detailtxt=(TextView)findViewById(R.id.detailtxt);

        screenSirka= this.getResources().getDisplayMetrics().widthPixels;
        screenVyska= this.getResources().getDisplayMetrics().heightPixels;

        Intent intent = getIntent();
        title=intent.getStringExtra("title");
        extract=intent.getStringExtra("extract");
        detailtxt.setText(title+"\n"+extract);
        detailtxt.setTextColor(Color.BLACK);
        detailtxt.setTextSize(TypedValue.COMPLEX_UNIT_PX,screenVyska/50);
        detailtxt.setMovementMethod(new ScrollingMovementMethod());

    }

}
