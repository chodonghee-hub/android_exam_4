package com.example.sbs.myapplication.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sbs.myapplication.Pokemon;
import com.example.sbs.myapplication.R;
import com.example.sbs.myapplication.service.PokemonService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 포켓몬 서비스
        final PokemonService pokemonService = new PokemonService();

        // 포켓몬 리사이클러 뷰
        final RecyclerView recyclerViewPokemon = findViewById(R.id.activity_main__recyclerViewPokemon);
        // 포켓몬 리사이클러 뷰 어덥터
        final RecyclerViewPokemonAdapter recyclerViewPokemonAdapter = new RecyclerViewPokemonAdapter();

        // 푸터의 `더 보기` 버튼을 클릭하면 일어날 일을 세팅
        recyclerViewPokemonAdapter.setOnClickLoadMore(view -> {
            pokemonService.getPokemons(responseBody -> {
                recyclerViewPokemonAdapter.addPokemons(responseBody.getResults());
            });
        });

        recyclerViewPokemon.setAdapter(recyclerViewPokemonAdapter);
    }
}
