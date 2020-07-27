public class Card {
    private final int rank;
    private final int suit;
    public static final String[] RANKS = {null,"Ace", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "Jack", "Queen", "King"};
    public static final String[] SUITS = {"Clubs", "Diamonds", "Heart", "Spades"};

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // getter
    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }


    // equals method
    public boolean equals(Card that) {
        return this.rank == that.rank && this.suit == that.suit;
    }

    // compareTo method
    public int compareTo (Card that) {

        if (this.suit > that.suit) {
            return 1;
        }
        if (this.suit < that.suit) {
            return -1;
        }
        if (this.rank > that.rank) {
            return 1;
        }
        if (this.rank < that.rank) {
            return -1;
        }
        return 0;
    }



    @Override
    public String toString() {
        return String.format("(%s of %s)", RANKS[this.rank], SUITS[this.suit]);
    }
}
