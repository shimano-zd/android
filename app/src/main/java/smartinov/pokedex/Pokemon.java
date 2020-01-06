package smartinov.pokedex;

import java.io.Serializable;

public class Pokemon implements Serializable {

        private String id;
        private String name;
        private int nationalPokedexNumber;
        private String imageUrl;
        private String[] types;
        private int hp;

        public Pokemon(String id, String name, int pokedexNumber, String imageUrl, String[] types, int hp){

            this.hp = hp;
            this.id = id;
            this.name = name;
            this.types = types;
            this.nationalPokedexNumber = pokedexNumber;
            this.imageUrl = imageUrl;
        }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPokedexNumber() {
        return nationalPokedexNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String[] getTypes() {
        return types;
    }

    public int getHp() {
        return hp;
    }
}
