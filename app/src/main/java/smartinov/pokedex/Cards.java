package smartinov.pokedex;

import java.io.Serializable;
import java.util.List;

public class Cards implements Serializable {

    private List<Pokemon> cards;

    public List<Pokemon> getCards(){
        return this.cards;
    }

}
