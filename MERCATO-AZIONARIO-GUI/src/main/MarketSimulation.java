import javax.swing.Timer;

public class MarketSimulation {
    private Timer timer;

    public MarketSimulation(Market market) {
        timer = new Timer(30000, e -> market.simulateMarket());
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
