package androidtest.com.androidtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import androidtest.com.androidtest.Model.SongResponse;
import androidtest.com.androidtest.Presenter.SongPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    private String searchSong;

    @Before
    public void init() {
        searchSong = "Michael Jakson";
    }


    @Test
    public void login_Success() {
        new SongPresenter().getSongs(searchSong, callback);

    }



    Callback<SongResponse> callback = new Callback<SongResponse>() {

        @Override
        public void onResponse(Call<SongResponse> call, Response<SongResponse> response) {
            SongResponse result = response.body();

            if (result != null)
                assertTrue(response.isSuccessful() && (result.getResults().get(0).getArtistName().contains(searchSong) || result.getResults().get(0).getTrackName().contains(searchSong)));
        }

        @Override
        public void onFailure(Call<SongResponse> call, Throwable t) {
            try {
                //assertTrue(response.isSuccessful() && (result.getResults().get(0).getArtistName().contains(searchSong) ||result.getResults().get(0).getTrackName().contains(searchSong)) );
                throw new InterruptedException("Error in the API!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @After
   public void tearDown(){
       searchSong =  null;
   }
}