import java.util.List;
import java.util.Random;
import java.util.Scanner;
//* This class represents WW3, the user being the United States, and the opposition being Russia.
public class War {
   public String countryName;
   public int troops;
   public int weaponScore;
   public int ships;
   public double warScore;
   boolean won;
   public int money = 1000000000;
   /*
        * Constructor for the War Class. Creates a new instance of a
        * countryName represents the name of the country being created
        * troops represents the amount of ground troops of the country being created
        * weaponScore represents the rating of the weapons of the country being created
        * ships represents the amount of ships the country being created has
        * warScore represents the war successes and losses of the country being created
   */
   public War(String countryName, int troops, int weaponScore, int ships, double warScore) {
       this.countryName = countryName;
       this.troops = troops;
       this.weaponScore = weaponScore;
       this.ships = ships;
       this.warScore = warScore;
   }
   /*
    * Method to stimulate war between the United States and the opponent
    * Compares troops, ships, weaponsScore to determine the winner
    * warScore is utilized to keep track of the war
    * If the war is won, the US gains money and Russia suffers heavy losses with troops, weapons and ships and vice versa.
    */
   public void simulateWar(War opponent) {
       if (this.troops < opponent.troops) {
           System.out.println("The United States has been defeated on the ground!");
           opponent.warScore += 5;
       } else {
           System.out.println("The United States defeated " + opponent.countryName + " on the ground!");
           this.warScore += 5;
       }
       if (this.ships < opponent.ships) {
           System.out.println("The United States has been defeated on sea!");
           opponent.warScore += 3;
       } else {
           System.out.println("The United States defeated " + opponent.countryName + " on sea");
           this.warScore += 3;
       }
       if (this.weaponScore < opponent.weaponScore) {
           System.out.println("The United States has inferior weaponry!");
           opponent.warScore += 2;
       } else {
           System.out.println("The United States has superior weaponry to " + opponent.countryName);
           this.warScore += 2;
       }
       if (this.warScore < opponent.warScore) {
           System.out.println("The United States has lost the battle!");
           this.troops = this.troops / 2;
           this.ships = this.ships / 2;
           this.weaponScore = this.weaponScore / 2;
           won = false;
           money -= 100000000;
       } else {
           System.out.println("The United States defeated " + opponent.countryName + " in the battle!");
           opponent.troops = opponent.troops / 2;
           opponent.ships = opponent.ships / 2;
           opponent.weaponScore = opponent.weaponScore / 2;
           won = true;
           money += 100000000;
       }
   }
   /*
    * Method to determine whether the United States won or lost the war in the main class
    */
   public boolean warWon() {
       return won;
   }
   /*
    * Method for the United States to find allies
    * This method randomly generates a country from the countries list, and if it is in the potential allies list, the US gains an ally. If it is the potential enemies list Russia gains an ally.
    * Combines troops, ships, and weapons with the country allied with.
    * If there are no more allies to be made, the method returns no more allies.
    */
   public void findAlly(List<War> allies, List<War> enemies, War Russia, List<War> countries) {
       Random rand = new Random();
       if (!countries.isEmpty()) {
           int randomIndex = rand.nextInt(countries.size());
           War randomCountry = countries.get(randomIndex);
           if (allies.contains(randomCountry)) {
               System.out.println("Congrats! You have gained a new ally: " + randomCountry.countryName);
               this.troops += randomCountry.troops;
               this.ships += randomCountry.ships;
               this.weaponScore += randomCountry.weaponScore;
               countries.remove(randomCountry);
           } else if (enemies.contains(randomCountry)) {
               System.out.println("You have failed to recruit new allies. Russia has gained an ally: " + randomCountry.countryName);
               Russia.troops += randomCountry.troops;
               Russia.ships += randomCountry.ships;
               Russia.weaponScore += randomCountry.weaponScore;
               countries.remove(randomCountry);
           }
       }
       else {
           System.out.println("There are no alliances to be made");
       }
   }
   /*
    * This method helps the user negotiate with Russia and other countries.
    * It randomizes the chances of favorable outcomes (which includes ships, money and troops)
    * The US can lose troops, ships and money whilst Russia can gain troops
    * The negotiations can be neutral
    * The negotiations can also be in favor of the US, so they gain troops and ships whilst Russia loses troops.
    */
   public void negotiations(War unitedStates, War Russia){
       Random r = new Random();
       int outcome = r.nextInt(3);
       if (outcome == 0){
           System.out.println("You failed at negotiations. Multiple relations were heavily strained");
           unitedStates.troops -= 10000;
           unitedStates.ships -= 10;
           Russia.troops += 100000;
           money -= 10000000;
       }
       if (outcome == 1){
           System.out.println("Negotiations were alright. No huge change. ");
       }
       if (outcome == 2){
           System.out.println("Successful Negotiations. You gained troops and ships!");
           unitedStates.troops += 10000;
           unitedStates.ships += 10;
           Russia.troops -= 100000;
       }
   }
   /*
    * Method helps user spend resources the United States has
    * The user can choose to spend money on troops or ships, or both
    * The method updates the balance of the US depending on how much the user buys
    * If the US does not have enough money, the method notifies the user.
    */
   public void resources(War unitedStates) {
       Scanner s = new Scanner(System.in);
       System.out.println("Allocating resources for the United States...");
       System.out.println("Here are the current available funds: $" + money);
       System.out.println("Enter the amount to allocate for troops in hundreds of millions(1 for 100 Million): ");
       int troopA = s.nextInt();
       System.out.println("Enter the amount to allocate for ships in hundreds of millions(1 for 100 Million): ");
       int shipA = s.nextInt();
       Random r = new Random();
       int outcome = r.nextInt(3);
       if (outcome == 1){
           System.out.println("Resources were lost. $100,000,000 dollars has been blundered");
           money -= 100000000;
       }
       int totalCost = (troopA * 100000000) + (shipA * 100000000);
       if (totalCost <= money) {
           unitedStates.troops = (unitedStates.troops + (troopA * 200000));
           unitedStates.ships = (unitedStates.ships + (shipA * 50));
           money -= totalCost;
           System.out.println("Resources used");
       } else {
           System.out.println("Insufficient funds");
       }
   }
   /*
    * Method displays the United States and troops, ships, weaponScore, War Score and money.
    */
   @Override
   public String toString() {
       return "Country: " + countryName +
               "\nTroops: " + troops +
               "\nWeapon Score: " + weaponScore +
               "\nShips: " + ships +
               "\nWar Score: " + warScore +
               "\nMoney: " + money;
   }
}
