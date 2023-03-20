/* ************************************************************************** */
/*                                                                            */
/*                                           =@@*   +@@+                      */
/*                                           =@@*   +@@+ :*%@@@%*:            */
/*                                           =@@*   =@@+.@@@=--%@@-           */
/*                                           :@@%. .#@@--@@*   +@@* .+%@@@    */
/*   PAC1Ex1.java                             =%@@@@@@+ =@@*   =@@+.@@@+-=    */
/*                                              .---:   -@@#.  *@@--@@*       */
/*   By: aperez-b <aperez-b@uoc.edu>                     +@@@@@@@* +@@+       */
/*                                                         :-==:.  -@@#       */
/*   Created: 2023/03/20 17:18:48 by aperez-b                       +@@@%@    */
/*   Updated: 2023/03/20 17:24:08 by aperez-b                                 */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;

public class PAC1Ex1 {

	public static void main(String[] args) {

		int number;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a positive number: ");
		number = scanner.nextInt(); //assigns the variable "number" the integer entered by keyboard

		if(number < 0) {
			System.out.println("[ERROR]: The given number is not positive");
		} else {
			fibonacci(number);
		}
		scanner.close();
	}

	private static void fibonacci(int n) {
		int old;
		int actual = 0;
		int next = 1;
		for (int i = 0; i < n; ++i) {
			System.out.print(actual); //prints the 'i' value of the fibonacci succession
			if (i + 1 != n)
				System.out.print(", ");
			else
				System.out.print("\n");
			old = next;
			next = next + actual;
			actual = old;
		}
	}
}
