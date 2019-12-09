package gabrielfernandeslemos.android.controledegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(new SimpleSlide.Builder()
                .title("Titulo")
                .description("descricao")
                .image(R.drawable.um)
                .background(R.color.colorPrimary)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Titulo2")
                .description("descricao2")
                .image(R.drawable.dois)
                .background(R.color.colorPrimary)
                .canGoForward(false)
                .build());


    }
}

