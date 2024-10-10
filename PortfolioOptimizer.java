import org.apache.common.math3.optm.InitialGuess;
import org.apache.common.math3.optm.MaxEval;
import org.apache.common.math3.optm.PointValuePair;
import org.apache.common.math3.optm.LinearConstraint;
import org.apache.common.math3.optm.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.ObjectiveFunction;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.NelderMeadSimplex;

public class PortfolioOptimizer {
    public double[] optimizePortfolio(double[] returns, RealMatrix covarianceMatrix) {
        int numAssets = returns.length;

        // Define the objective function (maximize returns)
        LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(returns, 0);

        // Defind the constraints (weights sum to 1, now short selling)
        LinearConstraintSet constraints = new LinearConstraintSet(
            new LinearConstraint(new double[numAssets], Relationship.EQ, 1), // weights sum to 1
            new LinearConstraint(new double[numAssets], Relationship.GEQ, 0) // no short selling
        );

        // Define the initial guess (equal weights)
        double[] initialGuess = new double[numAssets];
        for (int i = 0; i < numAssets; i++) {
            initialGuess[i] = 1.0 / numAssets;
        }

        // Define the optimization problem
        PointValuePair solution = new NelderMeadSimplex(1e-6).optimize(
            new MaxEval(1000),
            new ObjectiveFunction(objectiveFunction),
            GoalType.MAXIMIZE,
            new InitialGuess(initialGuess),
            constraints
        );

        // Return the optimized weights
        return solution.getPoint();
    }
}

///Example usage

/// double[] returns = {0.03, 0.05, 0.01}; // asset returns
/// RealMatrix covarianceMatrix = new Array2DRowRealMatrix(new double[][]{{0.001, 0.005, 0.002}, {0.005, 0.012, 0.007}, {0.002, 0.007, 0.015}}); // covariance matrix

///PortfolioOptimizer optimizer = new PortfolioOptimizer();
///double[] optimizedWeights = optimizer.optimizePortfolio(returns, covarianceMatrix);

///System.out.println("Optimized Weights:");
///for (int i = 0; i < optimizedWeights.length; i++) {
    ///System.out.println("Asset " + (i + 1) + ": " + optimizedWeights[i]);
///}