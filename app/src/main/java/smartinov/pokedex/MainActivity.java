package smartinov.pokedex;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button searchButton;
    private TextView searchText;
    private TextView titleText;
    private final String baseUrl = "https://api.pokemontcg.io/v1/cards?name=";
    private TextView noResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = (Button)findViewById(R.id.searchButton);
        searchText = (TextView) findViewById(R.id.searchText);
        titleText = (TextView) findViewById(R.id.txtTitle);
        noResultText = (TextView) findViewById(R.id.noResultText);
        noResultText.setVisibility(View.INVISIBLE);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = searchText.getText().toString();

                if(text == null || text.equals("")){
                    return;
                }

                GetCardsTask getCards = new GetCardsTask();
                String searchUrl = baseUrl + text;
                getCards.execute(searchUrl);

            }
        });
    }

    private class GetCardsTask extends AsyncTask<String, Void, Cards>{

        @Override
        protected Cards doInBackground(String... strings){
            String urlString = strings[0];

            try{

                    URL url = new URL(urlString);

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setReadTimeout(15000);
                con.setConnectTimeout(15000);
                con.connect();

                InputStreamReader streamReader = new InputStreamReader(con.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);

                Cards response = new Gson().fromJson(bufferedReader, Cards.class);

                bufferedReader.close();
                streamReader.close();

                return response;

            } catch (Exception e){

                Log.d("ERROR", e.getMessage());
            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values){

        }

        @Override
        public void onPostExecute(Cards cards){
            if(cards == null || cards.getCards().size() == 0){
                noResultText.setVisibility(View.VISIBLE);
            }else{
                noResultText.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(getApplicationContext(), CardsActivity.class);
                intent.putExtra("pokemons", cards);
                startActivity(intent);
            }
        }
    }


}
