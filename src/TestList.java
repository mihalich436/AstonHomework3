import Implementation.MyArrayList;
import Interface.MyList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        runDemo();
    }
    public static void runDemo() {
        final int wrongCapacity = -2;
        System.out.println("1. Create list with wrong capacity " + wrongCapacity + ".");
        try {
            MyList<String> myList = new MyArrayList<>(wrongCapacity);
            System.out.println("List with capacity " + wrongCapacity + " created.");
        }
        catch (Exception e){
            System.out.println(e);
        }

        System.out.println("2. Create list from collection {Bob, Jack}.");
        List<String> names = List.of("Bob", "Jack");
        MyList<String> myList1 = new MyArrayList<>(names);
        System.out.println(myList1);

        System.out.println("3. Get 0 - Bob, 1 - Jack and 2 - not existing one.");
        try{
            System.out.println("Element 0: " + myList1.get(0));
            System.out.println("Element 1: " + myList1.get(1));
            System.out.println("Element 2: " + myList1.get(2));
        }
        catch (Exception e){
            System.out.println(e);
        }

        System.out.println("4. Add John to the end, Brian on 2nd place, Will to 10th place.");
        myList1.add("John");
        myList1.add("Brian", 1);
        try {
            myList1.add("Will", 10);
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println(myList1);

        System.out.println("5. Add collection {Kelly, Lenny} to the end, add {Cody, Dan} on 2nd place.");
        names = List.of("Kelly", "Lenny");
        myList1.addAll(names);
        names = List.of("Cody", "Dan");
        myList1.addAll(names, 2);
        System.out.println(myList1);

        System.out.println("6. Add {Alex, Phil, Neill, George} to the end (size overcomes capacity).");
        names = List.of("Alex","Phil", "Neill", "George");
        myList1.addAll(names);
        System.out.println(myList1);

        System.out.println("7. Add Jerry, Ryan, Gale, Zak, Rodger, Keith, Tom, Jim, Mathew (size overcomes capacity).");
        myList1.add("Jerry");
        myList1.add("Ryan");
        myList1.add("Gale");
        myList1.add("Zak");
        myList1.add("Rodger");
        myList1.add("Keith");
        myList1.add("Tom");
        myList1.add("Jim");
        myList1.add("Mathew");
        System.out.println(myList1);
        System.out.println("Size: " + myList1.size());

        System.out.println("8. Remove Alex.");
        System.out.println(myList1.remove("Alex"));
        System.out.println(myList1);
        System.out.println("Size: " + myList1.size());

        System.out.println("9. Try remove Alex again.");
        System.out.println(myList1.remove("Alex"));

        System.out.println("10. Remove " + names + ".");
        System.out.println(myList1.removeAll(names));
        System.out.println(myList1);
        System.out.println("Size: " + myList1.size());

        System.out.println("11. Sort list.");
        //myList1.sort(String::compareTo);
        MyList.sort(myList1, String::compareTo);
        System.out.println(myList1);
    }
}
