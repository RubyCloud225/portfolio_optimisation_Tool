import jdk.jfr.Timestamp;
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

        // Optimize the portfolio
        double[] optimizedWeights = optimizer.optimizePortfolio(returns, covarianceMatrix);

        // Verify the optimized Weights
        assertEquals(3, optimizedWeights.length);
        for (double weight : optimizedWeights) {
            assertTrue(weight >= 0);
            assertTrue(weight <= 1);
        }
        assertEquals(1, sum(optimizedWeights), 1e-6);
    }

    @Time
    public void testOptimizerPortfolio_NoShortSelling() {
        // Define the asset returns and covariance matrix
        double[] returns = {0.03, 0.05, 0.01};
        RealMatrix covarianceMatrix = new AssetReturnCalculator(new double[][]{{0.001, 0.005, 0.002}, {0.005, 0.012, 0.007}, {0.002, 0.007, 0.015}});
        // Create a PortfolioOptimizer instance
        PortfolioOptimizer optimizer = new PortfolioOptimizer();
        //Optimize the Portfolio
        double[] optimizedWeights = optimizer.optimizePortfolio(returns, covarianceMatrix);
        // Verify no short selling
        for (double weight : optimizedWeights) {
            assertTrue(weight >= 0);
        }
    }

    @Test
    public void testOptimizerPortfolio_WeightsSumTo1() {
        // Define the asset returns and covariance matrix
        double[] returns = {0.03, 0.05, 0.01};
        RealMatrix covarianceMatrix = new Array2DRowRealMatrix(new double[][]{{0.001, 0.005, 0.002}, {0.005, 0.012, 0.007}, {0.002, 0.007, 0.015}});
    }
}


