public class Portfolio {
    private int id;
    private String name;
    private List<FX> fx;
    private List<Share> shares;
    private List<Bond> bonds;

    // getters and setters
}

public class FX {
    private int id;
    private String currencyPair;
    private double amount;
    private double exchangeRate;

    // getters and setters
}

public class Stock {
    private int id;
    private String symbol;
    private double amount;
    private double price;

    // getters and setters
}

public class Bond {
    private int id;
    private String symbol;
    private double amount;
    private double yield;

    // getters and setters
}

public class OptimizationResult {
    private List<FX> optimizedFX;
    private List<Share> optimiziedShares;
    private List<Bond> optimizedBonds;
    private double expectedReturn;
    private double risk;

    // getters and setters
}
