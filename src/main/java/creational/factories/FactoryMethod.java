package creational.factories;//enum CoordinateSystem {
//    CARTESIAN,
//    POLAR
//}


class Point {
    private double x, y;


//    /**
//     * @param a  is x if cartesian or rho if polar
//     * @param b
//     * @param cs
//     */
//    private Point(double a, double b, CoordinateSystem cs) {
//        switch (cs) {
//
//            case CARTESIAN:
//                this.x = x;
//                this.y = y;
//                break;
//            case POLAR:
//                x = a * Math.cos(b);
//                y = a * Math.sin(b);
//                break;
//        }
//    }


    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point newCartesianPoint(double x, double y) {
        return new Point(x, y);
    }

    public static Point newPolarPoint(double rho, double theta) {
        return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
    }


}

class FactoryMethod {

    public static void main(String[] args) {
        Point polarPoint = Point.newPolarPoint(2, 3);
        Point cartesianPoint = Point.newCartesianPoint(1, 2);
    }
}