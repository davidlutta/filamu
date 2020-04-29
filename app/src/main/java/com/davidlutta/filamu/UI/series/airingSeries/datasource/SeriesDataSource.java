package com.davidlutta.filamu.UI.series.airingSeries.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.davidlutta.filamu.api.RetrofitService;
import com.davidlutta.filamu.api.TvSeriesApi;
import com.davidlutta.filamu.models.series.Series;
import com.davidlutta.filamu.models.series.SeriesResponse;
import com.davidlutta.filamu.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesDataSource extends PageKeyedDataSource<Integer, Series> {
    public static final String TAG = "SeriesDataSource";
    private TvSeriesApi seriesApi;
    private String mAPI_KEY;

    public SeriesDataSource() {
        seriesApi = RetrofitService.getTvSeriesApi();
        mAPI_KEY = Constants.API_KEY;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Series> callback) {
        final int page = 1;
        seriesApi.getSeriesAiringToday(mAPI_KEY, String.valueOf(page)).enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Series> seriesList = response.body().getSeries();
                        callback.onResult(seriesList, null, page + 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {
                Log.d(TAG, "LoadInitial: onFailure: FAILED TO RETRIEVE SERIES AIRING TODAY");

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Series> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Series> callback) {
        final int page = params.key;
        seriesApi.getSeriesAiringToday(mAPI_KEY, String.valueOf(page)).enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Series> seriesList = response.body().getSeries();
                        callback.onResult(seriesList, page + 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {
                Log.d(TAG, "LoadAfter: onFailure: FAILED TO RETRIEVE SERIES AIRING TODAY");
            }
        });
    }
}
