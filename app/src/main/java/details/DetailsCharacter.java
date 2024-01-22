package details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import ies.carrillo.android.rickandmortyapi.R;
import models.Character;

public class DetailsCharacter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_character);

        TextView name = findViewById(R.id.tvDetailsName);
        TextView especie = findViewById(R.id.tvDetailsSpecie);
        TextView mundo = findViewById(R.id.tvDetailsMundo);
        ImageView imagen = findViewById(R.id.ivPersonaje);

        Intent intent = getIntent();

        Character character = (Character) intent.getSerializableExtra("character");

        name.setText("Nombre: " + character.getName());
        especie.setText("Especie: " + character.getSpecie());
        mundo.setText("Mundo: " + character.getLocation().getName());
        Picasso.get().load(character.getImage()).into(imagen);

    }
}