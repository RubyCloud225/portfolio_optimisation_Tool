import java.util.ArrayList;
import java.util.List;

public class AssetReturnCalculator {
    public void calculateAssetReturns(FinancialDataLoader loader, String fxData, String bonds, String stocks) {

        // Calculate asset returns
        List<Double> stockReturns = calculateStockReturns(stocks);
        List<Double> bondReturns = calculateBondReturns(bonds);
        List<Double> fxReturns = calculateReturns(fxData);

        // Calculate matrix convariance
        RealMatrix convarianceMatrix = calculateCovarianceMatrix(stockReturns, bondReturns, fxReturns);

        // Print or store the results
        System.out.println("Asset Returns:");
        System.out.println("Stocks: " + stockReturns);
        System.out.println("Bonds: " + bondReturns);
        System.out.println("FX: " + fxReturns);

        System.out.println("Covariance Matrix: ");
        System.out.println(convarianceMatrix);
    }

    private List<Double> calculateStockReturns(String stocks) {
        List<Double> stockreturns = new ArrayList<>();
        String[] stockSymbols = stocks.split(",");
        // need a method to get a return for single stock
        for (String symbol : stockSymbols) {
            double returnForStock = getReturnForStock(symbol);
            stockreturns.add(returnForStock);
        }
        return stockreturns;
    }

    private double getReturnForStock(String symbol){
        StockData stockData = FinancialDataLoader.loadStockData(symbol);
        if (stockData == null) {
            throw new RuntimeException("Failed to load stock data for symbol " + symbol);
        }

        double currentPrice = stockData.getCurrentPrice();
        double previousPrice = stockData.getPreviousPrice();

        if (previousPrice == 0) {
            throw new RuntimeException("Previous price cannot be zero for symbol " + symbol);
        }

        double returnForStock = (currentPrice - previousPrice) / previousPrice;

        return returnForStock;
    }

    private double calculateBondReturn(String bondSymbol) {
        BondData bondData = FinancialDataLoader.loadBondData(bondSymbol);
        if (bondData == null) {
            throw new RuntimeException("Failed to load bond data for symbol " + bondSymbol);
        }

        double faceValue = bondData.getFaceValue();
        double currentPrice = bondData.getCurrentPrice();
        double couponRate = bondData.getCouponRate();
        int yearsToMaturity = bondData.getYearsToMaturity();

        // Calculate the annual coupon payment
        double annualCouponPayment = faceValue * couponRate;

        // Calculate the return for the bond
        double bondReturn = (annualCouponPayment + (faceValue - currentPrice) / yearsToMaturity) / currentPrice;

        return bondReturn;
    }

    private List<Double> calculateBondReturns(String bondSymbols) {
        List<Double> bondReturns = new ArrayList<>();
        String[] bondSymbolArray = bondSymbols.split(",");

        for (String symbol : bondSymbolArray) {
            double bondReturn = calculateBondReturn(symbol);
            bondReturns.add(bondReturn);
        }

        return bondReturns;
    }

    private RealMatrix calculateCovarianceMatrix(List<Double> stockReturns, List<Double> bondReturns, List<Double> fxReturns) {
        int numRows = stockReturns.size();
        int numCols = 3; // stocks, bonds, FX

        double[][] data = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            data[i][0] = stockReturns.get(i);
            data[i][1] = bondReturns.get(i);
            data[i][2] = fxReturns.get(i);
        }

        Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(data);

        // Calculate covariance matrix
        RealMatrix covarianceMatrix = ((Object) matrix.transpose()).multiply(matrix).scalarMultiply(1.0 / (numRows -1));

        return covarianceMatrix;
    }

    private static class Array2DRowRealMatrix {

        public Array2DRowRealMatrix(double[][] data) {
        }

        private Object transpose() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private static class RealMatrix {

        @SuppressWarnings("unused")
        public RealMatrix() {
        }
    }
}






