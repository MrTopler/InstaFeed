package nc.prog1415;

import android.app.Application;

import java.util.ArrayList;
//Coded by Jake Terryberry
public class MyApplication extends Application {

    private ArrayList<Integer> currentBrands;
    private ArrayList<Integer> currentStores;
    private ArrayList<String> brandNames;
    private ArrayList<String> storeLocations;
    private ArrayList<String> storeImages;
    private String selectedBrandName;

    //storeImages get-set
    public ArrayList<String> getStoreImages() {
        return storeImages;
    }
    public void setStoreImages(ArrayList<String> storeImages) {
        this.storeImages = storeImages;
    }

    //storeLocations get-set
    public ArrayList<String> getStoreLocations() {
        return storeLocations;
    }
    public void setStoreLocations(ArrayList<String> storeLocations) {
        this.storeLocations = storeLocations;
    }

    //selectedBrandName get-set
    public String getSelectedBrandName() {
        return selectedBrandName;
    }
    public void setSelectedBrandName(String selectedBrandName) {
        this.selectedBrandName = selectedBrandName;
    }

    //brandNames get-set
    public ArrayList<String> getBrandNames() {
        return brandNames;
    }
    public void setBrandNames(ArrayList<String> brandNames) {
        this.brandNames = brandNames;
    }

    //currentBrands get-set
    public ArrayList<Integer> getCurrentBrands() {
        return currentBrands;
    }
    public void setCurrentBrands(ArrayList<Integer> currentBrands) {
        this.currentBrands = currentBrands;
    }

    //currentStores get-set
    public ArrayList<Integer> getCurrentStores() {
        return currentStores;
    }
    public void setCurrentStores(ArrayList<Integer> currentStores) {
        this.currentStores = currentStores;
    }

}
