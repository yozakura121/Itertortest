interface Iterator {
    public void first();

    public void next();

    public boolean isDone();

    public Object getItem();
}

class GameListIterator implements Iterator {
    private GameListAggregate aggregate;
    private int current;

    public GameListIterator(GameListAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public void first() {
        current = 0;
    }

    @Override
    public void next() {
        current += 1;
    }

    @Override
    public boolean isDone() {
        if (current >= aggregate.getNumberOfStock()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object getItem() {
        return aggregate.getAt(current);
    }
}

interface Aggregate {
    public Iterator createIterator();
}

class GameListAggregate implements Aggregate {
    private Game[] list = new Game[20];
    private int numberOfStock;

    @Override
    public Iterator createIterator() {
        return new GameListIterator(this);
    }

    public void add(Game game) {
        list[numberOfStock] = game;
        numberOfStock += 1;
    }

    public Object getAt(int number) {
        return list[number];
    }

    public int getNumberOfStock() {
        return numberOfStock;
    }
}

public class IteratorTest {
    public static void main(String[] args) {
        GameListAggregate gameListAggregate = new GameListAggregate();
        Iterator iterator = gameListAggregate.createIterator();
        gameListAggregate.add(new Game("みんなでゴルフ", 3700));
        gameListAggregate.add(new Game("ファイナルファンタジア", 7300));
        gameListAggregate.add(new Game("ロケットモンスター", 5400));
        gameListAggregate.add(new Game("サイコロの達人", 5200));
        iterator.first();
        while (!iterator.isDone()) {
            Game game = (Game) iterator.getItem();
            System.out.println(game.getName());
            iterator.next();
        }
    }
}

class Game {
    private String name;
    private int price;

    public Game(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}