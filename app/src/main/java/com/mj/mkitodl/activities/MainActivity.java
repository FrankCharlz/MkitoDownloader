package com.mj.mkitodl.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mj.mkitodl.R;
import com.mj.mkitodl.adapters.HomeListAdapter;
import com.mj.mkitodl.fragments.TopTenFragment;
import com.mj.mkitodl.models.HomeResponse;
import com.mj.mkitodl.network.Network;
import com.mj.mkitodl.network.MkitoService;
import com.mj.mkitodl.utils.Constants;
import com.mj.mkitodl.utils.M;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MkitoService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        AccountHeader drawerHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.mk_header)
                .build();

        //items
        PrimaryDrawerItem item0 = new PrimaryDrawerItem().withName("Top 10");
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Playlist");

        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(drawerHeader)
                .build();

        drawer.addItem(item0);
        drawer.addItem(item1);

        drawer.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                loadFragment(position);
                return false;
            }
        });

        //show harmburger
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

        initView();

        OkHttpClient client = Network.INSTANCE.getClient();
        client.setCache(
                new Cache(
                        getApplicationContext().getCacheDir(),
                        Constants.CACHE_MAX_SIZE
                )
        );

        service = Network.INSTANCE.getRetrofit().create(MkitoService.class);
        service.getHome().enqueue(hoo);

    }

    private void loadFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_main, new TopTenFragment());
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
        mRecyclerView.setHasFixedSize(true);

        if (!getResources().getBoolean(R.bool.is_tablet)) {
            LinearLayoutManager lm = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(lm);
        } else {
            StaggeredGridLayoutManager slm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(slm);
        }

    }

    private HomeListAdapter adapter;
    private Callback<HomeResponse> hoo = new Callback<HomeResponse>() {
        @Override
        public void onResponse(Response<HomeResponse> response, Retrofit retrofit) {
            HomeResponse b = response.body();
            if (b.success == 1) {
                adapter = new HomeListAdapter(b.songs);
                M.log("songs idadi : "+ b.songs.size());
                mRecyclerView.setAdapter(adapter);
            } else {
                Snackbar
                        .make(findViewById(R.id.container_main), "Request failed, try again.", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        service.getHome().enqueue(hoo); // retry request
                                    }
                                })
                        .show();

            }

        }

        @Override
        public void onFailure(Throwable t) {
            Snackbar
                    .make(findViewById(R.id.container_main), "Request failed, try again.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    service.getHome().enqueue(hoo); // retry request
                                }
                            })
                    .show();
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
