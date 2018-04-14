package com.example.ckpenep.testtaskagileengine.ui.activities.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.testtaskagileengine.App;
import com.example.ckpenep.testtaskagileengine.R;
import com.example.ckpenep.testtaskagileengine.Screens;
import com.example.ckpenep.testtaskagileengine.ui.common.RouterProvider;
import com.example.ckpenep.testtaskagileengine.ui.fragments.home.HomeFragment;
import com.example.ckpenep.testtaskagileengine.ui.fragments.logout.LogOutFragment;
import com.example.ckpenep.testtaskagileengine.ui.fragments.question.QuestionsListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

/**
 * Created by ckpenep on 23.03.2018.
 */

public class MainActivity extends MvpAppCompatActivity implements MainView, RouterProvider {

    @Inject
    Router router;
    @Inject
    NavigatorHolder navigatorHolder;

    @InjectPresenter
    MainPresenter presenter;
    @ProvidePresenter
    public MainPresenter createMainPresenter() {
        return new MainPresenter(router);
    }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawerLayoout)
    DrawerLayout mDrawerLayout;

    private HomeFragment mHomeFragment;
    private QuestionsListFragment mQuestionsListFragment;
    private LogOutFragment mLogOutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initContainers();
        initToolBar();
        initNavigationView();

        if (savedInstanceState == null) {
            presenter.onTabListClick();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    private void initToolBar() {
        this.setSupportActionBar(mToolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initNavigationView() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.actionHomeItem:
                        presenter.onTabHomeClick();
                        break;
                    case R.id.actionListItem:
                        presenter.onTabListClick();
                        break;
                    case R.id.actionLogoutItem:
                        presenter.onTabLogoutClick();
                        break;
                }

                return true;
            }
        });
    }

    private void initContainers() {
        FragmentManager fm = getSupportFragmentManager();

        mHomeFragment = (HomeFragment) fm.findFragmentByTag("HOME");
        if (mHomeFragment == null) {
            mHomeFragment = HomeFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.frame_layout, mHomeFragment, "HOME")
                    .detach(mHomeFragment).commitNow();
        }

        mQuestionsListFragment = (QuestionsListFragment) fm.findFragmentByTag("QUESTIONS");
        if (mQuestionsListFragment == null) {
            mQuestionsListFragment = QuestionsListFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.frame_layout, mQuestionsListFragment, "QUESTIONS")
                    .detach(mQuestionsListFragment).commitNow();
        }

        mLogOutFragment = (LogOutFragment) fm.findFragmentByTag("LOGOUT");
        if (mLogOutFragment == null) {
            mLogOutFragment = LogOutFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.frame_layout, mLogOutFragment, "LOGOUT")
                    .detach(mLogOutFragment).commitNow();
        }
    }


    @Override
    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private Navigator navigator = new Navigator() {

        @Override
        public void applyCommands(Command[] commands) {
            for (Command command : commands) applyCommand(command);
        }

        public void applyCommand(Command command) {
            if (command instanceof Back) {
                finish();
            } else if (command instanceof Replace) {
                FragmentManager fm = getSupportFragmentManager();

                switch (((Replace) command).getScreenKey()) {
                    case Screens.HOME_SCREEN:
                        fm.beginTransaction()
                                .detach(mQuestionsListFragment)
                                .detach(mLogOutFragment)
                                .attach(mHomeFragment)
                                .commitNow();
                        break;
                    case Screens.QUESTIONS_SCREEN:
                        fm.beginTransaction()
                                .detach(mHomeFragment)
                                .detach(mLogOutFragment)
                                .attach(mQuestionsListFragment)
                                .commitNow();
                        break;
                    case Screens.LOGOUT_SCREEN:
                        fm.beginTransaction()
                                .detach(mHomeFragment)
                                .detach(mQuestionsListFragment)
                                .attach(mLogOutFragment)
                                .commitNow();
                        break;
                }
            }
        }
    };

    @Override
    public Router getRouter() {
        return router;
    }
}
