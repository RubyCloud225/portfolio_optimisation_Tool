@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioDAO portfolioDAO;

    @GetMapping("/")
    public List<Portfolio> getAllPortfolios() {
        return portfolioDAO.getAllPortfolios();
    }

    @PostMapping("/")
    public ResponseEntity<Object> createPortfolio(@RequestBody Portfolio portfolio) {
        portfolioDAO.addPortfolio(portfolio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(portfolio.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/optimize")
    public OptimizationResult optimizePortfolio(@PathVariable int id) {
        Portfolio portfolio = portfolioDAO.getPortfolio(id);
        return portfolioDAO.optimizePortfolio(portfolio);
    }

    @GetMapping("/{id}/fx")
    public List<FX> getFX(@PathVariable int id) {
        Portfolio portfolio = portfolioDAO.getPortfolio(id);
        return portfolio.getFx();
    }

    @GetMapping("/{id}/shares")
    public List<Share> getShares(@PathVariable int id) {
        Portfolio portfolio = portfolioDAO.getPortfolio(id);
        return portfolio.getShares();
    }

    @GetMapping("/{id}/bonds")
    public List<Bond> getBonds(@PathVariable int id) {
        Portfolio portfolio = portfolioDAO.getPortfolio(id);
        return portfolio.getBonds();
    }
}