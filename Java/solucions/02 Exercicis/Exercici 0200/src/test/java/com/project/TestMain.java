package com.project;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.github.stefanbirkner.systemlambda.SystemLambda;

public class TestMain {

    @Test
    public void testMainOutput() throws Exception {
        Locale defaultLocale = Locale.getDefault(); // Guarda la configuració regional per defecte
        Locale.setDefault(Locale.US); // Estableix la configuració regional a US

        try {
            String text = SystemLambda.tapSystemOut(() -> {
                // Executa el main per a provar la seva sortida
                String[] args = {}; // Passa els arguments necessaris si n'hi ha
                Main.main(args);
            });
            text = text.replace("\r\n", "\n");

            // Comprova que la sortida conté el text esperat
            String expectedOutput = "1\n3\n5\n7\n9\n";
            String diff = TestStringUtils.findFirstDifference(text, expectedOutput);
            assertTrue(diff.compareTo("identical") == 0, 
                "\n>>>>>>>>>> >>>>>>>>>>\n" +
                diff +
                "<<<<<<<<<< <<<<<<<<<<\n");
        } finally {
            Locale.setDefault(defaultLocale); // Restaura la configuració regional per defecte
        }
    }
}