import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


//Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
//        Создать множество ноутбуков.
//        Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру.
//        Критерии фильтрации можно хранить в Map. Например: “Введите цифру, соответствующую необходимому критерию:
//        1 - ОЗУ
//        2 - Объем ЖД
//        3 - Операционная система
//        4 - Цвет
//        Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
//        Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.


public class Main {

    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();

        laptops.add(new Laptop(8, 256, "WindowsS", "черный"));
        laptops.add(new Laptop(4, 128, "Linux", "белый"));
        laptops.add(new Laptop(16, 512, "MacOS", "серебряный"));
        laptops.add(new Laptop(8, 2048, "Windows", "черный"));
        laptops.add(new Laptop(16, 512, "Linux", "серебряный"));
        laptops.add(new Laptop(6, 256, "Windows", "серый"));
        laptops.add(new Laptop(8, 512, "Linux", "красный"));
        laptops.add(new Laptop(16, 1024, "MacOS", "розовый"));
        laptops.add(new Laptop(4, 512, "Windows", "розовый"));
        laptops.add(new Laptop(8, 256, "Linux", "розовый"));

        var Criteria = Query();
        var Laptops = Filter(laptops, Criteria);
        if (Laptops.size() == 0)
        {
            System.out.println("Нет ноутбуков, по заданному критерию");
        }
        else for (Laptop laptop : Laptops) {
            System.out.println(laptop);
        }
    }
    public static Set<Laptop> Filter(Set<Laptop> laptops, Map<String, String> criteria) {
        Set<Laptop> Laptops = new HashSet<>();
        for (Laptop laptop : laptops) {
            boolean Match = true;
            for (Map.Entry<String, String> entry : criteria.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        if (laptop.getRam() < Integer.parseInt(entry.getValue())) {
                            Match = false;
                        }
                        break;
                    case "hdd":
                        if (laptop.getHdd() < Integer.parseInt(entry.getValue())) {
                            Match = false;
                        }
                        break;
                    case "os":
                        if (!laptop.getOs().equals(entry.getValue())) {
                            Match = false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equals(entry.getValue())) {
                            Match = false;
                        }
                        break;
                }
                if (!Match) {
                    break;
                }
            }
            if (Match) {
                Laptops.add(laptop);
            }
        }
        return Laptops;
    }

    public static Map<String, String> Query() {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> Criteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - RAM (4-16)");
        System.out.println("2 - Объем SD (128-2048)");
        System.out.println("3 - OS (Linux, MacOS, Windows)");
        System.out.println("4 - Цвет (черный, белый, серебряный, серый, красный, розовый)");

        int criteria = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите минимальное значение:");
        String minValue = scanner.nextLine();
        scanner.close();

        switch (criteria) {
            case 1:
                Criteria.put("ram", minValue);
                break;
            case 2:
                Criteria.put("hdd", minValue);
                break;
            case 3:
                Criteria.put("os", minValue);
                break;
            case 4:
                Criteria.put("color", minValue);
                break;
        }

        return Criteria;
    }


}