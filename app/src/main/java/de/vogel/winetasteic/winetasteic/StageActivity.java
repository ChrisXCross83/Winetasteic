package de.vogel.winetasteic.winetasteic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.vogel.winetasteic.winetasteic.models.Challenge;
import de.vogel.winetasteic.winetasteic.models.Pub;
import de.vogel.winetasteic.winetasteic.models.Stage;

public class StageActivity extends AppCompatActivity {

    int id;
    TextView stageTitle;
    TextView stageSubtitle;
    ImageView stageImage;
    Button buttonPub;
    Button buttonChallenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        id = getIntent().getIntExtra("index",0);

        stageTitle = (TextView)findViewById(R.id.challengTitle);
        stageSubtitle = (TextView)findViewById(R.id.challengeSubtitle);
        stageImage = (ImageView)findViewById(R.id.challengeImage);

        buttonPub = (Button)findViewById(R.id.btn_pup);
        buttonPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StageActivity.this,PubActivity.class);
                intent.putExtra("index",id);
                startActivity(intent);
            }
        });
        buttonChallenge = (Button)findViewById(R.id.btn_challenge);
        buttonChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StageActivity.this,ChallengeActivity.class);
                intent.putExtra("index",id);
                startActivity(intent);
                //START Challenge
            }
        });


        init();


    }

    private void init(){
        switch (id){
            case 0:
                stageTitle.setText("HERZLICH WILLKOMMEN");
                stageSubtitle.setText("IN DER VINOTHEK\n" +
                        "Till Eulenspiegel");
                stageImage.setImageDrawable(getResources().getDrawable(R.drawable.staatl_hofkeller_img));
                break;
            case 1:
                stageTitle.setText("HERZLICH WILLKOMMEN");
                stageSubtitle.setText("IN DER VINOTHEK\n" +
                        "Maulaffenbäck");
                stageImage.setImageDrawable(getResources().getDrawable(R.drawable.staatl_hofkeller_img));
                break;
            case 2:
                stageTitle.setText("HERZLICH WILLKOMMEN");
                stageSubtitle.setText("IN DER VINOTHEK\n" +
                        "Staatlicher Hofkeller");
                stageImage.setImageDrawable(getResources().getDrawable(R.drawable.staatl_hofkeller_img));
                break;
            case 3:
                stageTitle.setText("HERZLICH WILLKOMMEN");
                stageSubtitle.setText("IN DER VINOTHEK\n" +
                        "Alte Mainmühle");
                stageImage.setImageDrawable(getResources().getDrawable(R.drawable.staatl_hofkeller_img));
                break;
            case 4:
                stageTitle.setText("HERZLICH WILLKOMMEN");
                stageSubtitle.setText("IN DER VINOTHEK\n" +
                        "Juliusspital");
                stageImage.setImageDrawable(getResources().getDrawable(R.drawable.staatl_hofkeller_img));
                break;
        }
    }

}
