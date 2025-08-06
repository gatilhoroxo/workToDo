public class Main {
    public static void main(String[] args) {
        Items item1 = new Items("Headset da Avon", 150.50);
        Items item2 = new Items("SmartPhone Banana", 3001.99);
        Items item3 = new Items("Mouse Worker", 99.99);
        Items item4 = new Items("Teclado Eletrico", 250.00);
        Items item5 = new Items("Monitor 3K", 1200.00); 
        Items item6 = new Items("Mouse super gamer", 500.00); 
        Items item7 = new Items("Cadeira do Mario", 999.99); 
        Items item8 = new Items("Mesa do Chefao", 1500.00); 
        Items item9 = new Items("Bolsa da Prada", 200.00); 
        Items item10 = new Items("Garrafa Tapuer", 200.00);
        Items item11 = new Items("Celular da Pera" , 3000.00); 
        
        Buy buy = new Buy();
        buy.addItemToChart(item1);
        buy.addItemToChart(item2);
        buy.addItemToChart(item3);
        buy.addItemToChart(item4);
        buy.addItemToChart(item5); 
        buy.addItemToChart(item6);
        buy.addItemToChart(item7);
        buy.addItemToChart(item8);
        buy.addItemToChart(item9);
        buy.addItemToChart(item10);
        
        buy.addItemToChart(item11); //TEST#1: This will throw NotEnoughSpace exception
        
        buy.removeItemFromChart("Mouse Worker");

        buy.addItemToChart(item1); ////TEST#2: This will throw ItemAlreadyExists exception
        
        buy.removeItemFromChart("Headsett da Avon"); ////TEST#3: This will throw NotFound exception
        
        buy.searchItemInChart(null); ////TEST#4: This will throw NullParameters exception
        
        buy.displayChartItems();
    }

}
