import java.util.Iterator;

/**
 * PSKDTree is a Point collection that provides nearest neighbor searching using
 * 2d tree
 */
public class PSKDTree<Value> implements PointSearch<Value> {
    private Node root;
    private Point min;
    private Point max;
    int size = 0;
    Queue<Point> pointsQueue = new Queue<>();
    Node nearestNode = new Node();
    double nearDist;
    MaxPQ<PointDist> nearestQ = new MaxPQ<>();

    private class Node {
        Point p;
        Value v;
        Node left, right;
        Partition.Direction dir;

    }

    // constructor makes empty kD-tree
    public PSKDTree() {
        root = new Node();
    }

    // add the given Point to kD-tree
    public void put(Point p, Value v) {
        //recursion in helper function
        //base case: null or ==
            //if null, make new
            //if == update value

        if(p == null || v == null ) throw new NullPointerException();
        if(root.p == null) {
            root.p = p;
            root.v = v;
            root.dir = Partition.Direction.LEFTRIGHT;
            min = p;
            max = p;
        }
        else {
            min = Point.min(min, p);
            max = Point.max(max, p);
            putOnExisting(p,v);
        }
        size++;
        pointsQueue.enqueue(p);
    }

    private void putOnExisting(Point p, Value v){
        Node current = root;
        boolean keepsearching = true;
        while( keepsearching ){
            if(current.p == null) return;
            if(p.equals(current.p) ) {
                current.v = v;
                return;
            }
            //handling the first left
            if(current.dir == Partition.Direction.LEFTRIGHT){
                //where do we put == ?
                // >= or <= ?
                if(p.x() <= current.p.x() ) {
                    if(current.left == null) {
                        current.left = new Node();
                        current.left.p = p;
                        current.left.v = v;
                        current.left.dir = Partition.nextDirection(current.dir);
                        keepsearching = false;
                    } else current = current.left;
                } else if(p.x() > current.p.x() ) {
                    if( current.right == null ) {
                        current.right = new Node();
                        current.right.p = p;
                        current.right.v = v;
                        current.right.dir = Partition.nextDirection(current.dir);
                        keepsearching = false;
                    } else current = current.right;
                }
            } else {
                if(p.y() <= current.p.y() ) {
                    if(current.left == null) {
                        current.left = new Node();
                        current.left.p = p;
                        current.left.v = v;
                        current.left.dir = Partition.nextDirection(current.dir);
                        keepsearching = false;
                    } else current = current.left;
                } else if(p.y() > current.p.y() ) {
                    if( current.right == null ) {
                        current.right = new Node();
                        current.right.p = p;
                        current.right.v = v;
                        current.right.dir = Partition.nextDirection(current.dir);
                        keepsearching = false;
                    } else current = current.right; }
            }
        }//end of while
    }

    public Value get(Point p) {
        if(p == null) throw new NullPointerException();
        if(isEmpty()) return null;
        Node current = root;
        Partition.Direction direction = current.dir;

        while( current != null ){
            if(current.p == p) return current.v;
            if(direction == Partition.Direction.LEFTRIGHT){
                if(current.p.x() < p.x() ) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            } else {
                if(current.p.y() < p.y() ) {current = current.right; }
                else {current = current.left; }
            }
            direction = Partition.nextDirection(direction);
        }
        return null;
    }

    public boolean contains(Point p) {
        if(p == null) throw new NullPointerException();
        if(isEmpty()) return false;
        return get(p) != null;
    }

    public Value getNearest(Point p) {
        if(p == null) throw new NullPointerException();
        if(isEmpty()) return null;
        return nearestNode.v;
    }

    // return an iterable of all points in collection
    public Iterable<Point> points() {
        if(isEmpty()) return null;
        return pointsQueue;
    }

    // return an iterable of all partitions that make up the kD-tree
    public Iterable<Partition> partitions() {
        if(isEmpty()) return null;
        return null;
    }

    // return the Point that is closest to the given Point
    public Point nearest(Point p) {
        //recursively, factor in which side will be checked
        if(p == null) throw new NullPointerException();
        if(isEmpty()) return null;
        nearestNode = root;
        nearDist = nearestNode.p.dist(p);

        if(nearestNode.p.x() > p.x()) {
            nearestR(nearestNode.left, p);
            nearestR(nearestNode.right, p);
        } else {
            nearestR(nearestNode.right, p);
            nearestR(nearestNode.left, p);
        }
        return nearestNode.p;
    }

