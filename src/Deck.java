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
}
