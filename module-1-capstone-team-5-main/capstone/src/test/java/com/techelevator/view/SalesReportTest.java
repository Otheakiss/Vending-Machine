package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.Item;
import com.techelevator.SalesReport;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class SalesReportTest {

    String TEXT_FILE = "C:\\Users\\Student\\workspace\\Mod1Capstone\\module-1-capstone-team-5\\capstone\\"
            + LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
            + "-" + LocalTime.now().format(DateTimeFormatter.ofPattern("hh-mm"))
            + "-sales.txt";
    private File realFile = new File(TEXT_FILE);

    @Before
    public void setUp() throws Exception {
        if(realFile.exists()) {
            realFile.delete();
        }
    }

    @Test
    public void logCreatesAFile() throws FileNotFoundException {
        SalesReport report = new SalesReport();
        assertTrue(realFile.exists());
    }

    @Test
    public void logsAllSalesAndProfit() throws IOException {
        Item a1 = new Item("A1|Potato Crisps|1.00|Chip");
        Item a2 = new Item("A2|Spicy Taco|9.05|Chip");
        Item a3 = new Item("A3|CANDY|1.00|Chip");
        List<Item> list = List.of(a1, a2, a3);
        Inventory inventory = new Inventory(list);

        inventory.dispense(inventory.getItemByLocation("A1"));
        inventory.dispense(inventory.getItemByLocation("A3"));

        SalesReport report = new SalesReport();
        report.reportOnInventory(inventory);

        String output = "Potato Crisps|1\nSpicy Taco|0\nCANDY|1\n\n**TOTAL SALES** $2.00";
        String fileString = Files.readString(realFile.toPath());
        assertEquals(output, fileString);
    }

}
