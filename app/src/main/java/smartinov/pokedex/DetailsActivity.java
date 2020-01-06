package smartinov.pokedex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class DetailsActivity extends AppCompatActivity {

    private Pokemon pokemon;
    private TextView name;
    private TextView number;
    private TextView type;
    private ImageView image;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        Pokemon pokemon = (Pokemon) intent.getSerializableExtra("pokemon");

        name = findViewById(R.id.pokemonDetailsName);
        name.setText(pokemon.getName());

        number = findViewById(R.id.pokemonDetailsNumber);
        number.setText("Pokedex number: " + pokemon.getPokedexNumber());

        type = findViewById(R.id.pokemonDetailsType);
        String typeConcat = "";

        for(String singleType : pokemon.getTypes()){
            typeConcat += singleType + " ";
        }
        type.setText("Type: " + typeConcat);

        btnReturn = findViewById(R.id.btnBackFromDetails);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        image = findViewById(R.id.pokemonImage);
        DownloadImageTask downloadImageTask = new DownloadImageTask(image);
        downloadImageTask.execute(pokemon.getImageUrl());



    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView pokemonImage;

        public DownloadImageTask(ImageView bmImage) {
            this.pokemonImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(imageUrl).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            pokemonImage.setImageBitmap(result);
        }
    }


}
