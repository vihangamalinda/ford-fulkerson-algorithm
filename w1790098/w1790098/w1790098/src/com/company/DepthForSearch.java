package com.company;

import java.util.List;

import static java.lang.Math.min;
//Ford-Fulkerson with Depth First Search Algorithm (DFS)
public class DepthForSearch extends NetworkFlowInitializer {


    public DepthForSearch(int node, int source, int terminator) {
        super(node, source, terminator);
    }


    @Override
    public void solver() {
        //Finding the max flow by adding all the augmenting paths
        for (long x = dfs(source, overFlowCapture); x != 0; x = dfs(source, overFlowCapture)) {
            visitedTag++;
            maximumFlow += x;
        }
    }

    private long dfs(int n, long f) {
        //return agumented path flow at the sink node
        if (n == terminator) return f;

        // Mark the current node as visited.
        visitedList[n] = visitedTag;

        List<EdgeClass> eAll = presenter[n];
        for (EdgeClass e : eAll) {
            if (e.remainingCAPACITY() > 0 && visitedList[e.endingVertex] != visitedTag) {
                long bottleNeck = dfs(e.endingVertex, min(f, e.remainingCAPACITY()));


                if (bottleNeck > 0) {
                    e.clash(bottleNeck);
                    return bottleNeck;
                }
            }
        }
        return 0;
    }
}
