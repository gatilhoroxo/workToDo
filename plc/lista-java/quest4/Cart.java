public class Cart {
    private Items[] buyItems;
    private int itemCount;

    public Cart() {
        buyItems = new Items[10]; // Initializing with a fixed size
        itemCount = 0;
    }

    public String searchItem(String name) throws NotFound, NullParameters { //Pesquisa item pelo nome
        for (int i = 0; i < itemCount; i++) {
            if (buyItems[i].getName().equals(name)) {
                return "Item found: " + buyItems[i].getName() + " at price: " + buyItems[i].getPrice();
            }
        }
    }

    public void addItem(Items item) throws NotEnoughSpace, ItemAlreadyExists, NullParameters { //adiciona item ao carrinho
        for (int i = 0; i < itemCount; i++) {
            if (buyItems[i].getName().equals(item.getName())) {
                throw new ItemAlreadyExists();
            }
        }
        buyItems[itemCount++] = item;
    }

    public void removeItem(String name) throws NotFound, NullParameters { //Remove item pelo nome
        for (int i = 0; i < itemCount; i++) {
            if (buyItems[i].getName().equals(name)) {
                buyItems[i] = buyItems[--itemCount]; // Replace with last item
                buyItems[itemCount] = null; // Clear the last item
                return;
            }
        }
    }

    public void getItems() { //Print de todos itens no carrinho
        if (itemCount == 0) {
            System.out.println("No items in the chart.");
            return;
        }
        for (int i = 0; i < itemCount; i++) {
            System.out.println("Item: " + buyItems[i].getName() + ", Price: " + buyItems[i].getPrice());
        }
    }

}


