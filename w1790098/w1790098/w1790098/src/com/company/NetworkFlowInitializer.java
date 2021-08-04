package com.company;

import java.util.ArrayList;
import java.util.List;

public abstract class NetworkFlowInitializer {
    //this has been initialize to avoid the overflowing in the system
    static final long overFlowCapture = Long.MAX_VALUE / 2;


    /*main 3 inputs
     *no of nodes
     * flow originating node
     * flow terminating node
     */
    final int node;
    final int source;
    final int terminator;


    protected int visitedTag = 1;
    protected int[] visitedList;

    /*this has initialized to varify whether the algorithm has been run.
     *algorithm always gives the same output
     * therefore its not useful to run the process again
     */
    protected boolean ranAlready;


    protected long maximumFlow;

    //initialized an adjacency list for graph
    protected List<EdgeClass>[] presenter;


    public NetworkFlowInitializer(int node, int source, int terminator) {
        this.node = node;
        this.source = source;
        this.terminator = terminator;
        emptyFlowGraph();
        visitedList = new int[node];
    }


    @SuppressWarnings("unchecked")
    private void emptyFlowGraph() {
        presenter = new List[node];
        for (int i = 0; i < node; i++) presenter[i] = new ArrayList<EdgeClass>();
    }


    public void addingEdge(int startingVertex, int endingVertex, long CAPACITY) {
        if (CAPACITY <= 0) throw new IllegalArgumentException("Forward edge capacity is greater than or equal to zero");
        EdgeClass edge1 = new EdgeClass(startingVertex, endingVertex, CAPACITY);
        EdgeClass edge2 = new EdgeClass(endingVertex, startingVertex, 0);
        edge1.residualValue = edge2;
        edge2.residualValue = edge1;
        presenter[startingVertex].add(edge1);
        presenter[endingVertex].add(edge2);
    }

    /*Initializing a list so that we can figure out the edges we have ben used in the flow grap
           this will helps in degugging to clarify the edges we had been used
        */
    public List<EdgeClass>[] gettingGraph() {
        executeChecker();
        return presenter;
    }


    public long returnMaxFlow() {
        executeChecker();
        return maximumFlow;
    }

    // this method has been initialize to ensure that this we only call the solver method once
    private void executeChecker() {
        if (ranAlready) return;
        ranAlready = true;
        solver();
    }

    // Method to implement which solves the network flow problem.
    public abstract void solver();
}
