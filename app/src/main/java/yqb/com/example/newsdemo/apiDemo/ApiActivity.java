package yqb.com.example.newsdemo.apiDemo;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import yqb.com.example.newsdemo.R;

public class ApiActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    public static final String NEWS_TYPE_TOP = "top";
    public static final String NEWS_TYPE_SHEHUI = "shehui";
    public static final String NEWS_TYPE_YULE = "yule";
    public static final String NEWS_TYPE_TIYU = "tiyu";
    public static final String NEWS_TYPE_JUNSHI = "junshi";
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        mTablayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        init();
    }

    private void init() {
        fragments.add(NewsFragment.newInstance(NEWS_TYPE_TOP));
        fragments.add(NewsFragment.newInstance(NEWS_TYPE_SHEHUI));
        fragments.add(NewsFragment.newInstance(NEWS_TYPE_YULE));
        fragments.add(NewsFragment.newInstance(NEWS_TYPE_TIYU));
        fragments.add(NewsFragment.newInstance(NEWS_TYPE_JUNSHI));
        titles.add(getResources().getString(R.string.top));
        titles.add(getResources().getString(R.string.shehui));
        titles.add(getResources().getString(R.string.yule));
        titles.add(getResources().getString(R.string.tiyu));
        titles.add(getResources().getString(R.string.junshi));

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        mTablayout.setupWithViewPager(mViewPager);
    }

}
