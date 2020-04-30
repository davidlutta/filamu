package com.davidlutta.filamu.UI.series.popularSeries.datasource;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class SeriesDataSourceFactory extends DataSource.Factory {
    public static final String TAG = "SeriesDataSourceFactory";
    SeriesDataSource seriesDataSource;
    MutableLiveData<SeriesDataSource> mutableLiveData;

    public SeriesDataSourceFactory() {
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        seriesDataSource = new SeriesDataSource();
        Log.d(TAG, "create: " + seriesDataSource);
        mutableLiveData.postValue(seriesDataSource);
        return seriesDataSource;
    }

    public MutableLiveData<SeriesDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
