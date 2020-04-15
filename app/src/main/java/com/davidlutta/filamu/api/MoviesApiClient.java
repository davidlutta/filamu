package com.davidlutta.filamu.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.davidlutta.filamu.models.MovieResponse;
import com.davidlutta.filamu.models.Movies;
import com.davidlutta.filamu.util.AppExecutors;
import com.davidlutta.filamu.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MoviesApiClient {
    private static MoviesApiClient instance;
    String TAG = "MoviesApiClient";
    private MutableLiveData<List<Movies>> mMovies;
    private RetrieveMoviesRunnable mRetrieveMoviesRunnable;
    private MutableLiveData<Boolean> mMoviesRequestTimeout = new MutableLiveData<>();

    private MoviesApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public static MoviesApiClient getInstance() {
        if (instance == null) {
            instance = new MoviesApiClient();
        }
        return instance;
    }

    public LiveData<List<Movies>> getMovies() {
        return mMovies;
    }

    public LiveData<Boolean> isMoviesRequestTimedOut() {
        return mMoviesRequestTimeout;
    }

    public void getMoviesApi(int pageNumber) {
        if (mRetrieveMoviesRunnable != null) {
            mRetrieveMoviesRunnable = null;
        }
        mRetrieveMoviesRunnable = new RetrieveMoviesRunnable(pageNumber);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveMoviesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveMoviesRunnable implements Runnable {
        boolean cancelRequest;
        private int pageNumber;

        public RetrieveMoviesRunnable(int pageNumber) {
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getMovies(pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    assert response.body() != null;
                    List<Movies> list = new ArrayList<>(((MovieResponse) response.body()).getMovies());
                    if (pageNumber == 1) {
                        mMovies.postValue(list);
                    } else {
                        List<Movies> currentMoviesList = mMovies.getValue();
                        assert currentMoviesList != null;
                        currentMoviesList.addAll(list);
                        mMovies.postValue(currentMoviesList);
                    }
                } else {
                    assert response.errorBody() != null;
                    String error = response.errorBody().string();
                    Log.d(TAG, "run: " + error);
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<MovieResponse> getMovies(int pageNumber) {
            return RetrofitService.getMoviesApi().getNowPlayingMovies(Constants.API_KEY, String.valueOf(pageNumber));
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: Cancelling Request");
            cancelRequest = true;
        }
    }
}
