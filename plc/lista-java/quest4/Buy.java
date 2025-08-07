public class Buy {
    private Cart chart;

    public Buy() {
        chart = new Cart();
    }

    public void addItemToChart(Items item) { //Add item ao carrinho
        try {
            chart.addItem(item);
            System.out.println(item.getName()+ " added");
        }
        catch (NotEnoughSpace e) {System.out.println(e.getMessage());}
        catch (ItemAlreadyExists e) {System.out.println(e.getMessage());}
        catch (NullParameters e) {System.out.println(e.getMessage());}
    }

    public void removeItemFromChart(String name) { //Remove item pelo nome
        try {
            chart.removeItem(name);
            System.out.println(name+" removed");
        } 
        catch (NullParameters e) {System.out.println(e.getMessage());}
        catch (NotFound e) {System.out.println(e.getMessage());}
    }

    public void searchItemInChart(String name) { //Pesquisa item pelo nome
        try{
            String result = chart.searchItem(name);
            System.out.println(result);
        }
        catch (NullParameters e) {System.out.println(e.getMessage());}
        catch (NotFound e) {System.out.println(e.getMessage());}
    }

    public void displayChartItems() { //Print de todos itens no carrinho
        chart.getItems();
    }

}
