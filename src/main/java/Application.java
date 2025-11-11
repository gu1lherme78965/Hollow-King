import Game.Game;
import Game.LevelState;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();
        game.setState(new LevelState(game)); // Temporary while Game doesn't have a Min Menu set up

        game.run();
    }

}
