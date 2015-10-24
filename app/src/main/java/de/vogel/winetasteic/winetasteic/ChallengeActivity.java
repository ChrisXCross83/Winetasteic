package de.vogel.winetasteic.winetasteic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class ChallengeActivity extends AppCompatActivity implements ChallengeFragment.OnFragmentInteractionListener, ChallengeSuccessFragment.OnFragmentInteractionListener,SelfieFragment.OnFragmentInteractionListener{


    int id;
    int index=0;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        id = getIntent().getIntExtra("index",0);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }
    @Override
    public void onBackPressed() {
         super.onBackPressed();
    }

    @Override
    public void switchPage() {

        if(index < 4){
            index++;
            mPager.setCurrentItem(index);
        }

    }

    @Override
    public void finishChallenge() {
        Intent intent = new Intent(this,TourActivity.class);
        intent.putExtra("index",id);
        startActivity(intent);
    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return de.vogel.winetasteic.winetasteic.ChallengeFragment.newInstance(id, "kkk");
            }else if(position == 1){
                return ChallengeSuccessFragment.newInstance(id,"1");
            }else if(position == 2){
                return SelfieFragment.newInstance(id,"lkskd");
            }else{
                return ChallengeSuccessFragment.newInstance(id,"2");
            }


        }

        @Override
        public int getCount() {
            return 4;
        }
    }




}
