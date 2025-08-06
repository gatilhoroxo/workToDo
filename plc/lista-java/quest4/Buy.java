public class Buy {
    private Cart chart;

    public Buy() {
        chart = new Cart();
    }

    public void addItemToChart(Items item) { //Add item ao carrinho
        chart.addItem(item);
    }

    public void removeItemFromChart(String name) { //Remove item pelo nome
        chart.removeItem(name);
    }

    public void searchItemInChart(String name) { //Pesquisa item pelo nome
        String result = chart.searchItem(name);
        System.out.println(result);
    }

    public void displayChartItems() { //Print de todos itens no carrinho
        chart.getItems();
    }

}
