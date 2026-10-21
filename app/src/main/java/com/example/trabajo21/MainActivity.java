package com.example.trabajo21;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Referenciar el VideoView del XML
        VideoView videoView = findViewById(R.id.videoview);

        // 2. Crear la URI apuntando al video dentro de la carpeta raw (ej: video123.mp4)
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video123;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        // 3. (Opcional) Agregar controles de reproducción (Play, Pausa, Adelantar)
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // 4. Iniciar la reproducción del video
        videoView.start();
    }
}