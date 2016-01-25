package ch.outline.time.android.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
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
public class LoginRequest extends StringRequest {
    private TimeController tc;

    public LoginRequest(TimeController tc, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        this.tc = tc;
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = super.getParams();

        if(params == null || params.equals(Collections.emptyMap())) {
            params = new HashMap<String, String>();
        }

        params.put("_n", this.tc.getUser());
        params.put("_p", this.tc.getPassword());
        params.put("login", "Login");

        return params;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        this.checkForSessionCookie(response.headers);

        return super.parseNetworkResponse(response);
    }

    private void checkForSessionCookie(Map<String, String> headers) {
        String cookie = headers.get("Set-Cookie");

        if(cookie == null) {
            return;
        }

        // First split by ; and get the first group, then split by = and get the second occurence
        // Example Header: PHPSESSID=pbhee1jpshohn5qplhs00ba6e03ai0r7; path=/; HttpOnly
        this.tc.setSession(cookie.split(";")[0].split("=")[1]);
    }
}
