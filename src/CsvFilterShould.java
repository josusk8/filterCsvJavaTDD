import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFilterShould {
    int lines_num = 6;
    int colum_num = 9;


    @Test
    void num_jumps_lines() {
        assertEquals(lines_num, CsvFilter.dividerJumps());
    }

    @Test
    void num_colunm() {

        for (int i = 0; i < CsvFilter.getArrayList().size(); i++) {
            assertEquals(colum_num, CsvFilter.splitLines().get(i).length);
        }
    }

    @Test
    void exclude_lines_whith_igic_and_iva() {

        assertEquals(4, CsvFilter.splitIgicIva().size());

    }

    @Test
    void exclude_lines_whith_nif_and_cif() {

        assertEquals(4, CsvFilter.splitNifAndCif().size());

    }

    @Test
    void exclude_lines_whith_igic_or_iva_non_decimal() {

        assertEquals(5, CsvFilter.splitNonDecimalOnIgicOrIva().size());

    }




}
