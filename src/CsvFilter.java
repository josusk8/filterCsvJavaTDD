import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFilter {

    static ArrayList<String[]> arrayListSplit = new ArrayList<>();
    static String[] lines;
    static String entry =
            "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, Cif_Cliente, NIF_Cliente \r\n" +
                    "1,02/05/2019,1000,810,19,8,ACER Laptop,B76430134, \r\n" +
                    "2,03/08/2019,2000,2000,9,8,MacBook Pro,,78544372A\r\n" +
                    "3,03/12/2019,1000,2000,19,9,Lenovo Laptop,,78544372A\r\n" +
                    "4,02/08/2019,5000,2000,8,8,MacBook Pro Plus,CIA99893,78544372A\r\n" +
                    "5,02/08/2019,5000,2000,8,8,MacBook Pro Plus,CIA99893,78544372A";


    static int dividerJumps() {
        lines = entry.split("\\r\\n");
        return lines.length;
    }

    static ArrayList<String[]> splitLines() {
        dividerJumps();

        for (String line : lines) {
            String[] split = line.split(",");
            arrayListSplit.add(split);
        }
        return arrayListSplit;
    }

    static ArrayList<String[]> splitIgicIva() {
        splitLines();

        //ArrayList<Integer> remove = new ArrayList<>();
        System.out.println(arrayListSplit.size());
        for (int i = 0; i < arrayListSplit.size() ; i++) {
            String[] line = arrayListSplit.get(i);
            System.out.println("Comparando " + i + "; IVA:" + line[4] + " IGIC: " + line[5]);
            if (!"".equals(line[4]) && !"".equals(line[5])) {
                arrayListSplit.remove(i);
                System.out.println(arrayListSplit.size());
                System.out.println("Eliminando posición; " + i);
            }
        }

/*
        for (int i = 0; i < remove.size(); i++) {
            int delete = remove.get(i);
            System.out.println("Eliminando posición; " + remove.get(i));
            arrayListSplit.remove(delete);
        }*/

        return arrayListSplit;
    }

    static ArrayList<String[]> splitNifAndCif() {
        splitLines();

        ArrayList<Integer> remove = new ArrayList<>();

        for (int i = 1; i < arrayListSplit.size() ; i++) {
            String[] line = arrayListSplit.get(i);
            System.out.println("Comparando " + i + "; CIF:" + line[4] + " DNI: " + line[5]);
            if (!"".equals(line[7]) && !"".equals(line[8])) {
                remove.add(i);
            }
        }

        for (int i = 0; i < remove.size(); i++) {
            int delete = remove.get(i);
            System.out.println("Eliminando posición; " + remove.get(i));
            arrayListSplit.remove(delete);
        }

        return arrayListSplit;
    }

    static ArrayList<String[]> splitNonDecimalOnIgicOrIva() {
        splitLines();

        ArrayList<Integer> remove = new ArrayList<>();
        Pattern pat = Pattern.compile("\\D");

        for (int i = 1; i < arrayListSplit.size(); i++) {
            String[] line = arrayListSplit.get(i);
            System.out.println("Comparando " + i + "; IGIC:" + line[4] + " IVA: " + line[5]);
            Matcher mat= pat.matcher(line[4]);
            Matcher mat2= pat.matcher(line[5]);

            if ( mat.matches() ||  mat2.matches()) {
                remove.add(i);
            }
        }

        for (int i = 0; i < remove.size(); i++) {
            int delete = remove.get(i);
            System.out.println("Eliminando posición; " + remove.get(i));
            arrayListSplit.remove(delete);
        }

        return arrayListSplit;
    }



    static ArrayList<String[]> getArrayList() {

        return arrayListSplit;

    }


}
