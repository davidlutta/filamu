package com.davidlutta.filamu.UI.series.similarSeries.datasource;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class SeriesDataSourceFactory extends DataSource.Factory {
    public static final String TAG = "SeriesDataSourceFactory";
    SeriesDataSource seriesDataSource;
    MutableLiveData<SeriesDataSource> mutableLiveData;
    private String tvId;

    public SeriesDataSourceFactory(String tvId) {
        this.tvId = tvId;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        seriesDataSource = new SeriesDataSource(tvId);
        Log.d(TAG, "create: " + seriesDataSource);
        mutableLiveData.postValue(seriesDataSource);
        return seriesDataSource;
    }

    public MutableLiveData<SeriesDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
