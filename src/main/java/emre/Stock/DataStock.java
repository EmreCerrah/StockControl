package emre.Stock;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class DataStock {
    private static final DataStock instance = new DataStock();
    private final ObservableList<Item> itemList;

    private DataStock() {
        itemList = FXCollections.observableArrayList();
    }

    public static DataStock getInstance() {
        return instance;
    }

    public void addNewItem(Item o) {
        itemList.add(o);
    }

    public void deleteItem(Item o) {
        itemList.remove(o);
    }

    public void loadItems() throws Exception {
        try (BufferedReader reader= new BufferedReader(new FileReader("saves.txt")) ) {
            String singleLine;
            while ((singleLine = reader.readLine()) != null) {
                String[] partOfLine = singleLine.split("//");
                String name = partOfLine[0];
                String details = partOfLine[1];
                String number = partOfLine[2];
                int numberInt=Integer.parseInt(number);

                Item currentItem = new Item(name, details, numberInt);
                itemList.add(currentItem);
            }
        }
    }
    public void saveItems() throws Exception{
        try(BufferedWriter writer=new BufferedWriter(new FileWriter("saves.txt",false))){
            String txt = "";
            Iterator <Item> iterator= itemList.listIterator();

            while (iterator.hasNext()){
                Item currentItem = iterator.next();
                writer.write(String.format("%s//%s//%s",currentItem.getName(),currentItem.getDetails(),currentItem.getPieceString()));
                writer.newLine();
            }
        }
    }

    public ObservableList<Item> getItemList() {
        return itemList;
    }

    public String getItemListString() {
        String[] array = new String[itemList.size()];
        for (int i = 0;i<=itemList.size()-1; i++) {
            array [i]="\n" + (i+1) +" - " + itemList.get(i).getName()+" : " +itemList.get(i).getPieceString()+" Adet";
        }
        String str = Arrays.toString(array);
        return str;
    }
}