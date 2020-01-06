package smartinov.pokedex;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CardsActivity extends AppCompatActivity implements ListAdapter.RowClickInterface{

    private List<Pokemon> pokemons;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        Intent intent = getIntent();
        Cards cards = (Cards) intent.getSerializableExtra("pokemons");
        pokemons = cards.getCards();

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListAdapter(this);
        listAdapter.setRowClickInterface(this);

        recyclerView.setAdapter(listAdapter);
        listAdapter.setPokemons(pokemons);
        listAdapter.notifyDataSetChanged();

    }


    @Override
    public void onRowClick(View view, int position) {
        Pokemon pokemon = listAdapter.getPokemon(position);

        if(pokemon == null){
            return;
        }

        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("pokemon", pokemon);
        startActivity(intent);


    }


}
