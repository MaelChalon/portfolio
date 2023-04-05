package model;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Cette class permet de générer une carte de jeu de 52 cartes classique.
 */
public class FiftyTwoCards extends Card{

	/**
	 * Cette Enumeration gére toutes les couleurs de cartes possible
	 */
	public enum Suit {
		SPADES,
		CLUBS,
		DIAMONDS,
		HEARTS
	}

	/**
	 * Cette Enumeration gére toutes les valeurs de cartes possible
	 */
	public enum Rank {
		JOKER,
		AS,
		KING,
		QUEEN,
		JACK,
		TEN,
		NINE,
		EIGHT,
		SEVEN,
		SIX,
		FIVE,
		FOUR,
		THREE,
		TWO
	}
	
	private final Suit suit;
	private final Rank rank;

	public FiftyTwoCards(String name, Suit suit, Rank rank) {
		super(name, TypeEnum.FIFTY_TWO_CARD);
		this.suit = suit;
		this.rank = rank;
	}

	@Override
	public HashMap<String, String> getAttribute() {
		Gson gson = new Gson();
		HashMap<String, String> attributes = new HashMap<>();
		attributes.put("id", gson.toJson(this.id));
		attributes.put("name", gson.toJson(this.name));
		attributes.put("type", gson.toJson(this.type));
		attributes.put("suit", gson.toJson(this.suit));
		attributes.put("rank", gson.toJson(this.rank));
		return attributes;
	}
}
