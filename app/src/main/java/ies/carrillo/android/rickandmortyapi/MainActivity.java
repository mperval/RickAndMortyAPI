package ies.carrillo.android.rickandmortyapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import adapters.AdapterCharacter;
import apiClients.CharacterApiClient;
import apiServices.CharacterApiService;
import details.DetailsCharacter;
import models.Character;
import models.Info;
import models.NamedAPIResourceList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Info info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvCharacter = findViewById(R.id.LvCharacter);
        Button btnSiguiente = findViewById(R.id.btnSiguiente);

        // Creamos la instancia del APIService y lo inicializo.
        CharacterApiService characterApiService = CharacterApiClient.getClient().create(CharacterApiService.class);

        // Creamos la llamada al metodo que nos devuelve todos los personajes.
        Call<NamedAPIResourceList> callCharacterss = characterApiService.getCharacter();


        callCharacterss.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                // Castemos la respuesta en la clase del Modelo
                NamedAPIResourceList  namedAPIResourceList = (NamedAPIResourceList) response.body();
                // Cojo la informacion del personaje
                List<Character> characterList = namedAPIResourceList.getResults();

                info = namedAPIResourceList.getInfo();
                Log.i("info principal", info.toString());

                AdapterCharacter adapterCharacter = new AdapterCharacter(MainActivity.this, characterList);
                lvCharacter.setAdapter(adapterCharacter);

                for(Character p : namedAPIResourceList.getResults()){
                    Log.i("Character:", p.toString());
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("Error", "The request could not be made");
            }
        });

        lvCharacter.setOnItemClickListener((parent, view, position, id) -> {
            Character character = (Character) parent.getItemAtPosition(position);

            Intent intent = new Intent(MainActivity.this, DetailsCharacter.class);
            intent.putExtra("character", character);
            startActivity(intent);
        });

        //TODO no funciona correctamente
        btnSiguiente.setOnClickListener(v -> {

            URL nextUrl = null;
            try {
                nextUrl = new URL(info.getNext());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            String pageQueryParam = nextUrl.getQuery().split("=")[1];
            int nextPage = Integer.parseInt(pageQueryParam);
            Log.i("nextPage", String.valueOf(nextPage));

            CharacterApiService characterApiServices = CharacterApiClient.getClient().create(CharacterApiService.class);
            Call<NamedAPIResourceList> callCharactersPage = characterApiServices.getCharacterByPage(nextPage);

            callCharactersPage.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    // Castemos la respuesta en la clase del Modelo
                    NamedAPIResourceList  namedAPIResourceLists = (NamedAPIResourceList) response.body();
                    // Cojo la informacion del personaje
                    List<Character> characterList = namedAPIResourceLists.getResults();

                    Info infos = namedAPIResourceLists.getInfo();
                    Log.i("info boton", infos.toString());

                    AdapterCharacter adapterCharacter = new AdapterCharacter(MainActivity.this, characterList);
                    lvCharacter.setAdapter(adapterCharacter);

                    for(Character p : namedAPIResourceLists.getResults()){
                        Log.i("Character:", p.toString());
                    }
                }
                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.e("Error", "The request could not be made");
                }
            });
        });
    }
}