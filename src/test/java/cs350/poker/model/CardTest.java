package cs350.poker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

	
	Card c;
	
	@Test
	public void test_constructor() throws Exception{
		Card c = new Card(1,2);
		
		Assert.assertNotNull(c);	
	}
	
	
	@Test
	public void testFaceString() {
		assertEquals("Ace", new Card(Card.CLUBS,1 ).getFaceString());
		assertEquals("Jack", new Card(Card.CLUBS,11).getFaceString());
		assertEquals("Queen", new Card(Card.CLUBS,12).getFaceString());
		assertEquals("King", new Card(Card.CLUBS,13).getFaceString());
		assertEquals("7", new Card(Card.CLUBS,7).getFaceString());
		
	}
	
	@Test
	public void testSuitString() {
		assertEquals("Clubs", new Card(Card.CLUBS,5).getSuitString());
		assertEquals("Diamonds", new Card(Card.DIAMONDS,5).getSuitString());
		assertEquals("Hearts", new Card( Card.HEARTS,5).getSuitString());
		assertEquals("Spades", new Card(Card.SPADES,5).getSuitString());
	}
	
	
	@Test
	public void testRankOrderingNumbers() {
		Card twoClubs = new Card(Card.CLUBS,2);
		Card threeClubs = new Card(Card.CLUBS,3);
		
		assertTrue(twoClubs.getRank() < threeClubs.getRank());
	}
	
	@Test
	public void testAceHighRank() {
		Card kingSpades = new Card(Card.SPADES,13);
		Card aceClubs = new Card(Card.CLUBS,1);
		
		assertTrue(aceClubs.getRank() > kingSpades.getRank());
		
	}
	
	@Test
	public void testEqualsSameFace() {
		
		Card card1 = new Card(Card.CLUBS,5);
		Card card2 = new Card(Card.HEARTS,5);

		assertNotEquals(card1,card2);
	}
	
	@Test
	public void testNotEqualsDifferentFace() {
		
		Card card1 = new Card(Card.CLUBS,5);
		Card card2 = new Card(Card.CLUBS,6);

		assertNotEquals(card1,card2);
	}
	
	@Test
	public void testHashCodeConsistency() {
		
		Card card1 = new Card(Card.CLUBS,10);
		Card card2 = new Card(Card.CLUBS,10);

		assertEquals(card1.hashCode(),card2.hashCode());
	}


	@Test
	public void testCompareLessThan() {
		Card two = new Card(Card.CLUBS,2);
		Card three = new Card(Card.CLUBS,3);
		
		
		assertTrue(two.compareTo(three)< 0);
	}
	
	@Test
	public void testCompareGreater() {
		Card ace = new Card(Card.CLUBS,1);
		Card king = new Card(Card.CLUBS,13);
		
		
		assertTrue(ace.compareTo(king) > 0);
	}
	
	
	
	@Test
	public void testCompareEqual() {
		Card card1 = new Card(Card.CLUBS,7);
		Card card2 = new Card(Card.CLUBS,7);
		
		assertEquals(0,card1.compareTo(card2));
	}
	
	@Test
	public void testToString() {
		Card card = new Card(Card.HEARTS,1);
		assertEquals("Ace of Hearts", card.toString());
	}
	
}

