package com.davidlutta.filamu.repository.series;

import android.text.PrecomputedText;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.api.TvSeriesApi;
import com.davidlutta.filamu.models.cast.Cast;
import com.davidlutta.filamu.models.cast.CastResponse;
import com.davidlutta.filamu.models.cast.Crew;
import com.davidlutta.filamu.models.series.Series;
import com.davidlutta.filamu.models.series.SeriesResponse;
import com.davidlutta.filamu.models.show.Show;
import com.davidlutta.filamu.models.trailers.Trailer;
import com.davidlutta.filamu.models.trailers.TrailerResponse;
import com.davidlutta.filamu.util.Constants;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesRepository {
    private static SeriesRepository instance;
    private TvSeriesApi seriesApi;
    public static final String TAG = "SeriesRepository";
    private String mAPI_KEY;

    private SeriesRepository() {
        mAPI_KEY = Constants.API_KEY;
        seriesApi = RetrofitService.getTvSeriesApi();
    }

    public static SeriesRepository getInstance() {
        if (instance == null) {
            instance = new SeriesRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Series>> getSeriesAiringToday() {
        final MutableLiveData<List<Series>> seriesData = new MutableLiveData<>();
        seriesApi.getSeriesAiringToday(mAPI_KEY, "1").enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Series> series = response.body().getSeries();
                        Collections.sort(series, Series.BY_RATING);
                        Collections.reverse(series);
                        seriesData.postValue(series);
                    }
                }
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {
                Log.d(TAG, "GetSeriesAiringToday: onFailure: FAILED TO FETCH SERIES ON AIR FROM API");
            }
        });
        return seriesData;
    }

    public MutableLiveData<List<Series>> getSeriesOnAir() {
        final MutableLiveData<List<Series>> seriesData = new MutableLiveData<>();
        seriesApi.getSeriesOnAir(mAPI_KEY, "1").enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Series> series = response.body().getSeries();
                        Collections.sort(series, Series.BY_RATING);
                        Collections.reverse(series);
                        seriesData.postValue(series);
                    }
                }
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {
                Log.d(TAG, "GetSeriesOnAir: onFailure: FAILED TO RETRIEVE SERIES ON AIR FROM API");
            }
        });
        return seriesData;
    }

    public MutableLiveData<List<Series>> getPopularSeries() {
        final MutableLiveData<List<Series>> seriesData = new MutableLiveData<>();
        seriesApi.getPopularSeries(mAPI_KEY, "1").enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Series> series = response.body().getSeries();
                        Collections.sort(series, Series.BY_RATING);
                        Collections.reverse(series);
                        seriesData.postValue(series);
                    }
                }
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {
                Log.d(TAG, "GetPopularSeries: onFailure: FAILED TO RETRIEVE POPULAR SERIES FROM THE API");
            }
        });
        return seriesData;
    }

    public MutableLiveData<Show> getSeries(String tvId) {
        final MutableLiveData<Show> seriesData = new MutableLiveData<>();
        seriesApi.getSeries(tvId, mAPI_KEY).enqueue(new Callback<Show>() {
            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        seriesData.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {
                Log.d(TAG, "GetSeries: onFailure: FAILED TO RETRIEVE SERIES");
            }
        });
        return seriesData;
    }

    public MutableLiveData<List<Cast>> getSeriesCast(String tvID) {
        final MutableLiveData<List<Cast>> castData = new MutableLiveData<>();
        seriesApi.getCredits(tvID, mAPI_KEY).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Cast> castList = response.body().getCast();
                        castData.postValue(castList);
                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                Log.d(TAG, "GetSeriesCast: onFailure: FAILED TO RETRIEVE SERIES CAST");
            }
        });
        return castData;
    }

    public MutableLiveData<List<Crew>> getSeriesCrew(String tvId){
        final MutableLiveData<List<Crew>> crewData = new MutableLiveData<>();
        seriesApi.getCredits(tvId, mAPI_KEY).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Crew> crew = response.body().getCrew();
                        crewData.postValue(crew);
                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                Log.d(TAG, "GetSeriesCast: onFailure: FAILED TO RETRIEVE SERIES CREW");
            }
        });
        return crewData;
    }

    public MutableLiveData<List<Trailer>> getSeriesTrailer(String tvId) {
        final MutableLiveData<List<Trailer>> trailerData = new MutableLiveData<>();
        seriesApi.getTrailer(tvId, mAPI_KEY).enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Trailer> trailers = response.body().getTrailers();
                        trailerData.postValue(trailers);
                    }
                }
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                Log.d(TAG, "GetSeriesTrailers: onFailure: FAILED TO RETRIEVE SERIES TRAILERS");
            }
        });
        return trailerData;
    }

    public MutableLiveData<List<Series>> getSimilarShows(String tvID) {
        final MutableLiveData<List<Series>> seriesData = new MutableLiveData<>();
        seriesApi.getSimilarShows(tvID, mAPI_KEY, "1").enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Series> series = response.body().getSeries();
                        Collections.sort(series, Series.BY_RATING);
                        Collections.reverse(series);
                        seriesData.postValue(series);
                    }
                }
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {
                Log.d(TAG, "GetSimilarShows: onFailure: FAILED TO RETRIEVE SIMILAR TV SHOWS");
            }
        });
        return seriesData;
    }
}
