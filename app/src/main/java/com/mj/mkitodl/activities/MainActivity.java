package com.mj.mkitodl.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mj.mkitodl.R;
import com.mj.mkitodl.adapters.HomeListAdapter;
import com.mj.mkitodl.models.HomeResponse;
import com.mj.mkitodl.models.Song;
import com.mj.mkitodl.network.MkitoService;
import com.mj.mkitodl.utils.Constants;
import com.mj.mkitodl.utils.M;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        initView();

        Gson gson = new GsonBuilder().create();
        OkHttpClient client = HttpClient.INSTANCE.getClient();
        client.setCache(
                new Cache(
                        getApplicationContext().getCacheDir(),
                        Constants.CACHE_MAX_SIZE
                )
        );


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MkitoService service = retrofit.create(MkitoService.class);
        service.getHome().enqueue(hoo);

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);
    }


    private HomeListAdapter adapter;
    private Callback<HomeResponse> hoo = new Callback<HomeResponse>() {
        @Override
        public void onResponse(Response<HomeResponse> response, Retrofit retrofit) {
            HomeResponse b = response.body();
            for ( Song s : b.songs){
                M.log(s.toString());
            }

            adapter = new HomeListAdapter(b.songs);
            mRecyclerView.setAdapter(adapter);
        }

        @Override
        public void onFailure(Throwable t) {
            M.log(t.getLocalizedMessage());
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
