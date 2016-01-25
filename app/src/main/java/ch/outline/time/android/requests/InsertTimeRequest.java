package ch.outline.time.android.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.outline.time.android.controllers.TimeController;

/**
 * Created by Ahatius on 24.01.2016.
 */
public class InsertTimeRequest extends StringRequest{
    TimeController tc;

    public InsertTimeRequest(TimeController tc, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        this.tc = tc;
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        String[] dateArray = this.tc.getDateArray();
        Map<String, String> params = super.getParams();

        if(params == null || params.equals(Collections.emptyMap())) {
            params = new HashMap<String, String>();
        }

        params.put("_w_tag", dateArray[0]);
        params.put("_w_monat", dateArray[1]);
        params.put("_w_jahr", dateArray[2]);
        params.put("_w_stunde", dateArray[3]);
        params.put("_w_minute", dateArray[4]);
        params.put("absenden", "OK");

        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();

        if(headers == null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }

        if(this.tc.getSession() != null && !this.tc.getSession().equals("")) {
            headers.put("Cookie", "PHPSESSID=" + this.tc.getSession());
        }

        return headers;
    }
}
