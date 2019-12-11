package com.shipt.test.Swapi.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

import java.util.List;

public interface SwapiService {

    @GET("/api/people")
    public Call<Persons> getPeople();

    @GET
    public Call<Planet> getPlanets(@Url String url);

    @GET
    public Call<StarShip> getStarShips(@Url String url);
}
