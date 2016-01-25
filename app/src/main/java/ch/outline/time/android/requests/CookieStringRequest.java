package ch.outline.time.android.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ch.outline.time.android.controllers.TimeController;

/**
 * Created by Ahatius on 24.01.2016.
 */
public class CookieStringRequest extends StringRequest {
    private TimeController tc;

    public CookieStringRequest(TimeController tc, int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.tc = tc;
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
