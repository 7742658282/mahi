package androidtest.com.androidtest.interfaces;

import androidtest.com.androidtest.BuildConfig;
import androidtest.com.androidtest.Model.SongResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface SongAPI {

    @GET(BuildConfig.SEARCH_API)
    Call<SongResponse> getResults(@Query("term") String searchSong);
}
