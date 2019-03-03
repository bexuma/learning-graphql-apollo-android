package co.youngdeveloper.learninggraphql;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.exception.ApolloException;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private TextView title1, title2, description1, description2;
    private String t1, t2, d1, d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        description1 = findViewById(R.id.description1);
        description2 = findViewById(R.id.description2);

        getPosts();


//        String url = "https://graphql-pokemon.now.sh/?query=%7B%0A%20%20pokemon(name%3A%20%22Pikachu%22)%20%7B%0A%20%20%20%20name%0A%20%20%20%20attacks%20%7B%0A%20%20%20%20%20%20special%20%7B%0A%20%20%20%20%20%20%20%20name%0A%20%20%20%20%20%20%20%20type%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D";
//
//        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONObject data = response.getJSONObject("data");
//                    JSONObject pokemon = data.getJSONObject("pokemon");
//                    String name = pokemon.getString("name");
//
//                    Log.v("JSON", "HEEEEY!" + name);
//
//                } catch (JSONException e) {
//                    Log.v("JSON", "EXC! " + e.getLocalizedMessage());
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.v("ERROR", "Error: " + error.getLocalizedMessage());
//            }
//        });
//
//        Volley.newRequestQueue(this).add(objectRequest);
    }

    private void getPosts() {
        MyApolloClient.getMyApolloClient().query(AllPostsQuery.builder().build()).enqueue(new ApolloCall.Callback<AllPostsQuery.Data>() {
            @Override
            public void onResponse(@Nonnull com.apollographql.apollo.api.Response<AllPostsQuery.Data> response) {
                Log.d(TAG, "onResponse: " + response.data().allPosts().get(0).title());
                t1 = response.data().allPosts().get(0).title();
                t2 = response.data().allPosts().get(0).description();
                d1 = response.data().allPosts().get(1).title();
                d2 = response.data().allPosts().get(1).description();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        title1.setText(t1);
                        title2.setText(t2);
                        description1.setText(d1);
                        description2.setText(d2);
                    }
                });
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                e.getLocalizedMessage();
            }
        });
    }























}
