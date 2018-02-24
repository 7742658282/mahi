package androidtest.com.androidtest.Service;


import androidtest.com.androidtest.BuildConfig;
import androidtest.com.androidtest.interfaces.SongAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SongService {
    public SongAPI getAPI() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SongAPI.class);
    }
}
