package dao;

import org.junit.BeforeClass;
import org.junit.Test;
import util.ConnectionManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class VocabularyDaoTest {
    @BeforeClass
    public static void startUp() throws Exception {
        URL url = VocabularyDaoTest.class.getClassLoader().getResource("tables.sql");
        List<String> str = Files.readAllLines(Paths.get(url.toURI()));
        String sql = str.stream().collect(Collectors.joining());
        sql = sql.replaceAll("\t", "");
        System.out.println(sql);
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.executeUpdate();
        }
    }

    @Test
    public void testExample1() {
        System.out.println("Test 1");
    }

    @Test
    public void testExample2() {
        System.out.println("Test 2");
    }

    @Test
    public void testExample3() {
        System.out.println("Test 3");
    }
}