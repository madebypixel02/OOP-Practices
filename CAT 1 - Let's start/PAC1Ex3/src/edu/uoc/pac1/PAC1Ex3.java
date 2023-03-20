/* ************************************************************************** */
/*                                                                            */
/*                                           =@@*   +@@+                      */
/*                                           =@@*   +@@+ :*%@@@%*:            */
/*                                           =@@*   =@@+.@@@=--%@@-           */
/*                                           :@@%. .#@@--@@*   +@@* .+%@@@    */
/*   PAC1Ex3.java                             =%@@@@@@+ =@@*   =@@+.@@@+-=    */
/*                                              .---:   -@@#.  *@@--@@*       */
/*   By: aperez-b <aperez-b@uoc.edu>                     +@@@@@@@* +@@+       */
/*                                                         :-==:.  -@@#       */
/*   Created: 2023/03/20 18:53:06 by aperez-b                       +@@@%@    */
/*   Updated: 2023/03/20 19:21:54 by aperez-b                                 */
/*                                                                            */
/* ************************************************************************** */

package edu.uoc.pac1;

public class PAC1Ex3 {

    public static double twoDecimals(double number){
        return Math.round(number * 100d) / 100d;
    }

    static final double[] GASOLINE = {1.49,1.559,1.637,1.93,1.858,2.026,2.121,1.903,1.794,1.687,1.766,1.695};
    static final double[] DIESEL = {1.354,1.44,1.522,1.852,1.892,1.892,2.078,1.894,1.901,1.814,1.975,1.77};

    static final int[][] distanceMatrix = {
            //Barcelona
            {0,13,13,34,24,97,33,108,102,158,159,12,15,17,18},
            //Hospitalet de Llobregat
            {10,0,25,32,29,89,41,99,107,150,151,17,3,9,18},
            //Badalona
            {13,25,0,32,22,118,22,128,98,175,176,5,27,29,23},
            //Terrassa
            {35,32,31,0,10,107,50,118,104,146,148,25,34,36,17},
            //Sabadell
            {27,31,23,11,0,105,38,116,90,155,157,18,33,34,13},
            //Tarragona
            {97,90,117,109,109,0,137,14,190,101,101,111,87,88,97},
            //Mataró
            {33,41,22,49,38,136,0,146,69,185,187,25,42,49,44},
            //Reus
            {108,100,127,119,120,16,147,0,200,93,94,122,98,98,108},
            //Girona
            {103,108,91,104,91,190,69,200,0,226,227,94,109,119,97},
            //Lleida
            {159,151,174,147,157,102,187,92,227,0,2,169,147,146,147},
            //Centre Històric
            {162,154,177,150,160,103,190,93,230,2,0,171,149,149,150},
            //Santa Coloma de Gramenet
            {12,18,5,25,15,111,26,121,93,167,169,0,18,23,16},
            //Cornellà de Llobregat
            {15,3,27,35,36,87,43,98,116,146,147,18,0,5,24},
            //Sant Boi de Llobregat
            {16,7,28,37,38,86,48,97,118,145,146,23,4,0,26},
            //Sant Cugat del Vallès
            {17,18,24,17,14,97,45,107,98,146,147,19,18,26,0},
    };

    public static int calculateTravelledDistance(int[] route) {
		int	total;

		total = 0;
        if (route == null || route.length == 0) {
			System.out.println("[ERROR]: The route is empty");
			return (0);
		}
		for (int i = 0; i < route.length; i++) {
			if (route[i] < 0 || route[i] >= distanceMatrix.length) {
				System.out.println("[ERROR]: The route contains invalid destination points");
				return (-1);
			}
		}
		for (int i = 0; i < route.length - 1; i++) {
			total += distanceMatrix[route[i]][route[i + 1]];
		}
		return (total);
    }

    public static double calculateTravelCostByMonth(int[] route, int month, double litersPer100KM, boolean isGasoline) {
		int	dist;

        if (month < 0 || month > 11) {
			System.out.println("[ERROR]: Invalid month");
			return (-1);
		}
        if (litersPer100KM <= 0) {
			System.out.println("[ERROR]: Invalid litersPer100KM value");
			return (-1);
		}
		dist = calculateTravelledDistance(route);
        if (dist == -1)
			return (-1);
		if (isGasoline)
			return (twoDecimals(dist * litersPer100KM / 100 * GASOLINE[month]));
		return (twoDecimals(dist * litersPer100KM / 100 * DIESEL[month]));
    }

    public static double calculateTravelCosts(int[][] routes, int[] months, double litersPer100KM, boolean isGasoline) {
		double	total;
		double	temp;

		if (routes == null || routes.length == 0)
			return (-1);
		total = 0;
		for (int i = 0; i < routes.length; i++) {
			temp = calculateTravelCostByMonth(routes[i], months[i], litersPer100KM, isGasoline);
			if (temp == -1)
				return (-1);
			total += temp;
		}
		return (twoDecimals(total));
    }

    public static boolean isGasolineCheaper(int[][] routes, int[] months, double litersPer100KMGasoline, double litersPer100KMDiesel) {
        double	gasolineTotal, dieselTotal;

		gasolineTotal = calculateTravelCosts(routes, months, litersPer100KMGasoline, true);
		dieselTotal = calculateTravelCosts(routes, months, litersPer100KMDiesel, false);
		return (gasolineTotal < dieselTotal);
    }
}
