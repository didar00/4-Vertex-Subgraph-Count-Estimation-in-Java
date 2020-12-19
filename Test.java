import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
     
        UndirectedGraph g = new UndirectedGraph(250);
        Edge[] e = g.sampler();
        System.out.println("((" + e[0].toString() + "), (" 
            + e[1].toString() + "), (" + e[2].toString() + "))");


	}
}
