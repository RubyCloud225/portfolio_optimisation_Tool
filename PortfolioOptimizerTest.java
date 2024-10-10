import org.junit.Test;
import static org.junit.Assert.*;


public class PortfolioOptimizerTest {
    @Test
    public void testOptimizerPortfolio() {
        // Define the asset returns and covariance matrix
        double[] returns = {0.03, 0.05, 0.01};
        RealMatrix covarianceMatrix = new Array2DRowRealMatrix(new double[][]{{0.001, 0.005, 0.002},{0.005, 0.012, 0.007}, {0.002, 0.007, 0.015}});

        // Create a PortfolioOptimizer instance
        PortfolioOptimizer optimizer = new PortfolioOptimizer();
    }
}
