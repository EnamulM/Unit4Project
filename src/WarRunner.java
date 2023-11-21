import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class WarRunner {
   public static void main(String[] args) {
       Scanner s = new Scanner(System.in);
       System.out.println("Hello! What is your name?");
       String name = s.nextLine();
       War unitedStates = new War("United States", 14000000, 95, 580, 0);
       System.out.println("Hello " + name + "! You are the Vice President of the United States. The President, Donaldous Trump, has been killed in an attack from Russia! You are now the President and Commander in Chief.");
       System.out.println("The United States is filled with civil unrest. People are protesting. You send a letter to the President of Russia, and get a response");
       System.out.println("......");
       System.out.println("Dear President " + name + ",");
       System.out.println("The tragic passing of President Donaldous Trump was not our intention. However, we refuse any apology or compensation as the killing was not intentional.");
       System.out.println("Sincerely,");
       System.out.println("V. Putin");
       System.out.println("The people of America are in outrage. Will you advocate for peace or declare war? (p or d)");
       String response = s.nextLine();
       if (response.equals("p")) {
           System.out.println("President " + name + ": We as a nation must not cause a World War in spite. The tragic death of Donaldous Trump was not the intention of Russia. It was by pure accident that this occurred. We have to be better than to seek vengeance");
           System.out.println("The citizens and fellow politicians are disappointed. You have been impeached");
           System.exit(0);
       }
       if (response.equals("d")) {
           System.out.println("The United States has declared war. The nation is in a state of total war. Time to collect allies");
       }
       War Russia = new War("Russia", 13000000, 94, 400, 0);
       System.out.println("10 countries have joined the conflict. The rest are neutral, or not interested in engaging in conflict");
       War UnitedKingdom = new War("United Kingdom", 1000000, 95, 350, 0);
       War Canada = new War("Canada", 11500000, 80, 200, 0);
       War France = new War("France", 2035500, 85, 300, 0);
       War Australia = new War("Australia", 2000000, 70, 70, 0);
       War Germany = new War("Germany", 1900000, 85, 100, 0);
       // enemies
       War China = new War("China", 20000000, 90, 400, 0);
       War NorthKorea = new War("North Korea", 5000000, 100, 470, 0);
       War Iran = new War("Iran", 5000000, 80, 95, 0);
       War Iraq = new War("Iraq", 6000000, 75, 90, 0);
       War Brazil = new War("Brazil", 3000000, 80, 134, 0);
       List<War> countries = new ArrayList<>();
       countries.add(UnitedKingdom);
       countries.add(Canada);
       countries.add(France);
       countries.add(Australia);
       countries.add(Germany);
       countries.add(China);
       countries.add(NorthKorea);
       countries.add(Iran);
       countries.add(Iraq);
       countries.add(Brazil);
       List<War> allies = new ArrayList<>();
       allies.add(UnitedKingdom);
       allies.add(Canada);
       allies.add(France);
       allies.add(Australia);
       allies.add(Germany);
       List<War> enemies = new ArrayList<>();
       enemies.add(China);
       enemies.add(NorthKorea);
       enemies.add(Iran);
       enemies.add(Iraq);
       enemies.add(Brazil);
       System.out.println("Would you like to make allies? (y or n)");
       String response2 = s.nextLine();
       if (response2.equals("y")) {
           unitedStates.findAlly(allies, enemies, Russia, countries);
           unitedStates.simulateWar(Russia);
       }
       if (response2.equals("n")) {
           System.out.println("You go into war without any allies");
           Russia.findAlly(allies, enemies, Russia, countries);
           unitedStates.simulateWar(Russia);
       }
       boolean warWon = unitedStates.warWon();
       if (warWon == true) {
           System.out.println("The United States is overjoyed. You have received congrats from many! However, the war is not over");
       } else {
           System.out.println("You have lost the battle. But this war is far from over!");
       }

       while (unitedStates.troops >= 100000 && unitedStates.ships >= 100 && Russia.troops >= 100000 && Russia.ships >= 100) {
           System.out.println("What would you like to do? W for War, F to Find Allies, N to Negotiate, and R to use Resources");
           String response3 = s.nextLine();
           if (response3.equals("W")) {
               unitedStates.simulateWar(Russia);
           } else if (response3.equals("F")) {
               unitedStates.findAlly(allies, enemies, Russia, countries);
           } else if (response3.equals("N")) {
               unitedStates.negotiations(unitedStates, Russia);
           } else if (response3.equals("R")) {
               unitedStates.resources(unitedStates);
           }
           else{
               System.out.println("Please Input A Valid Response");
           }
           if (unitedStates.troops <= 100000 || unitedStates.ships <= 100) {
               System.out.println("The war has been lost. The United States is fully under the control of Russia. You have been exiled");
               System.exit(0);
           }

           if (Russia.troops <= 100000 || Russia.ships <= 100) {
               System.out.println("The war has been won. You are now known as one of the great leaders of the United States.");
               System.exit(0);
           }
       }
   }
}






