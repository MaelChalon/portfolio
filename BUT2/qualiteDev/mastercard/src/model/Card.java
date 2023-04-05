package model;

import java.util.HashMap;

/**
 * Cette classe abstraite doit être mère de n'importe quelle carte.
 */
public abstract class Card {

    /**
     * Cette énumération référence tous les types de cartes. Si vous voulez créer un nouveau type de carte, il faut
     * l'ajouter dans l'énumération avant de créer la classe.
     *
     */
    public enum TypeEnum {
        WERE_WOLF,
        FIFTY_TWO_CARD
    }

    private static int idCounter = 0;
    protected int id;
    protected String name;
    protected TypeEnum type;

    protected Card(String name, TypeEnum type) {
        this.id = Card.idCounter;
        Card.idCounter++;
        this.name = name;
        this.type = type;
    }

    /**
     * Cette methode permet de récuperer tous les attributs d'une cartes quelque soit son type.
     * @return Cette methode renvoie une Hashmap avec chaque attribut serializé en Json.
     */
    protected abstract HashMap<String, String> getAttribute();
}
