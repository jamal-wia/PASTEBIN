package wia.soft.official.pastebin.ui.mainscreen.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.jamal.pastebin.R;

import java.util.List;

import wia.soft.official.pastebin.data.models.PasteRoom;
import wia.soft.official.pastebin.mvp.mainscreen.main.MainPresenter;
import wia.soft.official.pastebin.mvp.mainscreen.main.MainView;
import wia.soft.official.pastebin.ui.mainscreen.listpaste.saved.SavedPasteFragment;
import wia.soft.official.pastebin.ui.mainscreen.listpaste.trending.TrendingFragment;

import static wia.soft.official.pastebin.utils.RouterUtils.showFragment;

public class MainActivity extends AppCompatActivity implements MainView,
        TrendingFragment.UpdateSaveRecyclerViewListener {
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter();
        presenter.attachView(this);
        presenter.startView();
        initViews();
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void startFragment(Fragment fragment) {
        showFragment(R.id.FrameLayout_main_container, fragment, this);
    }

    @Override
    public void startView(Fragment fragment) {
        showFragment(R.id.FrameLayout_main_container, fragment,
                this);
    }

    @Override
    public void updateSaveRecyclerView(List<PasteRoom> allPaste) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof SavedPasteFragment) {
                ((SavedPasteFragment) fragment).updateRemoveRecycleView(allPaste);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initViews() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            presenter.onBottomNavigationSelectClick(item.getItemId());
            return true;
        });
    }
}