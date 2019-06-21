package com.example.amst3_ta4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Redes_Sensores extends AppCompatActivity {
    private RequestQueue mQueue;
    private String token = "";
    private String mensaje;
    private String temperatura;
    private String acex;
    private String[] parts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes__sensores);
        mQueue = Volley.newRequestQueue(this);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
        revisarSensores();
    }

    private void revisarSensores(){
        final TextView
                tempValue = (TextView) findViewById(R.id.tempVal);
        final TextView acexValue = (TextView) findViewById(R.id.acex);
        String url_temp = "https://amstdb.herokuapp.com/db/logDos/5";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url_temp, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            mensaje=response.getString("value");
                            parts = mensaje.split(";");
                            temperatura=parts[0];
                            acex=parts[1];
                            System.out.println();
                            tempValue.setText(temperatura + " C");
                            acexValue.setText(acex);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String,
                    String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,
                        String>();
                params.put("Authorization", "JWT " + token);
                System.out.println(token);
                return params;
            }
        };;
        mQueue.add(request);
    }
}
