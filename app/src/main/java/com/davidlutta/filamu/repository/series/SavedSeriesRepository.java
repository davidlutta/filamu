package com.davidlutta.filamu.repository.series;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.davidlutta.filamu.database.AppDatabase;
import com.davidlutta.filamu.database.tv.Series;
import com.davidlutta.filamu.database.tv.SeriesDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SavedSeriesRepository {
    private SeriesDao seriesDao;
    private LiveData<List<Series>> seriesList;

    public SavedSeriesRepository(Application application) {
        seriesDao = AppDatabase.getInstance(application).seriesDao();
        seriesList = seriesDao.loadAllSavedSeries();
    }

    public LiveData<List<Series>> getSavedSeries() {
        return seriesList;
    }

    public Series getSeries(int tvId) throws ExecutionException, InterruptedException {
        return new GetSeriesByIdAsyncTask(seriesDao).execute(tvId).get();
    }

    public void saveSeries(Series series) {
        new InsertSeriesAsyncTask(seriesDao).execute(series);
    }

    public void deleteSeries(int tvId) {
        new DeleteSeriesAsyncTask(seriesDao).execute(tvId);
    }

    private static class InsertSeriesAsyncTask extends AsyncTask<Series, Void, Void> {
        private SeriesDao seriesDao;

        public InsertSeriesAsyncTask(SeriesDao seriesDao) {
            this.seriesDao = seriesDao;
        }

        @Override
        protected Void doInBackground(Series... series) {
            seriesDao.insertSeries(series[0]);
            return null;
        }
    }

    private static class DeleteSeriesAsyncTask extends AsyncTask<Integer, Void, Void> {
        private SeriesDao seriesDao;

        public DeleteSeriesAsyncTask(SeriesDao seriesDao) {
            this.seriesDao = seriesDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            seriesDao.deleteMovieById(integers[0]);
            return null;
        }
    }

    private static class GetSeriesByIdAsyncTask extends AsyncTask<Integer, Void, Series> {
        private SeriesDao seriesDao;

        public GetSeriesByIdAsyncTask(SeriesDao seriesDao) {
            this.seriesDao = seriesDao;
        }

        @Override
        protected Series doInBackground(Integer... integers) {
            return seriesDao.loadSavedSeries(integers[0]);
        }
    }

}


