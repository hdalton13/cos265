import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * Pathfinder uses A* search to find a near optimal path
 * between to locations with given terrain.
 */

public class Pathfinder {
    //global vars
    Coord startLoc;
    Coord endLoc;
    float heuristic;
    Terrain t;
    Stack<PFNode> winningPath = new Stack<>();
    Stack<Coord> coordsWin = new Stack<>();
    PFNode[][] board;
    int count = 1 ;
    boolean isPath = false;
    PFNode totalCost;

    /**
     * PFNode will be the key for MinPQ (used in computePath())
     */
    private class PFNode implements Comparable<PFNode> {
        Coord currentLoc;
        PFNode previousLoc;
        float cost;

        // loc: the location of the PFNode
        // fromNode: how did we get here? (linked list back to start)
        public PFNode(Coord loc, PFNode fromNode) {
            currentLoc = loc;
            previousLoc = fromNode;
            this.setCost(heuristic);
        }

        // compares this with that, used to find minimum cost PFNode
        public int compareTo(PFNode that) {
            float thisCost = this.getCost(heuristic);
            float thatCost = that.getCost(heuristic);
            if(thisCost < thatCost) return -1;
            if(thisCost > thatCost) return 1;
            else return 0;


        }

        private void setCost(float heuristic) {
            //keep track of cost in PFNode, return that variable
            if (this.previousLoc == null) this.cost = 0;
            else if(this.currentLoc.isInBounds(0,0,t.getN()-1,t.getN()-1)){
                this.cost = this.previousLoc.cost + t.computeTravelCost(this.currentLoc, this.previousLoc.currentLoc);
            }
        }

        // returns the cost to travel from starting point to this
        // via the fromNode chain
        public float getCost(float heuristic) {

            float hCost = heuristic * t.computeDistance(this.currentLoc.getI(),this.currentLoc.getJ(),endLoc.getI(), endLoc.getJ());
            return this.cost + hCost;

        }

        // returns if this PFNode is still valid
        public boolean isValid() {

            return this != null;
        }

        // invalidates the PFNode
        public void invalidate() {
            //do we just make the PFNode null?
            //take out of priority queue wo doing anything w/ the value?
        }

        // returns if the PFNode has been used
        public boolean isUsed() {

            return true;
        }

        // uses the PFNode
        public void use(Coord loc) {
            //pop from priority q, assign PFNode to new position?
            board[loc.getI()][loc.getJ()] = this;
        }

        public Stack<PFNode> neighbors() {

            Stack<PFNode> s = new Stack<>();

            PFNode north = new PFNode(currentLoc.add(-1,0),this);
            PFNode east = new PFNode(currentLoc.add(0,1),this);
            PFNode south = new PFNode(currentLoc.add(1,0),this);
            PFNode west = new PFNode(currentLoc.add(0,-1),this);

            //could check to make sure if it is in bounds  OR  valid
            if((north.currentLoc.getJ()>=0 && north.currentLoc.getJ()<t.getN() )
                    && (north.currentLoc.getI()>=0 && north.currentLoc.getI()<t.getN()) ) {
                    if (board[north.currentLoc.getI()][north.currentLoc.getJ()] == null ) {
                        use(north.currentLoc);
                        s.push(north);
                        count++;
                    } else if(north.getCost(heuristic) < board[north.currentLoc.getI()][north.currentLoc.getJ()].getCost(heuristic)) {
                        use(north.currentLoc);
                        s.push(north);
                    }
            }
            if((east.currentLoc.getJ()>=0 && east.currentLoc.getJ()<t.getN() )
                    &&(east.currentLoc.getI()>=0 && east.currentLoc.getI()<t.getN()) ) {
                    if ( board[east.currentLoc.getI()][east.currentLoc.getJ()] == null ) {
                        use(east.currentLoc);
                        s.push(east);
                        count++;
                    } else if( east.getCost(heuristic) < board[east.currentLoc.getI()][east.currentLoc.getJ()].getCost(heuristic)) {
                        use(east.currentLoc);
                        s.push(east);
                    }

            }
            if((south.currentLoc.getJ()>=0 && south.currentLoc.getJ()<t.getN() )
                    &&(south.currentLoc.getI()>=0 && south.currentLoc.getI()<t.getN()) ) {
                    if (board[south.currentLoc.getI()][south.currentLoc.getJ()] == null ){
                        use(south.currentLoc);
                        s.push(south);
                        count++;
                    }
                    else if(south.getCost(heuristic) < board[south.currentLoc.getI()][south.currentLoc.getJ()].getCost(heuristic)) {
                        use(south.currentLoc);
                        s.push(south);
                    }

            }
            if((west.currentLoc.getJ()>=0 && west.currentLoc.getJ()<t.getN() )
                    &&(west.currentLoc.getI()>=0 && west.currentLoc.getI()<t.getN()) ) {
                    if (board[west.currentLoc.getI()][west.currentLoc.getJ()] == null ){
                        use(west.currentLoc);
                        s.push(west);
                        count++;
                    }
                    else if(west.getCost(heuristic) < board[west.currentLoc.getI()][west.currentLoc.getJ()].getCost(heuristic)) {
                        use(west.currentLoc);
                        s.push(west);
                    }
            }
            return s;
        }
    }//end of PFNode

    public Pathfinder(Terrain terrain) {
        t = terrain;
        board = new PFNode[t.getN()][t.getN()];
    }

    public void setPathStart(Coord loc) {
        if(!loc.isInBounds(0,0,t.getN()-1,t.getN()-1)){ throw new IndexOutOfBoundsException(); }
        if( loc == null ) throw new IllegalArgumentException();
        startLoc = loc;
    }

    public Coord getPathStart() {
        return startLoc;
    }

    public void setPathEnd(Coord loc) {
        endLoc = loc;
    }

    public Coord getPathEnd() {
        return endLoc;
    }

    public void setHeuristic(float v) {
        heuristic = v;
    }

    public float getHeuristic() {
        return heuristic;
    }

    public void resetPath() {
        board = new PFNode[t.getN()][t.getN()];
        isPath = false;
        count =1;
        winningPath = new Stack<>();
        coordsWin = new Stack<>();
    }

    public void computePath() {

        MinPQ<PFNode> priQ = new MinPQ<>();

        //create the first PFNode
        PFNode firstPQ = new PFNode(startLoc,null);
        board[firstPQ.currentLoc.getI()][firstPQ.currentLoc.getJ()] = firstPQ;

        //put start on pq, then goes into loop
        priQ.insert(firstPQ);

        PFNode lastOnQ = priQ.delMin();

        boolean keepChecking = true;
        while(keepChecking){
            Iterable<PFNode> s = lastOnQ.neighbors();
            for(PFNode x: s){
                priQ.insert(x);
            }

            if(lastOnQ.currentLoc.equals(getPathEnd())) {
                isPath = true;
                totalCost = lastOnQ;
                break;
            }

            lastOnQ = priQ.delMin();
        }//while end

        while(lastOnQ != null)
        {
            winningPath.push(lastOnQ);
            coordsWin.push(lastOnQ.currentLoc);
            lastOnQ = lastOnQ.previousLoc;
        }

    }//end compute path

    public boolean foundPath() {
        return isPath;
    }

    public float getPathCost() { return totalCost.getCost(0);  }

    public int getSearchSize() {
        return count;
    }

    public Iterable<Coord> getPathSolution() {
        return coordsWin;
    }

    public boolean wasSearched(Coord loc) {
        if(board[loc.getI()][loc.getJ()] != null) return true;
        else return false;
    }
}//end of pathfinder



