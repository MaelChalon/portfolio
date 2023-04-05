package model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe permet de genérer une carte du jeu de carte Loup Garous (WereWolf en anglais).
 * Elle Etend les fonctionnalités de la classe Abstraite Card
 */
public class WereWolf extends Card{

    /**
     * Cette enumeration nous permet de lister les différents camps (Faction en anglais) du jeu de carte du Loup Garous
     */
    public enum FactionsEnum{
        VILLAGE, WEREWOLF, SOLO
    }

    private String description;
    private ArrayList<FactionsEnum> factions;

    public WereWolf(String name, String description){
        super(name, TypeEnum.WERE_WOLF);
        this.description = description;
        this.factions = new ArrayList<>();
    }

    /**
     * Cette methode permet d'ajouter une faction à la carte
     * @param faction La faction à ajouter(FactionEnum)
     */
    public void addFaction(FactionsEnum faction){
        this.factions.add(faction);
    }

    @Override
    public HashMap<String, String> getAttribute() {
        Gson gson = new Gson();
        HashMap<String,String> result = new HashMap<>();

        result.put("id", gson.toJson(this.id));
        result.put("name", gson.toJson(this.name));
        result.put("type", gson.toJson(this.type));
        result.put("description", gson.toJson(this.description));
        result.put("factions", gson.toJson(this.factions));

        return result;
    }
}
