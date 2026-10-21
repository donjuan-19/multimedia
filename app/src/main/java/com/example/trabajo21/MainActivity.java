package com.example.trabajo21;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button btnPlayPause;
    private int posicionAudio = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ==========================================
        // 1. CONFIGURACIÓN DEL VIDEO CON SUS CONTROLES (Play, Pausa, Adelantar)
        // ==========================================
        VideoView videoView = findViewById(R.id.videoview);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video123;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        // Añadimos los controles táctiles al video (la barra flotante con botones)
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start(); // Inicia la reproducción del video

        // ==========================================
        // 2. CONFIGURACIÓN DE LA MÚSICA DE FONDO Y SU BOTÓN
        // ==========================================
        btnPlayPause = findViewById(R.id.btnPlayPause);

        // Inicializa tu archivo de música ubicado en res/raw/musica.mp3
        mediaPlayer = MediaPlayer.create(this, R.raw.musica);

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    // Si la música está sonando, la pausamos
                    mediaPlayer.pause();
                    posicionAudio = mediaPlayer.getCurrentPosition();
                    btnPlayPause.setText("Reanudar Música");
                } else {
                    // Si está pausada, la reanudamos
                    mediaPlayer.seekTo(posicionAudio);
                    mediaPlayer.start();
                    btnPlayPause.setText("Pausar Música");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberamos memoria de la música al cerrar la app
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}