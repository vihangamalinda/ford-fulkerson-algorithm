package com.company;

public class EdgeClass {
    public int startingVertex;
    public int endingVertex;
    public EdgeClass residualValue;
    public long flowing;
    //capacity of a edge is constant throughout the process
    public final long CAPACITY;

    public EdgeClass(int startingVertex, int endingVertex, long CAPACITY) {
        this.startingVertex = startingVertex;
        this.endingVertex = endingVertex;
        this.CAPACITY = CAPACITY;
    }
    /*Only forward edges have a constant capacity
       so if  a edge have a capacity ==0 , means that its a residual edge
     */


    public boolean residualCheck() {
        return CAPACITY == 0;
    }

    //remaining CAPACITY can be gain by subtracting current flowing from the CAPACITY of that edge
    public long remainingCAPACITY() {
        return CAPACITY - flowing;
    }

    public void clash(long bottleNeckValue) {
        flowing += bottleNeckValue;
        residualValue.flowing -= bottleNeckValue;
    }

    public String toString(int s, int t) {
        String u = (startingVertex == s) ? "s" : ((startingVertex == t) ? "t" : String.valueOf(startingVertex));
        String v = (endingVertex == s) ? "s" : ((endingVertex == t) ? "t" : String.valueOf(endingVertex));
        return String.format(
                "Edge %s -> %s | flow = %3d | capacity = %3d | is residual: %s",
                u, v, flowing, CAPACITY, residualCheck());
    }
}
