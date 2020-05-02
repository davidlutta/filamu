package com.davidlutta.filamu.UI.series.similarSeries.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.davidlutta.filamu.UI.series.similarSeries.datasource.SeriesDataSource;
import com.davidlutta.filamu.UI.series.similarSeries.datasource.SeriesDataSourceFactory;
import com.davidlutta.filamu.models.series.Series;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SimilarSeriesViewModel extends AndroidViewModel {
    private SeriesDataSourceFactory seriesDataSourceFactory;
    private MutableLiveData<SeriesDataSource> dataSourceMutableLiveData;
    private LiveData<PagedList<Series>> pagedListLiveData;
    private Executor executor;

    public SimilarSeriesViewModel(@NonNull Application application, String tvId) {
        super(application);
        seriesDataSourceFactory = new SeriesDataSourceFactory(tvId);
        dataSourceMutableLiveData = seriesDataSourceFactory.getMutableLiveData();
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(5);
        pagedListLiveData = new LivePagedListBuilder<Integer, Series>(seriesDataSourceFactory, config)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Series>> getPagedListLiveData() {
        if (pagedListLiveData != null) {
            return pagedListLiveData;
        }else
            return null;
    }
}
