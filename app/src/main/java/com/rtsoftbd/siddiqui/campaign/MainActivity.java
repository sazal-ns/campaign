package com.rtsoftbd.siddiqui.campaign;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar) Toolbar ms_Toolbar;
    @BindView(R.id.frame) FrameLayout ms_Frame;
    @BindView(R.id.nav_view) NavigationView ms_NavView;
    @BindView(R.id.drawer_layout) DrawerLayout ms_DrawerLayout;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ms_Toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(ms_Toolbar);


        ms_DrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, ms_DrawerLayout, ms_Toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ms_DrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        ms_NavView = (NavigationView) findViewById(R.id.nav_view);
    }

    @Override
    public void onBackPressed() {
        ms_DrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (ms_DrawerLayout.isDrawerOpen(GravityCompat.START)) {
            ms_DrawerLayout.closeDrawer(GravityCompat.START);
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
