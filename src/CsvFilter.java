import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFilter {

    static ArrayList<String[]> arrayListSplit = new ArrayList<>();
    static String[] lines;
    static String entry =
            "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, Cif_Cliente, NIF_Cliente \r\n" +
                    "1,02/05/2019,1000,810,19,8,ACER Laptop,B76430134, \r\n" +
                    "2,03/08/2019,2000,2000,,8,MacBook Pro,,78544372A\r\n" +
                    "3,03/12/2019,1000,2000,19,,Lenovo Laptop,,78544372A\r\n" +
                    "4,02/08/2019,5000,2000,8,8,MacBook Pro Plus,CIA99893,78544372A\r\n" +
                    "5,02/08/2019,5000,2000,,A,MacBook Pro Plus,CIA99893,78544372A";


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

        //Delete white space
        Pattern pat = Pattern.compile("\\s");
        for (String[] line : arrayListSplit) {
            for (int i = 0; i < line.length; i++) {
                if (line[i].matches(pat.pattern())) {
                    line[i] = "";
                }
            }

        }


        return arrayListSplit;
    }

    static ArrayList<String[]> splitIgicIva() {
        splitLines();

        ArrayList<Integer> removeList = new ArrayList<>();


        for (int i = 1; i < arrayListSplit.size(); i++) {
            String[] line = arrayListSplit.get(i);
            System.out.println("Comparando Num_Fact " + line[0] + "; IVA:" + line[4] + " IGIC: " + line[5]);
            if (!"".equals(line[4]) && !"".equals(line[5])) {
                removeList.add(i);
            }
        }

        Collections.sort(removeList, Collections.reverseOrder());
        int countDelete = removeList.size();

        for (int i = 0; i < countDelete; i++) {

            int delete = removeList.get(0);
            removeList.remove(0);
            String[] line = arrayListSplit.get(delete);
            System.out.println("Eliminando Num_Fact " + line[0] + ";");
            arrayListSplit.remove(delete);

        }

        return arrayListSplit;
    }

    static ArrayList<String[]> splitNifAndCif() {
        splitLines();

        ArrayList<Integer> removeList = new ArrayList<>();

        for (int i = 1; i < arrayListSplit.size(); i++) {
            String[] line = arrayListSplit.get(i);
            System.out.println("Comparando " + i + "; CIF: " + line[7] + " DNI: " + line[8]);
            if (!"".equals(line[7]) && !"".equals(line[8])) {
                removeList.add(i);
            }
        }

        Collections.sort(removeList, Collections.reverseOrder());
        int countDelete = removeList.size();

        for (int i = 0; i < countDelete; i++) {
            int delete = removeList.get(0);
            removeList.remove(0);
            String[] line = arrayListSplit.get(delete);
            System.out.println("Eliminando Num_Fact " + line[0] + ";");
            arrayListSplit.remove(delete);

        }

        return arrayListSplit;
    }

    static ArrayList<String[]> splitNonDecimalOnIgicOrIva() {
        splitLines();

        ArrayList<Integer> removeList = new ArrayList<>();
        Pattern pat = Pattern.compile("\\D");

        for (int i = 1; i < arrayListSplit.size(); i++) {
            String[] line = arrayListSplit.get(i);
            System.out.println("Comparando Num_Fact " + i + "; IGIC:" + line[4] + " IVA: " + line[5]);
            Matcher mat = pat.matcher(line[4]);
            Matcher mat2 = pat.matcher(line[5]);

            if (mat.matches() || mat2.matches()) {
                removeList.add(i);
            }
        }

        Collections.sort(removeList, Collections.reverseOrder());
        int countDelete = removeList.size();

        for (int i = 0; i < countDelete; i++) {
            int delete = removeList.get(0);
            removeList.remove(0);
            String[] line = arrayListSplit.get(delete);
            System.out.println("Eliminando Num_Fact " + line[0] + ";");
            arrayListSplit.remove(delete);

        }

        return arrayListSplit;
    }


    static ArrayList<String[]> getArrayList() {

        return arrayListSplit;

    }


}
