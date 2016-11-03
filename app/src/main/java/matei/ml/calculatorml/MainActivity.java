package matei.ml.calculatorml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static matei.ml.calculatorml.Utils.log2;

public class MainActivity extends AppCompatActivity {

    private EditText hFractie;
    private EditText hPartitie;
    private EditText hCondit1;
    private EditText hCondit2;

    private TextView resultPartitie;
    private TextView resultFractie;
    private TextView resultCondit;
    private TextView resultIG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hFractie = (EditText) findViewById(R.id.h_fractie);
        hPartitie = (EditText) findViewById(R.id.h_partitie);
        hCondit1 = (EditText) findViewById(R.id.h_conditionala_p1);
        hCondit2 = (EditText) findViewById(R.id.h_conditionala_p2);

        resultFractie = (TextView) findViewById(R.id.result_fractie);
        resultPartitie = (TextView) findViewById(R.id.result_partitie);
        resultCondit = (TextView) findViewById(R.id.result_conditionala);
        resultIG = (TextView) findViewById(R.id.result_ig);
    }

    public void calculateIG(View view) {
        String input1 = hCondit1.getText().toString();
        String input2 = hCondit2.getText().toString();
        Partition partition1 = new Partition(input1);
        Partition partition2 = new Partition(input2);

        Partition parentPartition = new Partition(partition1.getNo1() + partition2.getNo1(),
                partition1.getNo2() + partition2.getNo2());
        resultIG.setText(" " +
                (parentPartition.getEntropy() - performHCondit(partition1, partition2)));
    }

    public void calculateCondit(View view) {
        String input1 = hCondit1.getText().toString();
        String input2 = hCondit2.getText().toString();
        Partition partition1 = new Partition(input1);
        Partition partition2 = new Partition(input2);
        resultCondit.setText(" " + performHCondit(partition1, partition2));
    }

    private double performHCondit(Partition partition1, Partition partition2) {
        double sumPar1 = partition1.getNo1() + partition1.getNo2();
        double sumPar2 = partition2.getNo1() + partition2.getNo2();
        double sum = sumPar1  + sumPar2;
        double res = (sumPar1/sum)*partition1.getEntropy() + (sumPar2/sum)*partition2.getEntropy();
        return res;
    }

    public void calculatePartitie(View view) {
        String input = hPartitie.getText().toString();
        Partition partition = new Partition(input);
        resultPartitie.setText(" " + partition.getEntropy());
    }

    private double calculateHFractie(double numarator, double numitor) {
        double oposite = numitor - numarator;
        double result = (numarator / numitor) * log2(numarator / numitor)
                + (oposite / numitor) * log2(oposite / numitor);
        return -result;
    }

    public void calculateFractie(View view) {
        String input = hFractie.getText().toString();
        if (input.toString().equals("0")) {
            resultFractie.setText("0");
        } else {
            int linie = input.indexOf('/');
            double numarator = Double.parseDouble((input.substring(0, linie)));
            double numitor = Double.parseDouble(input.substring(linie + 1));

            resultFractie.setText(" " + numarator + " " + numitor + " " + calculateHFractie(numarator, numitor));
        }
    }
}
