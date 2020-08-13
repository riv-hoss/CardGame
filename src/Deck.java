import java.util.Random;

public class Deck {
    private Card[] cards;

    public Deck (int n) {
        this.cards = new Card[n];
    }

    // 52 deck constructor
    public Deck () {
        this.cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[index] = new Card(rank, suit);
                index++;
            }
        }
    }


    // print deck method
    public void print () {
        for (Card c : this.cards) {
            System.out.println(c);
        }
    }

    public Card[] getCards () {
        return this.cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public void setCards(Card card, int i) {
        this.cards[i] = card;
    }

    // shuffle deck
    public void shuffle() {
        for (int i = 0; i < cards.length ; i++) {
            int index01 = randomInt(i, cards.length -1);
            swapCards(i, index01);

        }
    }

    // method within shuffle() - generate random number in a range
    private static int randomInt (int low, int high) { // helper method - private
        Random rnd = new Random();                      // instance method
        int range = high - low +1;
        return rnd.nextInt(range) + low ;
    }

    // method within shuffle() and selectionSort() - swap cards at two positions
    private void swapCards (int i, int j) { // non-static since referred to this.cards
        Card first = this.cards[i];         // helper method - private
        cards[i] = cards[j];                // class method
        cards[j] = first;
    }


    // sort deck
    public void selectionSort () {
        for (int i = 0; i < cards.length ; i++) {
            int index01 = indexLowest(i, cards.length);
            swapCards(i, index01);

        }

    }

    // method within selectionSort() - find lowest card at or to the right of index i
    public int indexLowest (int low, int high) {
        Card minCard = cards[low];
        int indexOfMin = low;
        for (int j = low+1 ; j < high; j++) {
            if (minCard.compareTo(cards[j]) > 0) {
                minCard = cards[j];
                indexOfMin = j;
            }
        }
        return indexOfMin;
    }


    // generate sub deck
    public Deck subDeck(int low, int high) {
        Deck sub = new Deck(high - low + 1);
        for (int i = 0; i < sub.cards.length; i++) {
            sub.cards[i] = this.cards[low + i];
        }
        return sub;
    }


    // merge
    private static Deck merge(Deck d1, Deck d2) {
        // create a new deck, d3, big enough for all the cards
        int d1Length = d1.getCards().length;
        int d2Length = d2.getCards().length;
        int totalCards = d1Length + d2Length;
        Deck d3 = new Deck(totalCards);
        // use the index i to keep track of where we are at in
        // the first deck, and the index j for the second deck

        int i = 0;
        int j = 0;
        // the index k traverses the result deck
        for (int k = 0; k < totalCards; k++) {
            // if d1 is empty, use top card from d2
            // if d2 is empty, use top card from d1
            // otherwise, compare the top two cards
            // add lowest card to the new deck at k
            // increment i or j (depending on card)
            if (i >= d1Length) {
                Card nextCard = d2.getCards()[j];
                d3.setCards(nextCard, k);
                j++;
            } else if (j >= d2Length) {
                Card nextCard = d1.getCards()[i];
                d3.setCards(nextCard, k);
                i++;
            } else {
                if (d1.getCards()[i].compareTo(d2.getCards()[j]) < 0) {
                    Card nextCard = d1.getCards()[i];
                    d3.setCards(nextCard, k);
                    i++;
                } else {
                    Card nextCard = d2.getCards()[j];
                    d3.setCards(nextCard, k);
                    j++;
                }
            }
        }
        return d3; // return the new deck
    }

    // almost efficient
    public Deck almostMergeSort() {

        // divide the deck into two subdecks
        Deck sub1 = this.subDeck(0, cards.length/2);
        Deck sub2 = this.subDeck(cards.length/2 + 1, cards.length -1);
        // sort the subdecks using selectionSort
        sub1.selectionSort();
        sub2.selectionSort();
        // merge the subdecks, return the result


        return merge(sub1, sub2);
    }




    // with recursive - efficient
    public Deck mergeSort() {
        int len = this.getCards().length;
        if (len == 0 || len == 1) { // if the deck has 0 or 1 cards, return it
            return this;
        } else {
            // otherwise, divide the deck into two subdecks
            Deck d1 = subDeck(0, len/2 -1).mergeSort();
            Deck d2 = subDeck(len/2, len -1).mergeSort(); // sort the subdecks using mergeSort

            return merge(d1, d2); //merge the subdecks and return the result

        }

    }

}
