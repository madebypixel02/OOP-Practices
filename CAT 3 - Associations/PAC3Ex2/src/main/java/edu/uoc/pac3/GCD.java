/* ************************************************************************** */
/*                                                                            */
/*                                           =@@*   +@@+                      */
/*                                           =@@*   +@@+ :*%@@@%*:            */
/*                                           =@@*   =@@+.@@@=--%@@-           */
/*                                           :@@%. .#@@--@@*   +@@* .+%@@@    */
/*   GCD.java                                 =%@@@@@@+ =@@*   =@@+.@@@+-=    */
/*                                              .---:   -@@#.  *@@--@@*       */
/*   By: aperez-b <aperez-b@uoc.edu>                     +@@@@@@@* +@@+       */
/*                                                         :-==:.  -@@#       */
/*   Created: 2023/04/30 18:31:25 by aperez-b                       +@@@%@    */
/*   Updated: 2023/04/30 18:31:26 by aperez-b                                 */
/*                                                                            */
/* ************************************************************************** */

package edu.uoc.pac3;

/**
 * Personal Implementation of the Greatest Common Divisor (GCD)
 * @author aperez-b
 * @version 1.0
 */
public class GCD {
    /**
     * Returns greatest common divisor between two integers
     * @param a first integer
     * @param b second integer
     * @return GCD of a and b
     */
    public static int gcd(int a, int b) {
        int n;

        n = 1;
        for (int i = 1; i <= Math.max(a, b); i++) {
            if ((a % i) == 0 && (b % i) == 0)
                n = i;
        }
        return (n);
    }
}
