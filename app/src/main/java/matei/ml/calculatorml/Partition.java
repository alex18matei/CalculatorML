package matei.ml.calculatorml;

import android.util.Log;

public class Partition {

    private String input;
    private double no1;
    private double no2;
    private double entropy;

    public Partition(String input) {
        this.input = input;
        int linie = input.indexOf(' ');
        no1 = Double.parseDouble(input.substring(0, linie));
        no2 = Double.parseDouble(input.substring(linie + 1));
        entropy = performHPartitie(no1, no2);
        Log.e("Partitie " + input, " " + entropy);
    }

    public Partition(double a, double b) {
        no1 = a;
        no2 = b;
        Log.e("Parent "," " + no1 +  " " + no2);
        entropy = performHPartitie(no1, no2);
        Log.e("Parent "," " + entropy);
    }

    private double performHPartitie(double no1, double no2) {
        if( no1 == 0 || no2 == 0){
            return 0;
        }
        double sum = no1 + no2;
        double result = (no1 / sum) * Utils.log2(no1 / sum)
                + (no2 / sum) * Utils.log2(no2 / sum);
        return -result;
    }

    public double getEntropy() {
        return entropy;
    }

    public double getNo1() {
        return no1;
    }

    public double getNo2() {
        return no2;
    }
}
