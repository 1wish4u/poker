package cs350.poker.analysis;

import cs350.poker.model.Card;
import cs350.poker.model.Hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//this is a change for jenkins
//test the private helper methods.

public class HandAnalyzer {
    public static final int HIGH_CARD = 0;
    public static final int ONE_PAIR = 1;
    public static final int TWO_PAIR = 2;
    public static final int THREE_OF_A_KIND = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int FOUR_OF_A_KIND = 7;
    public static final int STRAIGHT_FLUSH = 8;
    public static final int ROYAL_FLUSH = 9;
    private static final String[] HAND_TYPE_NAMES = new String[]{"High Card", "One Pair", "Two Pair", "Three of a Kind", "Straight", "Flush", "Full House", "Four of a Kind", "Straight Flush", "Royal Flush"};
    private final Hand hand;
    private final int handType;
    private final int[] faceCounts;
    private final boolean flushFlag;
    private final boolean straightFlag;

    

    
    public HandAnalyzer(Hand hand) {
        if (hand == null) {
            throw new IllegalArgumentException("Hand cannot be null.");
        } else if (hand.size() != 5) {
            throw new IllegalArgumentException("Hand must contain exactly 5 cards, but has " + hand.size());
        } 
        this.hand = hand;
        
        //phase 1 
        
        faceCounts = countFaces();
        
        //phase 2
       
        flushFlag = detectFlush();
        straightFlag = detectStraight();
        
        //phase 3
        handType = evaluateHandType();
        
    }

    private int[] countFaces() {
    	int[]counts = new int[Card.MAX_FACE + 1];
    	List<Card> cards = hand.getCards();
    	for(Card c: cards) {
    		counts[c.getFaceNum()]++;
    	}
    	return counts;
    }

    private boolean detectFlush() {
    	List<Card> cards = hand.getCards();
    	int suit = cards.get(0).getSuitNum();
    	for(int i = 1;i < cards.size(); i++) {
    		if(cards.get(i).getSuitNum() != suit) {
    			return false;
    		}
    	}
        return true;
    }

    private boolean detectStraight() {
        List<Card> sorted = new ArrayList<>(hand.getCards());
        sorted.sort(Comparator.comparingInt(Card::getFaceNum));

        if (isWheel(sorted)) return true;

        boolean aceHigh = sorted.get(0).getFaceNum() == 1 &&
                          sorted.get(1).getFaceNum() == 10 &&
                          sorted.get(2).getFaceNum() == 11 &&
                          sorted.get(3).getFaceNum() == 12 &&
                          sorted.get(4).getFaceNum() == 13;
        if (aceHigh) return true;

        for (int i = 1; i < sorted.size(); i++) {
            int prev = sorted.get(i - 1).getFaceNum();
            int curr = sorted.get(i).getFaceNum();

            if (prev == 1) prev = 14;

            if (curr - prev != 1) return false;
        }

        return true;
    }

    private boolean isWheel(List<Card> cards) {
    	boolean hasAce = false;
    	boolean hasTwo = false;
    	boolean hasThree = false;
    	boolean hasFour = false;
    	boolean hasFive = false;
    	
    	for(Card c : cards) {
    		int face = c.getFaceNum();
    		switch(face) {
    		case 1-> hasAce = true;
    		case 2-> hasTwo = true;
    		case 3-> hasThree = true;
    		case 4-> hasFour = true;
    		case 5-> hasFive = true;
    		}
    	}
    	
    	return hasAce && hasTwo && hasThree && hasFour && hasFive;
    }

    protected int evaluateHandType() {
    	int pairs = 0;
    	int threeOfAKind = 0;
    	int fourOfAKind = 0;
    	
    	for(int i = Card.MIN_FACE; i <= Card.MAX_FACE;i++) {
    		if(faceCounts[i] == 2) pairs++;
    		else if (faceCounts[i] == 3) threeOfAKind++;
    		else if (faceCounts[i] == 4)fourOfAKind++;
    		
    	}
    	
    	if (flushFlag && straightFlag) {
    		if(faceCounts[1] == 1 && faceCounts[10] == 1 && faceCounts[11] == 1
    				&& faceCounts[12] == 1 && faceCounts[13] == 1) {
    			return ROYAL_FLUSH;
    		}
    		return STRAIGHT_FLUSH;
    	}
    	if(fourOfAKind > 0) return FOUR_OF_A_KIND;
    	if(threeOfAKind > 0 && pairs > 0) return FULL_HOUSE;
    	if(flushFlag) return FLUSH;
    	if(straightFlag) return STRAIGHT;
    	if(threeOfAKind > 0) return THREE_OF_A_KIND;
    	if(pairs == 2)return TWO_PAIR;
    	if(pairs == 1)return ONE_PAIR;
    	return HIGH_CARD;
    }

    public int getHandType() {
        return this.handType;
    }

    public String getHandTypeName() {
        return HAND_TYPE_NAMES[this.handType];
    }

    public boolean isRoyalFlush() {
        return this.handType == 9;
    }

    public boolean isStraightFlush() {
        return this.handType == 8;
    }

    public boolean isFourOfAKind() {
        return this.handType == 7;
    }

    public boolean isFullHouse() {
        return this.handType == 6;
    }

    public boolean isFlush() {
        return this.handType == 5;
    }

    public boolean isStraight() {
        return this.handType == 4;
    }

    public boolean isThreeOfAKind() {
        return this.handType == 3;
    }

    public boolean isTwoPair() {
        return this.handType == 2;
    }

    public boolean isOnePair() {
        return this.handType == 1;
    }

    public boolean isHighCard() {
        return this.handType == 0;
    }

    public Hand getHand() {
        return this.hand;
    }

    public String toString() {
        String var10000 = this.getHandTypeName();
        return "Hand Type: " + var10000 + "\n" + this.hand.toString();
    }
}
