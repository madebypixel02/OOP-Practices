/* ************************************************************************** */
/*                                                                            */
/*                                           =@@*   +@@+                      */
/*                                           =@@*   +@@+ :*%@@@%*:            */
/*                                           =@@*   =@@+.@@@=--%@@-           */
/*                                           :@@%. .#@@--@@*   +@@* .+%@@@    */
/*   PAC1Ex2.java                             =%@@@@@@+ =@@*   =@@+.@@@+-=    */
/*                                              .---:   -@@#.  *@@--@@*       */
/*   By: aperez-b <aperez-b@uoc.edu>                     +@@@@@@@* +@@+       */
/*                                                         :-==:.  -@@#       */
/*   Created: 2023/03/20 18:53:15 by aperez-b                       +@@@%@    */
/*   Updated: 2023/03/20 18:53:17 by aperez-b                                 */
/*                                                                            */
/* ************************************************************************** */

package edu.uoc.pac1;

public class PAC1Ex2 {

	public static double twoDecimals(double number){
		return Math.round(number * 100d) / 100d;
	}

	public static int incomeTaxRate(double amount){
		if (amount <= 0)
			return (-1);
		if (amount <= 15000)
			return (15);
		if (amount <= 24800)
			return (18);
		return (22);
	}

	public static double totalIncomeTaxVAT(double amount) {
		if (amount <= 0)
			return (0);
		return (twoDecimals(amount - (amount * incomeTaxRate(amount) / 100) + (amount * 0.21)));
	}

	public static double[] invoicesTotal(double[][] invoices) {
		double	[]list;
		double	total;

		list = new double[invoices.length];
		for (int i = 0; i < invoices.length; i++) {
			total = 0;
			for (int j = 0; j < invoices[i].length; j++)
				total += invoices[i][j];
			total = totalIncomeTaxVAT(total);
			System.out.println("Client " + (i + 1) + ": " + total);
			list[i] = total;
		}
		return (list);
	}
}
