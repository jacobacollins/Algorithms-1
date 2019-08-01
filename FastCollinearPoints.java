/******************************************************************************
 *  Name: Jacob Collins
 *  Date: 7-31-19
 *  Description: Fast sorting based solution to find Collinear Points
 *****************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segmentCollection;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        if (duplicatePoints(points)) throw new IllegalArgumentException();
        if (checkNullPoints(points)) throw new IllegalArgumentException();

        ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
        Point[] aux = points;
        Arrays.sort(aux);

        for (Point i : points) {

        }

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
}
