
public class RealMatrix {
    public static RealMatrix createRealMatrix(double[][] data) {
        return new Array2DRowRealMatrix(data);
    }

    private static class Array2DRowRealMatrix extends RealMatrix {

        public Array2DRowRealMatrix(double[][] data) {
        }
    }
}
