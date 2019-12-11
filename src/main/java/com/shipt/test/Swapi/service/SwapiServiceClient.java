package com.shipt.test.Swapi.service;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

@Service
public class SwapiServiceClient {

    private SwapiService service;

    public SwapiServiceClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

         service = retrofit.create(SwapiService.class);
    }

    public String getPeopleDetails() throws IOException {
        Call<Persons> callSync = service.getPeople();
        Response<Persons> response = callSync.execute();
        List<People> apiResponse = response.body().getResults();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i< apiResponse.size();i++){
            sb.append("People Name: "+apiResponse.get(i).getName()+"\n");
            Call<Planet> pcallSync = service.getPlanets(apiResponse.get(i).getHomeworld());
            try {
                Response<Planet> pResponse = pcallSync.execute();
                sb.append("Planet Name: "+pResponse.body().getName()+"\n");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            for (String ship : apiResponse.get(i).getStarships()) {
                Call<StarShip> scallSync = service.getStarShips(ship);
                try {
                    Response<StarShip> sResponse = scallSync.execute();
                    sb.append("Starship Name: "+sResponse.body().getName()+"\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            sb.append("-----------------------------------------------------------------------------"+"\n\n");

        }

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return sb.toString();
    }

}
