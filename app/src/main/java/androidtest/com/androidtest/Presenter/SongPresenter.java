package androidtest.com.androidtest.Presenter;

import android.content.Context;
import android.text.TextUtils;


import androidtest.com.androidtest.Model.SongResponse;
import androidtest.com.androidtest.R;
import androidtest.com.androidtest.Service.SongService;
import androidtest.com.androidtest.interfaces.SongPresenterListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongPresenter {
    private Context context;
    private SongPresenterListener mListener;
    private SongService songService;

    public SongPresenter(SongPresenterListener listener, Context context) {
        this.mListener = listener;
        this.context = context;
        this.songService = new SongService();
    }

    //It will require for junit testing
    public SongPresenter() {
        this.songService = new SongService();
    }


    public void searchSong(String searchData) {
        if (TextUtils.isEmpty(searchData))
            mListener.songErrorSenario(context.getString(R.string.error_search_query));
         else
            getSongs(searchData, callback);
    }

    public void getSongs(String searchData, Callback<SongResponse> songResponseCallback) {

        songService
                .getAPI()
                .getResults(searchData)
                .enqueue(songResponseCallback);
    }

    Callback<SongResponse> callback = new Callback<SongResponse>() {

        @Override
        public void onResponse(Call<SongResponse> call, Response<SongResponse> response) {
            SongResponse result = response.body();

            if (result != null && result.getResultCount()!=0){

                mListener.songsReady(result.getResults());
            }
            else
                mListener.songErrorSenario(context.getString(R.string.error_search_query));
        }

        @Override
        public void onFailure(Call<SongResponse> call, Throwable t) {
            try {
                mListener.songErrorSenario(context.getString(R.string.common_error));
                throw new InterruptedException("Error in song API!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
