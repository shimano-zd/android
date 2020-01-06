package smartinov.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListRow> {

    private List<Pokemon> pokemons;
    private LayoutInflater layoutInflater;
    private RowClickInterface rowClickInterface;

    public ListAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    public void setPokemons(List<Pokemon> pokemons){
        this.pokemons = pokemons;
    }



    @Override
    public ListAdapter.ListRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_row, parent, false);
        return new ListRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ListRow holder, int position) {
            Pokemon pokemon = pokemons.get(position);
            holder.name.setText(pokemon.getName());
            holder.pokedexNumber.setText(pokemon.getPokedexNumber()+"");
    }

    @Override
    public int getItemCount() {
        return pokemons == null ? 0 : pokemons.size();
    }

    public Pokemon getPokemon(int position){
        return pokemons.get(position);
    }


    public class ListRow extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private TextView pokedexNumber;

        public ListRow(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.listRowName);
            pokedexNumber = itemView.findViewById(R.id.listRowPokedexNumber);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                if(rowClickInterface == null){
                    return;
                }
                rowClickInterface.onRowClick(view, getAdapterPosition());
        }
    }

    public void setRowClickInterface(RowClickInterface clickInterface){
        this.rowClickInterface = clickInterface;

    }

    public interface RowClickInterface{
        void onRowClick(View view, int position);
    }
}
