/*
 * Copyright By Noor Nabiul Alam Siddiqui  (c) 2017.
 *  www.fb.com/sazal.ns
 */

package com.rtsoftbd.siddiqui.engrhossain;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rtsoftbd.siddiqui.engrhossain.model.AboutSocial;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity ";

    private static final String TAG_ABOUT = "ABOUT";
    private static final String TAG_LATEST_UPDATE = "LATEST UPDATE";
    private static final String TAG_ACHIEVEMENT = "ACHIEVEMENT";
    private static final String TAG_RESUME = "RESUME";
    private static final String TAG_EDUCATION = "EDUCATION";
    private static final String TAG_GALLERY = "GALLERY";
    private static final String TAG_SOCIAL = "SOCIAL";
    private static final String TAG_REG = "REGISTRATION";
    private static final String TAG_LOGIN = "LOGIN";

    private static String TAG_CURRENT = TAG_ABOUT;

    @BindView(R.id.toolbar) Toolbar ms_Toolbar;
    @BindView(R.id.nav_view) NavigationView ms_NavView;
    @BindView(R.id.drawer_layout) DrawerLayout ms_DrawerLayout;

    private ActionBarDrawerToggle toggle;

    public static int navItemIndex = 0;
    private String[] activityTitles;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ms_Toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(ms_Toolbar);

        mHandler = new Handler();

        ms_DrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, ms_DrawerLayout, ms_Toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ms_DrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        activityTitles = getResources().getStringArray(R.array.nav_array);
        ms_NavView = (NavigationView) findViewById(R.id.nav_view);

        View header = ms_NavView.getHeaderView(0);
        CircleImageView pic = (CircleImageView) header.findViewById(R.id.imageView);
        pic.setImageBitmap(SplashActivity.getLogo());

        TextView textView = (TextView) header.findViewById(R.id.nameTextView);
        textView.setText(AboutSocial.getSectorAboutHeader());

        setNavigationClickHandler();

        if (savedInstanceState == null){
            navItemIndex =0;
            TAG_CURRENT = TAG_ABOUT;
            loadHomeFragment();
        }else {
            ms_NavView.getMenu().getItem(navItemIndex).setChecked(true);
            getSupportActionBar().setTitle(activityTitles[navItemIndex]);
        }

        if( getIntent().getBooleanExtra("Exit", false)){
            finish();
        }
    }

    private void setNavigationClickHandler() {
        ms_NavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_about:
                        navItemIndex = 0;
                        TAG_CURRENT = TAG_ABOUT;
                        break;
                    case R.id.nav_latest_update:
                        navItemIndex = 1;
                        TAG_CURRENT = TAG_LATEST_UPDATE;
                        break;
                    case R.id.nav_achievement:
                        navItemIndex = 2;
                        TAG_CURRENT = TAG_ACHIEVEMENT;
                        break;
                    case R.id.nav_resume:
                        navItemIndex = 3;
                        TAG_CURRENT = TAG_RESUME;
                        break;
                    case R.id.nav_education:
                        navItemIndex = 4;
                        TAG_CURRENT = TAG_EDUCATION;
                        break;
                    case R.id.nav_gallery:
                        navItemIndex = 5;
                        TAG_CURRENT = TAG_GALLERY;
                        break;
                    case R.id.nav_social:
                        navItemIndex = 6;
                        TAG_CURRENT = TAG_SOCIAL;
                        break;
                    case R.id.nav_reg:
                        navItemIndex = 7;
                        TAG_CURRENT = TAG_REG;
                        break;
                    case R.id.nav_login:
                        navItemIndex = 8;
                        TAG_CURRENT = TAG_LOGIN;
                        break;
                    default:
                        navItemIndex = 0;
                }

                loadHomeFragment();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, ms_DrawerLayout, ms_Toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        ms_DrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void loadHomeFragment() {

        ms_NavView.getMenu().getItem(navItemIndex).setChecked(true);
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.frame,fragment,TAG_CURRENT);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        mHandler.post(runnable);

        ms_DrawerLayout.closeDrawers();

        invalidateOptionsMenu();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title",activityTitles[navItemIndex]);
    }

    @Override
    public void onBackPressed() {
        if (ms_DrawerLayout.isDrawerOpen(GravityCompat.START)) {
            ms_DrawerLayout.closeDrawers();
            return;
        }

        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                TAG_CURRENT = TAG_ABOUT;
                loadHomeFragment();
                return;
            }


            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getResources().getString(R.string.exit))
                    .setMessage(getResources().getString(R.string.sure))
                    .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Exit", true);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.no), null)
                    .show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.removeItem(R.id.action_sms);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
              new MaterialDialog.Builder(this)
                        .customView(R.layout.about_us, true)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private Fragment getHomeFragment() {
        switch (navItemIndex){
            case 1:
                return new LatestUpdateFragment();
            case 2:
                return new AchivementFragment();
            case 3:
                return new ResumeFragment();
            case 4:
                return new EducationFragment();
            case 5:
                return new GalleryFragment();
            case 6:
                return new SocialFragment();
            case 7:
                return new RegFragment();
            case 8:
                return new LoginFragment();
            default:
                return new AboutFragment();
        }
    }
}
