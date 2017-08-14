package com.willthishappen.infuture.presentation.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.willthishappen.infuture.R;
import com.willthishappen.infuture.domain.UserBean;
import com.willthishappen.infuture.presentation.presenter.MainPresenter;
import com.willthishappen.infuture.presentation.ui.auth.LoginActivity;
import com.willthishappen.infuture.presentation.ui.prediction.list.PredictListFragment;
import com.willthishappen.infuture.util.SessionHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity
        implements IMainView, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Inject
    MainPresenter presenter;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        presenter.onViewCreated();
    }

    @Override
    public void initMainView() {
        navigationView.setCheckedItem(R.id.nav_predict_list);
        navigateFragment(FragmentFactory.getPredictListFragment());

        UserBean user = SessionHelper.getCurrentUser();

        View headerView = navigationView.getHeaderView(0);
        TextView txtUserName = (TextView) headerView.findViewById(R.id.txtUserName);
        TextView txtUserEmail = (TextView) headerView.findViewById(R.id.txtUserEmail);
        ImageView imgUserAvatar = (ImageView) headerView.findViewById(R.id.imgUserAvatar);

        txtUserName.setText(user.getName());
        txtUserEmail.setText(user.getEmail());
        if (user.getPhotoUrl() != null) {
            Glide.with(this).load(user.getPhotoUrl())
                    .placeholder(R.drawable.ic_account_circle_80dp)
                    .bitmapTransform(new CropCircleTransformation(this))
                    .into(imgUserAvatar);
        }
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_predict_list) {
            navigateFragment(FragmentFactory.getPredictListFragment());
        } else if (id == R.id.nav_log_out) {
            // todo add confirm dialog
            presenter.onLogoutClick();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigateFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.contentMain, fragment).commit();
    }

    private void showUserInDrawer() {

    }

    private static class FragmentFactory {
        @NonNull
        static Fragment getPredictListFragment() {
            return new PredictListFragment();
        }
    }
}
