import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFilterShould {
    int lines_num = 4;
    int colum_num = 9;
    String entry =
            "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, Cif_Cliente, NIF_Cliente \r\n" +
                    "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,\r\n" +
                    "2,03/08/2019,2000,2000,,8,MacBook Pro,,78544372A\r\n" +
                    "3,03/12/2019,1000,2000,19,9,Lenovo Laptop,,78544372A";

    @Test
    void num_jumps_lines() {
        assertEquals(lines_num, CsvFilter.dividerJumps());
    }

    @Test
    void get_array_whith_size_of_jumps_lines() {
        assertEquals(lines_num, CsvFilter.getArrayList().size());
    }

    @Test
    void num_colunm() {

        for (int i = 0; i < CsvFilter.getArrayList().size(); i++) {
            assertEquals(colum_num, CsvFilter.getArrayList().get(i).length);
        }
    }

    @Test
    void exclude_lines_whith_igic_and_iva() {

        assertEquals(3, CsvFilter.getArrayList().size());

    }




}
