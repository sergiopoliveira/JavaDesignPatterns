package creational.factories;//enum CoordinateSystem {
//    CARTESIAN,
//    POLAR
//}


class PointF {
    private double x, y;

    private PointF(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory {
        public static PointF newCartesianPoint(double x, double y) {

            return new PointF(x, y);
        }

        public static PointF newPolarPoint(double rho, double theta) {
            return new PointF(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }
}

class Factory {

    public static void main(String[] args) {
        PointF polarPoint = PointF.Factory.newPolarPoint(2, 3);
        PointF cartesianPoint = PointF.Factory.newCartesianPoint(1, 2);
    }
}