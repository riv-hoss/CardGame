import java.util.Arrays;

public class Main {





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
        System.out.println(binarySearch(cards.getCards(), new Card(2,0)));;




    }
}
