import java.nio.charset.CoderResult;
import java.util.Iterator;

/**
 * Walker takes an Iterable of Coords and simulates an individual
 * walking along the path over the given Terrain
 */
public class Walker {
    Terrain t;
    Queue<Coord> routeQ = new Queue<>();
    Coord currentCoord;
    float cumulativeTime=0;
    float timeNeeded;

    // terrain: the Terrain the Walker traverses
    // path: the sequence of Coords the Walker follows
    public Walker(Terrain terrain, Iterable<Coord> path) {
        t = terrain;

        for(Coord x: path){
            routeQ.enqueue(x);
        }
        currentCoord = routeQ.dequeue();

    }

    // returns the Walker's current location
    public Coord getLocation() {
        return currentCoord;
    }

    // returns true if Walker has reached the end Coord (last in path)
    public boolean doneWalking() {
        return routeQ.size() == 1;
    }

    public void advance(float byTime) {
        timeNeeded = t.computeTravelCost(currentCoord, routeQ.peek());
        cumulativeTime +=  byTime;
        while(cumulativeTime >= timeNeeded && !doneWalking()) {
            currentCoord = routeQ.dequeue();
            cumulativeTime -= timeNeeded;
            timeNeeded = t.computeTravelCost(currentCoord, routeQ.peek());
        }
    }
}//end of walker
