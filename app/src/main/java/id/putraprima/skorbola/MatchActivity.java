package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MatchActivity extends AppCompatActivity {
    TextView homeTeamTextView, awayTeamTextView, homeScoreTextView, awayScoreTextView;
    Button addHomeScoreButton, addAwayScoreButton, checkResultButton;
    ImageView homeTeamLogo, awayTeamLogo;
    int homeScore = 0;
    int awayScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeTeamTextView = findViewById(R.id.txt_home);
        awayTeamTextView = findViewById(R.id.txt_away);
        homeTeamLogo = findViewById(R.id.home_logo);
        awayTeamLogo = findViewById(R.id.away_logo);
        homeScoreTextView = findViewById(R.id.score_home);
        awayScoreTextView = findViewById(R.id.score_away);
        addHomeScoreButton = findViewById(R.id.btn_add_home);
        addAwayScoreButton = findViewById(R.id.btn_add_away);
        checkResultButton = findViewById(R.id.btn_result);

        // Mendapatkan data dari Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String homeTeam = extras.getString("homeTeam");
            String awayTeam = extras.getString("awayTeam");
            String homeTeamLogoUriString = extras.getString("homeTeamLogoUri");
            String awayTeamLogoUriString = extras.getString("awayTeamLogoUri");

            // Menampilkan detail match sesuai data dari MainActivity
            homeTeamTextView.setText(homeTeam);
            awayTeamTextView.setText(awayTeam);

            // Set gambar logo home team jika URI tidak null
            if (homeTeamLogoUriString != null) {
                Uri homeTeamLogoUri = Uri.parse(homeTeamLogoUriString);
                homeTeamLogo.setImageURI(homeTeamLogoUri);
            }

            // Set gambar logo away team jika URI tidak null
            if (awayTeamLogoUriString != null) {
                Uri awayTeamLogoUri = Uri.parse(awayTeamLogoUriString);
                awayTeamLogo.setImageURI(awayTeamLogoUri);
            }


        }

        addHomeScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeScore++;
                updateScoreDisplay();
            }
        });

        addAwayScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awayScore++;
                updateScoreDisplay();
            }
        });


        checkResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String winner;
                if (homeScore > awayScore) {
                    winner = "Pemenanganya " +  homeTeamTextView.getText().toString() + " 🔥🔥";
                } else if (homeScore < awayScore) {
                    winner = "Pemenanganya " +  awayTeamTextView.getText().toString() + " 🔥🔥";
                } else {
                    winner = "Draw";
                }


                Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
                intent.putExtra("winner", winner );
                startActivity(intent);
            }
        });



        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }
    // Metode untuk memperbarui tampilan skor
    private void updateScoreDisplay() {
        homeScoreTextView.setText(String.valueOf(homeScore));
        awayScoreTextView.setText(String.valueOf(awayScore));
    }
}
