package com.example.chriechic.order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.chriechic.database.DatabaseClient;
import com.example.chriechic.database.DatabaseModel;
import com.example.chriechic.database.dao.DatabaseDao;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrderViewModel extends AndroidViewModel {

    LiveData<List<DatabaseModel>> modelDatabase;
    DatabaseDao databaseDao;

    //untuk inisialisasi databaseDao
    public OrderViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    //untuk mengambil data dari database
    public LiveData<List<DatabaseModel>> getDataIdUser() {
        modelDatabase = databaseDao.getAllOrder();
        return modelDatabase;
    }

    //untuk update data berdasarkan Id secara realtime
    public void addDataOrder(String menuName, int items, int totalPrice) {
        Completable.fromAction(() -> {
                    DatabaseModel databaseModel = new DatabaseModel();
                    databaseModel.nama_menu = menuName;
                    databaseModel.items = items;
                    databaseModel.harga = totalPrice;
                    databaseDao.insertData(databaseModel);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
