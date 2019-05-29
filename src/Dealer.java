/***
 *
 *
 * Dealer object is only created once in the game. Each indivdual player is playing against the dealer.
 *
 */


public class Dealer extends Person {

    public boolean stay = false;

    //function determines if the dealer will hit or stay
    public boolean hitOrNo() {
        if (this.cardTotal >= 15)
        {
            stay = true;
            return false;
        }
        else
        {
            return true;
        }

    }
}
