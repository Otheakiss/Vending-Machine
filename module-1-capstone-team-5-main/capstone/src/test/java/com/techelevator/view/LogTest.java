package com.techelevator.view;

import com.techelevator.Item;
import com.techelevator.Log;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class LogTest {
    String TEXT_FILE = "C:\\Users\\Student\\workspace\\Mod1Capstone\\module-1-capstone-team-5\\capstone\\Log.txt";
    private File realFile = new File(TEXT_FILE);

    @Before
    public void setUp() throws Exception {
        if(realFile.exists()) {
            realFile.delete();
        }
    }

    @Test
    public void logCreatesAFile() throws FileNotFoundException {
        Log log = new Log();
        assertTrue(realFile.exists());
    }

    @Test
    public void logRecordsMoneyAdded() throws IOException {
        Log log = new Log();
        log.logMoneyAdded(BigDecimal.ZERO, BigDecimal.TEN);
        assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))
                + " FEED MONEY: $0.00 $10.00\n", Files.readString(realFile.toPath()));
    }

    @Test
    public void logRecordsGiveChange() throws IOException {
        Log log = new Log();
        log.logGiveChange(BigDecimal.TEN, BigDecimal.ZERO);
        assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))
                + " GIVE CHANGE: $10.00 $0.00\n", Files.readString(realFile.toPath()));
    }

    @Test
    public void logRecordsItemBought() throws IOException {
        Log log = new Log();
        String s = "A1|Potato Crisps|3.05|Chip";
        Item i = new Item(s);
        log.logItemBought(i, BigDecimal.TEN);
        assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                + " "
                + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))
                + " Potato Crisps A1 $3.05 $10.00\n", Files.readString(realFile.toPath()));
    }
}
