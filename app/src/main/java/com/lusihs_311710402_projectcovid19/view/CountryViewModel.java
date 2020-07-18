package com.lusihs_311710402_projectcovid19.view;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lusihs_311710402_projectcovid19.api.ApiEndPoint;
import com.lusihs_311710402_projectcovid19.api.ApiService;
import com.lusihs_311710402_projectcovid19.model.CountryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class CountryViewModel extends AndroidViewModel {

    private MutableLiveData<CountryModel> mutableLiveData = new MutableLiveData<>();

    public CountryViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCountryData() {
        Retrofit retrofit = ApiService.getRetrofitService();
        ApiEndPoint apiEndpoint = retrofit.create(ApiEndPoint.class);
        Call<CountryModel> call = apiEndpoint.getSummaryIdn();
        call.enqueue(new Callback<CountryModel>() {
            @Override
            public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CountryModel> call, Throwable t) {

            }
        });
    }

    public LiveData<CountryModel> getCountryData() {
        return mutableLiveData;
    }
}

