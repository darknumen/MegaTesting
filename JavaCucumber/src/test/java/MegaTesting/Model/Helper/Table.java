package MegaTesting.Model.Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Table {

    public WebElement table;
    /*
    Ways to find elements
    */
    private static By rowTag = By.xpath("./tbody/tr");
    private static By headerTag = By.xpath(".//th"); //thead/tr[1]/th
    private static By cellTag = By.xpath("./td");
    private static String innerText = "innerText";
    By buttonTag = By.tagName("button");


    public Table(){
    }

    public Table(WebElement table){
        this.table = table;
    }

    public By getRowTag() {
        return rowTag;
    }

    public By getHeaderTag() {
        return headerTag;
    }

    public By getCellTag() {
        return cellTag;
    }


    /**
     * To set the table
     * @param table The table to set
     */
    public void setTable (WebElement table){
        this.table = table;
    }


    /**
     * The list of the rows
     * @return a List of row WebElement
     */
    public List<WebElement> getRowsList(){
        return table.findElements(rowTag);
    }


    /**
     * The size of the table (number of rows)
     * @return the table size
     */
    public int tableSize(){
        return table.findElements(rowTag).size();
    }


    /**
     * The List of table header cells
     * @return a List of cells WebElement
     */
    public List<WebElement> getHeaderList(){
        return  table.findElements(headerTag);
    }


    /**
     * The cells List of a column
     * @param columnName The name of the column to list
     * @return a WebElements (cells) List of the column
     */
    public List<WebElement> getColumnList(String columnName){
        List<WebElement> cells = new ArrayList<>();
        List<WebElement> rows = this.getRowsList();
        int columnIndex = this.findColumnIndex(columnName);

        for(WebElement row : rows){
            cells.add(row.findElements(cellTag).get(columnIndex));
        }

        return cells;
    }


    /**
     * The cells List of a column
     * @param columnName The name of the column to list
     * @return a String (cells) List of the column
     */
    public List<String> getColumnListOfString(String columnName){
        List<String> cells = new ArrayList<>();
        List<WebElement> rows = this.getRowsList();
        int columnIndex = this.findColumnIndex(columnName);

        for(WebElement row : rows){
            cells.add(row.findElements(cellTag).get(columnIndex).getText());
        }

        return cells;
    }



    /**
     * The List of table header titles in a List
     * @return a List of string titles
     */
    public List<String> getHeaderTitlesList(){
        List <WebElement> headerCells = this.getHeaderList();
        int columnCount = headerCells.size();
        List<String> headerTitles = new ArrayList<>();
        for (int i = 0; i < columnCount; i++) {
            /** Debug code **/ Logger.getGlobal().info(headerCells.get(i).getText());
            headerTitles.add(headerCells.get(i).getText());
        }
        return headerTitles;
    }


    /**
     * Find a single row (a list of cells) by item name in the table and item column index
     * @param itemName
     * @param itemColumnIndex
     * @return a list with the row cells, a empty list if item name not has been found
     */
    public List<WebElement> getCellsFromRow(String itemName, int itemColumnIndex){
        List<WebElement> rowCells = new ArrayList<>();
        if(itemColumnIndex >= 0){
            List<WebElement> rows = this.getRowsList();
            /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Rows Size: {0}", rows.size());
            for(int j = 0; j < rows.size(); j++){
                //The list of the cells row
                rowCells = rows.get(j).findElements(cellTag);
                String currentItem = rowCells.get(itemColumnIndex).getText();
                currentItem = trimInitialSpaces(currentItem);
                /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Elemento:{0}; Expected:{1}",
                        new Object[]{currentItem, itemName});
                //If the content of the cell, in the corresponding column, equal the column name, return the row
                if(currentItem.equalsIgnoreCase(itemName)){
                    /** Debug code **/ Logger.getGlobal().info("Cell founded!!!");
                    return rowCells;
                }
            }
            /** Debug code **/ Logger.getGlobal().info("Can't find the element in the table.");
            rowCells.clear();
            return rowCells;
        }else{
            /** Debug code **/ Logger.getGlobal().info("One or all columns has not been found in the table.");
        }
        return rowCells;
    }


    /**
     * Find a single row (a list of cells) by item name in the table and item column name
     * @param itemName
     * @param itemColumn
     * @return a list with the row cells, a empty list if item name not has been found
     */
    public List<WebElement> getCellsFromRow(String itemName, String itemColumn){
        int itemColumnIndex = this.findColumnIndex(itemColumn);
        return this.getCellsFromRow(itemName, itemColumnIndex);
    }


    /**
     * Find a rows by item name in the table
     * @param itemName
     * @param itemColumn
     * @return a list with the rows, a empty list if item name not has been found
     */
    public List<WebElement> findRowList(String itemName, String itemColumn){
        List<WebElement> rows = new ArrayList<>();
        int itemColumnIndex = this.findColumnIndex(itemColumn);
        if(itemColumnIndex >= 0)
        {
            List<WebElement> allRows = this.getRowsList();
            for(WebElement row : allRows){
                List<WebElement> cells = row.findElements(cellTag);
                //If the content of the cell in the corresponding column equal the column name, return the row
                if(cells.get(itemColumnIndex).getText().equalsIgnoreCase(itemName)){
                    rows.add(row);
                }

            }
        }
        else
        {
            /** Debug code **/ Logger.getGlobal().info("One or all columns has not been found in the table.");
        }
        return rows;
    }


    /**
     * The number of columns
     * @return the number of columns
     */
    public int columnsNumber(){
        return this.getHeaderList().size();
    }


    /**
     * Find the index of the column by name (starting from 0)
     * @param columnName the name of the column
     * @return (-1) if column not found, (>=0) if the column found
     */
    public int findColumnIndex(String columnName){
        List<WebElement> headers = this.getHeaderList();
        int itemColumnIndex = -1;
        /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Header Size: {0}", headers.size());
        for(int i = 0; i < headers.size(); i++){
            WebElement th = headers.get(i);
            /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Column: {0}", th.getAttribute(innerText));
            if(th.getAttribute(innerText).equalsIgnoreCase(columnName)){
                itemColumnIndex = i;
                /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Column found! {0}", itemColumnIndex);
                break;
            }
        }
        return itemColumnIndex;
    }


    /**
     * Looks for a cell by item column and data column name
     * @param itemName the string to search in the table
     * @param itemColumn the column in which to search the item
     * @param dataColumn the column in which to collect the data
     * @return a WebElement represent a cell, (null) if item name not has been found
     */
    public WebElement getCell(String itemName, String itemColumn, String dataColumn){
        //Find the data columns index
        int itemColumnIndex = this.findColumnIndex(itemColumn);
        int dataColumnIndex = this.findColumnIndex(dataColumn);
        return this.getCell(itemName, itemColumnIndex, dataColumnIndex);
    }


    /**
     * Looks for a cell by item column index and data column index
     * @param itemName the string to search in the table
     * @param itemColumnIndex the column index in which to search the item
     * @param dataColumnIndex the column index in which to collect the data
     * @return a WebElement represent a cell, (null) if item name not has been found
     */
    public WebElement getCell(String itemName, int itemColumnIndex, int dataColumnIndex){
        //Find the row
        List<WebElement> cells = this.getCellsFromRow(itemName, itemColumnIndex);
        /** Debug code **/ if (cells.isEmpty()) Logger.getGlobal().info("Cells is empty");
        if ((!cells.isEmpty()) && (dataColumnIndex >= 0)){
            return cells.get(dataColumnIndex);
        }
        return null;
    }


    /**
     * This method trim initial spaces of a string
     * @param toBeCut
     * @return
     */
    private String trimInitialSpaces(String toBeCut) {
        while(Character.isSpaceChar(toBeCut.charAt(0)))
        {
            toBeCut = toBeCut.substring(1, toBeCut.length());
        }
        return toBeCut;
    }
}
