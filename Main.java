import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("\nМножество всех расс в игре составляет: " + getCountSpecies() + " ед.\n");

        System.out.println("Общее количество золота на карте: " + calculateGold() + " ед.\n");

        System.out.println("Количество объектов на координате x=2: " + getCountObjectX(2) + " ед.\n");

        System.out.println("Третий по количеству золота на карте:\n "+searchThrid());
        searchThrid();

// посчитать общее количество золота по расе

// найти второго по количеству золота на каждой клетке

    }

    public static Integer getCountSpecies() {
        Integer countSpecies = GameWorld.game.values().stream()
                .flatMap(list -> list.stream())
                .map(gameObject -> gameObject.getRace())
                .distinct()
                .toList()
                .size();
        return countSpecies;
    }

    public static Integer calculateGold() {
        Integer sumGold = GameWorld.game.values().stream()
                .flatMap(list -> list.stream())
                .mapToInt(GameObject -> GameObject.getGold())
                .sum();
        return sumGold;
    }

    public static Integer getCountObjectX(Integer coordinateX) {
        Integer countObjectX = GameWorld.game.entrySet().stream()
                .filter(map -> map.getKey().getX() == 2)
                .flatMap(map -> map.getValue().stream())
                .toList().size();
        return countObjectX;
    }

    public static String searchThrid(){

        GameObject gameObject = GameWorld.game.values().stream()
                .flatMap(list->list.stream())
                .sorted((o1, o2) -> o2.getGold()-o1.getGold())
                .skip(2)
                .findFirst()
                .orElse(null);

        return "Объект:" + gameObject + "\n раса:"+gameObject.getRace()+"\n количество золота: "+gameObject.getGold()+"\n";
    }




    class GameWorld {
        public static final Map<Coordinate, List<GameObject>> game =
                Map.ofEntries(
                        Map.entry(new Coordinate(1, 1), List.of(new GameObject(200, "elf"), new GameObject(205, "gnome"))),
                        Map.entry(new Coordinate(2, 1), List.of(new GameObject(400, "dwarf"), new GameObject(4200, "ork"))),
                        Map.entry(new Coordinate(3, 1), List.of(new GameObject(350, "dwarf"), new GameObject(355, "troll"))),
                        Map.entry(new Coordinate(1, 2), List.of(new GameObject(5400, "human"), new GameObject(325, "human"))),
                        Map.entry(new Coordinate(2, 2), List.of(new GameObject(5400, "human"), new GameObject(2300, "ork"))),
                        Map.entry(new Coordinate(3, 2), List.of(new GameObject(1350, "elf"), new GameObject(6050, "gnome"))),
                        Map.entry(new Coordinate(1, 3), List.of(new GameObject(7400, "gnome"), new GameObject(300, "troll"))),
                        Map.entry(new Coordinate(2, 3), List.of(new GameObject(2356, "human"), new GameObject(1600, "ork"))),
                        Map.entry(new Coordinate(3, 3), List.of(new GameObject(1350, "elf"), new GameObject(4000, "dwarf")))
                );
    }

    static class GameObject {
        private Integer gold;
        private String race;

        public GameObject(Integer gold, String race) {
            this.gold = gold;
            this.race = race;
        }

        public void setGold(Integer gold) {
            this.gold = gold;
        }

        public void setRace(String race) {
            this.race = race;
        }


        public String getRace() {
            return race;
        }

        public Integer getGold() {
            return gold;
        }
    }


    static class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}