    private void nearestR(Node current, Point p){
        if(current == null) return;
        double testDist = current.p.dist(p);

        if(testDist > nearDist) {
            if (current.dir == Partition.Direction.DOWNUP) {
                if (current.p.y() > p.y()) {
                    nearestR(current.left, p);
                    if(p.dist(p.x(),current.p.y()) < testDist) {
                        nearestR(current.right, p);
                    }
                } else {
                    nearestR(current.right, p);
                    if(p.dist(p.x(),current.p.y()) < testDist) {
                        nearestR(current.left, p);
                    }
                }
            } else {
                if (current.p.x() > p.x()) {
                    nearestR(current.left, p);
                    if(p.dist(current.p.x(),p.y()) < testDist) {
                        nearestR(current.right, p);
                    }

                } else {
                    nearestR(current.right, p);
                    if(p.dist(current.p.x(),p.y()) < testDist) {
                        nearestR(current.left, p);
                    }
                }
            }
        } else {
            nearDist = testDist;
            nearestNode = current;

            nearestR(current.left,p);
            nearestR(current.right,p);
        }
        return;
    }

    // return the k nearest Points to the given Point
    public Iterable<Point> nearest(Point p, int k) {
        if(p == null) throw new NullPointerException();
//        if(isEmpty()) return null;
        Stack<Point> nearestPoints = new Stack<>();
        if(isEmpty()) return nearestPoints;
        nearestQ = new MaxPQ<>();
/*        for(Point current : points()) {
            PointDist pd = new PointDist(current, p.dist(current));
            nearestQ.insert(pd);
            if(nearestQ.size() > k) {
                nearestQ.delMax();
            }
        }

        for( int i = 0; i < k; i++ ){
            if( !nearestQ.isEmpty()) {
                nearestPoints.push(nearestQ.delMax().p());
            }
        }*/

        if(k != 0) {
            PointDist pointd = new PointDist(root.p, p.dist(root.p));
            nearestQ.insert(pointd);

            nearestKR(root, p, k);
        }

        for(PointDist pd : nearestQ) {
            nearestPoints.push(pd.p());
        }
        return nearestPoints;

    }

    private void nearestKR(Node current, Point p, int k){
        if(current == null) return;
        if(k==0 ) return;
        double testDist = current.p.dist(p);
        double maxDist = 0;

        PointDist pointd = new PointDist(current.p, p.dist(current.p));
        nearestQ.insert(pointd);
        if( nearestQ.size() > k && !nearestQ.isEmpty()) {
            nearestQ.delMax();
        }

        if(!nearestQ.isEmpty()){
             maxDist = nearestQ.max().d();
        }

        if(testDist > maxDist) {
            if (current.dir == Partition.Direction.DOWNUP) {
                if (current.p.y() > p.y()) {
                    nearestKR(current.left, p, k);
                    if(p.dist(p.x(),current.p.y()) < maxDist || nearestQ.size() < k) {
                        nearestKR(current.right, p, k);
                    }
                } else {
                    nearestKR(current.right, p, k);
                    if(p.dist(p.x(),current.p.y()) < maxDist || nearestQ.size() < k) {
                        nearestKR(current.left, p, k);
                    }
                }
            } else {
                if (current.p.x() > p.x()) {
                    nearestKR(current.left, p, k);
                    if(p.dist(current.p.x(),p.y()) < maxDist || nearestQ.size() < k) {
                        nearestKR(current.right, p, k);
                    }
                } else {
                    nearestKR(current.right, p, k);
                    if(p.dist(current.p.x(),p.y()) < maxDist || nearestQ.size() < k) {
                        nearestKR(current.left, p, k);
                    }
                }
            }
        } else {
            nearestKR(current.left, p, k);
            nearestKR(current.right, p, k);
        }
        return;
    }


    // return the min and max for all Points in collection.
    // The min-max pair will form a bounding box for all Points.
    // if kD-tree is empty, return null.
    public Point min() {
        if(isEmpty()) return null;
        return min;
    }
    public Point max() {
        if(isEmpty()) return null;
        return max;
    }

    // return the number of Points in kD-tree
    public int size() { return size; }

    // return whether the kD-tree is empty
    public boolean isEmpty() { return size==0; }

    // place your timing code or unit testing here
    public static void main(String[] args) {
        //not really able to make own tests, but possible

    }

}
