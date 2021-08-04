/******************************
    uow: w1790098
    iit no: 2019495
    name: S.V.M. Fernando
*****************************************
 */
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner myReader = new Scanner(new File("ladder_9.txt"));

        int n = myReader.nextInt();
        System.out.println(" ");
        System.out.println("\nNo. of Vertices : " + n);
        System.out.println(" ");
        int s = 0;
        int t = n - 1;

        long start = System.currentTimeMillis();
        DepthForSearch solver = new DepthForSearch(n, s, t);

        while (myReader.hasNext()) {
            int a = myReader.nextInt(); //getting node source of the edge from the file
            int b = myReader.nextInt(); //getting the terminating node of the edge from the file
            int c = myReader.nextInt(); //getting the CAPCITY of the edge from the file
            solver.addingEdge(a,b,c); //adding them according to the order
            System.out.println("From : " + a + " | To : " + b + " | Capacity : " + c + ""); //print out the edges of the graph

        }
        long now = System.currentTimeMillis();


        System.out.println(" ");
        System.out.printf("Maximum Flow is: %d\n", solver.returnMaxFlow());
        System.out.println(" ");

        List<EdgeClass>[] resultGraph = solver.gettingGraph();


        for (List<EdgeClass> edgeClasses : resultGraph) for (EdgeClass e : edgeClasses) System.out.println(e.toString(s, t));
        double elapsed = (now - start) / 1000.0;
        System.out.println("\nElapsed time = " + elapsed + " seconds");
    }
}
