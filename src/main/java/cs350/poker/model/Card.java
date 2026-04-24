package cs350.poker.model;

import java.util.Objects;

/**
 * Represents a single immutable playing card in a standard 52-card deck.
 *
 * <p>Each card is identified by a suit (1=Clubs, 2=Diamonds, 3=Hearts, 4=Spades)
 * and a face value (1=Ace, 2=2, ..., 11=Jack, 12=Queen, 13=King). Cards are
 * immutable once constructed; no mutator methods are provided.</p>
 *
 * <p>The rank of a card is computed so that Aces are highest and Deuces lowest.
 * Rank formula: if Ace, rank = 12*4 + suit; otherwise rank = (faceValue - 2)*4 + suit.
 * This yields a unique rank for every card in the deck (1..52).</p>
 *
 * @author Poker Demo
 * @version 1.0
 */
public class Card implements Comparable<Card> {

	public static final int CLUBS = 1;
	public static final int DIAMONDS = 2;
	public static final int HEARTS = 3;
	public static final int SPADES = 4;
	
	public static final int MAX_FACE = 13;
	public static final int MIN_FACE = 1;
	
	public static final int MIN_SUIT = 1;
	public static final int MAX_SUIT = 4;
	
	
	
	private final int face;
	private final int suit;
	
	public Card(int suit, int face) {
		
		if(suit < MIN_SUIT || suit > MAX_SUIT) {
			throw new IllegalArgumentException
			("Suit Must be Between " + MIN_SUIT + " and " + MAX_SUIT + " currently have " + suit);
		}
		
		if(face < MIN_FACE || face > MAX_FACE) {
			throw new IllegalArgumentException
			("Face Must be Between " + MIN_FACE + " and " + MAX_FACE + " currently have " + face);
			
		}
		
		this.face = face;
		this.suit = suit;
		
	}
	
	public int getSuitNum() {
		return suit;
	}
	public int getFaceNum() {
		return face;
	}
	
	public String getFaceString() {
		switch (face) {
		case 1: return "Ace";
		case 11: return "Jack";
		case 12: return "Queen";
		case 13: return "King";
		default : return String.valueOf(face);
		
		}
		
	}
	
	public String getSuitString() {
		switch (suit) {
		case CLUBS: return "Clubs";
		case DIAMONDS: return "Diamonds";
		case HEARTS: return "Hearts";
		case SPADES: return "Spades";
		default: return "Unknown";
		}
	}
	

	
	public int getRank() {
		if(face == 1) {
			return 12 * 4 + suit;
			
		}
		return (face - 2) * 4 + suit;
	}
	
	@Override
	public boolean equals(Object obj) {
	if(this == obj) return true;
	if(obj == null || getClass() != obj.getClass()) return false;
	
	Card other = (Card)obj;
	return this.face == other.face && this.suit == other.suit;
	
		
	}
	
	
	//override the hashCode to prevent hashSet / hashMaps breaking
	@Override
	public int hashCode() {
	    return Objects.hash(face,suit);
	}
	
	@Override
	public int compareTo(Card other) {
		return Integer.compare(this.getRank(), other.getRank());
	}
	

	@Override
	public String toString() {
		return getFaceString() + " of " + getSuitString();
	}

	
	//dealt 5 cards, choose whichever you dont want of the hand, then draw 3 more 
	
	//add comments - final 
	
	
	

}
