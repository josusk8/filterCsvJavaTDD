import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFilter {

    static ArrayList<String[]> arrayListSplit = new ArrayList<>();
    static String[] lines;
    static String entry =
            "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, Cif_Cliente, NIF_Cliente \r\n" +
                    "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134, \r\n" +
                    "2,03/08/2019,2000,2000,,8,MacBook Pro,,78544372A\r\n" +
                    "3,03/12/2019,1000,2000,19,9,Lenovo Laptop,,78544372A\r\n" +
                    "4,02/08/2019,5000,2000,,8,MacBook Pro Plus,CIA99893,78544372A";


    static int dividerJumps() {
        lines = entry.split("\\r\\n");
        return lines.length;
    }
    static ArrayList<String[]> splitLines(){

        for (String line : lines) {
            String[] split = line.split(",");
            arrayListSplit.add(split);
        }
        return arrayListSplit;
    }

    static ArrayList<String[]> splitIgicIva(){

        for (int i =1 ; i < arrayListSplit.size()-1 ; i++) {
            String[] line = arrayListSplit.get(i);
            if (!"" .equals(line[4]) && !"" .equals(line[5])) {
                arrayListSplit.remove(i);
            }
        }
        return arrayListSplit;
    }


    static ArrayList<String[]> getArrayList() {
        dividerJumps();
        splitLines();
        splitIgicIva();

        return arrayListSplit;

    }


}
