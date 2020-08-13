import java.util.Arrays;
import java.util.Random;

public class Main {

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



    // sequential search method - not sufficient - no other way as array is not ordered
    public static int search (Card[] cards, Card target) {
        for (int i = 0; i < cards.length ; i++) {
            if (cards[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // binary search method - more sufficient - if array is ordered
    public static int binarySearch (Card[] cards, Card target) {
        int low = 0;
        int high = cards.length - 1;
        while (low <= high) {
            System.out.println(low + ", " + high);
            int mid = (low + high) / 2;
            int comp = cards[mid].compareTo(target);

            if (comp == 0) {
                return mid;
            } else if (comp < 0) {
                low = mid + 1;
            } else {
                high = mid -1 ;
            }
        }

        return -1;
    }



    public static void main(String[] args) {

        Deck cards = new Deck(); // creating a new 52 cards deck from Deck class

        System.out.println("\n=============== printing deck " +
                "using printDeck() method ==================");
        cards.print();


        System.out.println("\n========================= testing " +
                " binarySearch() ==========================");
        System.out.println(binarySearch(cards.getCards(), new Card(2,0)));


        System.out.println("\n=============== printing deck " +
                "after shuffle() ==================");
        cards.shuffle();
        cards.print();


        System.out.println("\n=============== printing deck " +
                "after selectionSort() ==================");
        cards.selectionSort();
        cards.print();


        Deck myDeck = new Deck();
        myDeck.shuffle();

        Deck d1 = myDeck.subDeck(40,51);
        Deck d2 = myDeck.subDeck(10,20);
        System.out.println("=".repeat(30) + " deck 1" + "=".repeat(30));
        d1.print();

        System.out.println("=".repeat(30) + " deck 2" + "=".repeat(30));
        d2.print();


        System.out.println("=".repeat(30) + " deck 1 after sort" + "=".repeat(30));
        d1.selectionSort();
        d1.print();
        System.out.println("=".repeat(30) + " deck 2 after sort" + "=".repeat(30));
        d2.selectionSort();
        d2.print();





        System.out.println("=".repeat(30) + " merged d3" + "=".repeat(30));
        Deck d3 = merge(d1,d2);
        d3.print();




        System.out.println("=".repeat(30) + " sort myDeck with almostMergeSort() " + "=".repeat(30));
        myDeck = myDeck.almostMergeSort();
        myDeck.print();


        System.out.println("=".repeat(30) + " sort myDeck with mergeSort() " + "=".repeat(30));
        myDeck.shuffle();
        myDeck = myDeck.mergeSort();
        myDeck.print();
    }
}
