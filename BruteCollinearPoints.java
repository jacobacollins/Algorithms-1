import java.util.ArrayList;
import java.util.Arrays;

/******************************************************************************
 *  Name: Jacob Collins
 *  Date:7-31-19
 *  Description: Brute Force Solution to the Search for Collinear Points
 *****************************************************************************/

public class BruteCollinearPoints {
    // finds all line segments containing 4 points

    private LineSegment[] segmentCollection;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        if (duplicatePoints(points)) throw new IllegalArgumentException();
        if (checkNullPoints(points)) throw new IllegalArgumentException();

        ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
        Point[] aux = points;
        Arrays.sort(aux);

        for (int i = 0; i < (aux.length - 3); ++i) {
            for (int j = i + 1; j < (aux.length - 2); ++j) {
                for (int k = j + 1; k < (aux.length - 1); ++k) {
                    for (int l = k + 1; l < aux.length; ++l) {
                        if (aux[i].slopeTo(aux[j]) == aux[i].slopeTo(aux[l])
                                && aux[i].slopeTo(aux[j]) == aux[i].slopeTo(aux[k])) {

                            LineSegment tempSegment = new LineSegment(aux[i], aux[l]);

                            if (!segmentList.contains(tempSegment)) segmentList.add(tempSegment);
                        }

                    }
                }
            }
        }
        segmentCollection = segmentList.toArray(new LineSegment[segmentList.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segmentCollection.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segmentCollection;
    }

    public boolean duplicatePoints(Point[] pointCollection) {
        for (int i = 0; i < pointCollection.length; i++) {
            for (int j = i + 1; j < pointCollection.length; j++) {
                if (pointCollection[i].compareTo(pointCollection[j]) == 0) return true;
            }
        }
        return false;
    }

    private boolean checkNullPoints(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // read the n points from a file
        // In in = new In(args[0]);
        // int n = in.readInt();
        // Point[] points = new Point[n];
        // for (int i = 0; i < n; i++) {
        //     int x = in.readInt();
        //     int y = in.readInt();
        //     points[i] = new Point(x, y);
        // }
        //
        // // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // for (Point p : points) {
        //     p.draw();
        // }
        // StdDraw.show();
        //
        // // print and draw the line segments
        // BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        // for (LineSegment segment : collinear.segments()) {
        //     StdOut.println(segment);
        //     segment.draw();
        // }
        // StdDraw.show();
    }
}
