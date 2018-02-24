package androidtest.com.androidtest.interfaces;

import java.util.List;

import androidtest.com.androidtest.Model.SongResponse;



public interface SongPresenterListener {
    void songsReady(List<SongResponse.SongModel> countries);

    void songErrorSenario(String msg);
}
