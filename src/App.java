import java.io.File;
import java.util.*;

public class App {
    /*
     * D&D Sort Latest Update November 11, 2021
     * 
     * @author Abby Bock
     */
    public static void main(String[] args) throws Exception {
        /*
         * HashMap<Character, HashMap<String, Integer>> dD = new HashMap();
         * //hashmap<Characer (holds name, species), Hashmap<skill, level>>
         * HashMap<String, Character > dD = new HashMap(); //<Name + species,
         * Character(name, species, skill, level)
         */

        // HashMap<Species+name, HasMap<skill, level>>
        HashMap<String, HashMap<String, Integer>> d_d = new HashMap<>();

        // part 1: load in all characters and skills
        Scanner sc = new Scanner(new File("src/charList.txt"));
        String name = "";
        // while charList.txt has next line, continue scanning
        while (sc.hasNext()) {
            String input = sc.nextLine();
            // skip empty lines
            if (input.equals("")) {
                input = sc.nextLine();
            }
            name = input;
            // initialize new key (the name and species of the character)
            d_d.put(name, new HashMap<>());
            // for next 6 lines, add each skill and level pair to
            // character's hashmap
            for (int i = 0; i < 6; i++) {
                input = sc.nextLine();
                String[] skillLevel = input.split(":");
                d_d.get(name).put(skillLevel[0].toLowerCase(), Integer.parseInt(skillLevel[1]));
            }
        }
        sc.close();
        // Part 2: take in parameter and sort characters and skills
        Scanner in = new Scanner(System.in);
        // always true while loop for continuous input
        // until user initiates exit.
        while (true) {
            System.out.println("Please enter the skill you would like to sort D&D characters by:");
            String input = in.nextLine();
            input = input.toLowerCase();
            // checks for exit key and valid skill inputs
            if (input.equals("recursion!")) {
                System.out.println("\nBye-bye! And dont die!\n");
                in.close();
                System.exit(0);
            } else if (!d_d.get(name).containsKey(input)) {
                System.out.println("\nInvalid input. Please check your spelling. ");
            } else {
                System.out.println("\nThe characters are in ascending order of their " + input + ":");
                // initialize priority que to sort level values
                PriorityQueue<Integer> sort = new PriorityQueue<>();
                // load level values of all characters of specific skill into queue
                for (String a : d_d.keySet()) {
                    sort.add(d_d.get(a).get(input));
                }
                // scans through the entirity of priority que
                while (!sort.isEmpty()) {
                    // for each character, check if their level matches
                    // if it does, print out that character
                    // and remove level value from queue
                    for (String s : d_d.keySet()) {
                        if (d_d.get(s).get(input) == sort.peek()) {
                            System.out.println(s + " (" + d_d.get(s).get(input) + ")");
                            sort.poll();
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}
