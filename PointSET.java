import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> pointsCollection;

    public PointSET() {
        pointsCollection = new TreeSet<>();
    }                           // construct an empty set of points

    public boolean isEmpty() {
        return pointsCollection.isEmpty();
    }                  // is the set empty?

    public int size() {
        return pointsCollection.size();
    }// number of points in the set

    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if (p == null) {
            throw new NullPointerException();
        }
        if(!pointsCollection.contains(p)){
            pointsCollection.add(p);
        }
    }

    public boolean contains(Point2D p)            // does the set contain point p?
    {
        if (p == null) {
            throw new NullPointerException();
        }
        return pointsCollection.contains(p);
    }

    public void draw()                         // draw all points to standard draw
    {
    }

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if (rect == null) {
            throw new NullPointerException();
        }
        Point2D minimumPoint = new Point2D(rect.xmin(), rect.ymin());
        Point2D maximumPoint = new Point2D(rect.xmax(), rect.ymax());
        List<Point2D> pointsWithinRectangle = new LinkedList<>();

        for(Point2D p : pointsCollection.subSet(minimumPoint,true, maximumPoint, true)){
            if(p.x() >= rect.xmin() && p.x() <= rect.xmax()){
                pointsWithinRectangle.add(p);
            }
        }
        return pointsWithinRectangle;
    }

    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if (p == null) {
            throw new NullPointerException();
        }
        if(isEmpty()){
            return null;
        }

        Point2D next = pointsCollection.ceiling(p);
        Point2D prev = pointsCollection.floor(p);

        if(next == null && prev == null){
            return null;
        }

        double nextClosest = next == null ? Double.MAX_VALUE : p.distanceTo(next);
        double prevClosest = prev == null ? Double.MAX_VALUE : p.distanceTo(prev);
        double d = Math.min(nextClosest, prevClosest);

        Point2D min = new Point2D(p.x(), p.y()-d);
        Point2D max = new Point2D(p.x(), p.y() + d);
        Point2D nearest = next == null ? prev : next;

        for(Point2D candidate: pointsCollection.subSet(min, true, max, true)){
            if(p.distanceTo(candidate) < p.distanceTo(nearest)){
                nearest = candidate;
            }
        }
        return  nearest;
    }

    public static void main(String[] args) {

    }            // unit testing of the methods (optional)
}
