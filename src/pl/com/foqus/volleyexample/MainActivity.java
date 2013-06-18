
package pl.com.foqus.volleyexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import de.greenrobot.inject.Injector;
import de.greenrobot.inject.annotation.InjectView;

public class MainActivity extends Activity {

    @InjectView(id = R.id.response)
    TextView mResponseTextView;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Injector.injectInto(this);
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://bot.whatismyipaddress.com/";
        Listener<String> listener = new Listener<String>() {

            @Override
            public void onResponse(String response) {
                mResponseTextView.setText(response);
            }
        };
        ErrorListener errorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mResponseTextView.setText(error.getLocalizedMessage());
            }
        };
        mResponseTextView.setText("");
        Request<?> request = new StringRequest(url, listener, errorListener);
        mRequestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
