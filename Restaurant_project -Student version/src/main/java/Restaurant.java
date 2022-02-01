import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public Object isRestaurantOpen() {
        int checkClosingTime = getCurrentTime().compareTo(this.closingTime);
        int checkOpeningTime = getCurrentTime().compareTo(this.openingTime);

        //Check if the currenttime is greater than or equal to opening time and less than closing time
        return checkClosingTime < 0 && checkOpeningTime >= 0;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {

        return this.menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }
    public int getTotalCostOfItems(List<Item> selectedItems){
        int totalCost = 0;
        for(Item item: selectedItems) {
            totalCost = totalCost + item.getPrice();
        }
        return  totalCost;


    }
}
