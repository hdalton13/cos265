import java.util.Iterator;

/**
 * PSBruteForce is a Point collection that provides brute force
 * nearest neighbor searching using red-black tree.
 */
public class PSBruteForce<Value> implements PointSearch<Value> {
    private RedBlackBST<Point, Value> bst;
    private Point min;
    private Point max;


    // constructor makes empty collection
    public PSBruteForce() {
        bst = new RedBlackBST<>();
    }

    // add the given Point to KDTree
    public void put(Point p, Value v) {

        if(p == null || v == null) throw new NullPointerException();
        bst.put(p,v);
        if(bst.size() == 1){
            min = p;
            max = p;
        } else {
            min = Point.min(min, p);
            max = Point.max(max, p);
        }
    }

    public Value get(Point p) {
        if(p == null) throw new NullPointerException();
        if(bst.isEmpty()) return null;
        return bst.get(p);
    }

    public boolean contains(Point p) {
        if(p == null) throw new NullPointerException();
        if(bst.isEmpty()) return false;
        return bst.get(p) != null;
    }

    // return an iterable of all points in collection
    public Iterable<Point> points() {
        if(bst.isEmpty()) return null;
        return bst.keys();
    }

    // return the Point that is closest to the given Point
    public Point nearest(Point p) {
        if(p == null) throw new NullPointerException();
        if(bst.isEmpty()) return null;
        Point nearest = null;
        for(Point current : points()){
            if(nearest == null) {
                nearest = current;
            } else {
                if(p.dist(current) < p.dist(nearest)){
                    nearest = current;
                }
            }
        }
        return nearest;
    }

    // return the Value associated to the Point that is closest to the given Point
    public Value getNearest(Point p) {
        if(p == null) throw new NullPointerException();
        if(bst.isEmpty()) return null;
        return get(nearest(p));
    }

    // return the min and max for all Points in collection.
    // The min-max pair will form a bounding box for all Points.
    // if KDTree is empty, return null.
    public Point min() {
        //can't just use min and max bc looking for wrong thing
        if(bst.isEmpty()) return null;
        return min;
    }
    public Point max() {
        //find the box that includes all data points
        if(bst.isEmpty()) return null;
        return max;
    }

    // return the k nearest Points to the given Point
    public Iterable<Point> nearest(Point p, int k) {
        if(p == null) throw new NullPointerException();
        if(bst.isEmpty()) return null;
        //priority queue?
        MaxPQ<PointDist> nearest = new MaxPQ<>();
        for(Point current : points()) {
            PointDist pd = new PointDist(current, p.dist(current));
            nearest.insert(pd);
            if(nearest.size() > k) {
                nearest.delMax();
            }
        }
        Stack<Point> nearestPoints = new Stack<>();
        for(PointDist pd : nearest) {
            nearestPoints.push(pd.p());
        }
        return nearestPoints;
    }

    public Iterable<Partition> partitions() { return null; }

    // return the number of Points in KDTree
    public int size() { return bst.size(); }

    // return whether the KDTree is empty
    public boolean isEmpty() { return bst.isEmpty(); }

    // place your timing code or unit testing here
    public static void main(String[] args) {
    }
}
