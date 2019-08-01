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

        ArrayList<LineSegment> lineSegment = new ArrayList<LineSegment>();

        for (int i = 0; i < points.length; i++) {

            Point origin = aux[i];
            Point[] slopeArray = aux;
            Arrays.sort(aux, origin.slopeOrder());

            int x = 1;
            while(x < points.length){

                ArrayList<Point> candidatePoints = new ArrayList<Point>();
                double POSSIBLE_SLOPE = origin.slopeTo(slopeArray[x]);

                while(x < points.length && origin.slopeTo(slopeArray[x]) == POSSIBLE_SLOPE){
                    candidatePoints.add(slopeArray[x++]);
                }


                if(candidatePoints.size() >= 3 && origin.compareTo(candidatePoints.get(0)) < 0){

                    Point min = origin;
                    Point max = candidatePoints.remove(candidatePoints.size() - 1);
                    lineSegment.add(new LineSegment(min , max));
                }
            }
        }
        segmentCollection = lineSegment.toArray();
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
