package co.youngdeveloper.learninggraphql;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by youngdeveloper on 01/02/2018.
 */

public class MyApolloClient {
    private static final String BASE_URL = "https://api.graph.cool/simple/v1/cjd2s0bub97io0123o8twdrjt";
    private static ApolloClient myApolloClient;

    public static ApolloClient getMyApolloClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        myApolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();

        return myApolloClient;

    }


}
