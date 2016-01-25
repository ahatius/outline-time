package ch.outline.time.android.singletons;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ahatius on 24.01.2016.
 */
public class RequestQueueSingleton {
    private static RequestQueueSingleton rqsInstance;
    private RequestQueue requestQueue;
    private static Context rqsContext;

    private RequestQueueSingleton(Context context) {
        rqsContext = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized  RequestQueueSingleton getInstance(Context context) {
        if(rqsInstance == null) {
            rqsInstance = new RequestQueueSingleton(context);
        }

        return rqsInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(rqsContext.getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
