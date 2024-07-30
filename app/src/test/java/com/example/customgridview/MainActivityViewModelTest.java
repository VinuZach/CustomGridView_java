package com.example.customgridview;

import com.example.customgridview.model.SampleDataDetails;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MainActivityViewModelTest {
    private MainActivityViewModel mainActivityViewModel;
    ArrayList<SampleDataDetails> sampleDataDetailsArrayList ;
    @Before
    public void setUp()  {
        mainActivityViewModel = new MainActivityViewModel();
        sampleDataDetailsArrayList = mainActivityViewModel.retrieveInitialData();
    }



    @Test
    public void retrieveInitialData() {

        assert (!sampleDataDetailsArrayList.isEmpty());
    }

    @Test
    public void paginationDataIsWorking() {

        mainActivityViewModel.index = mainActivityViewModel.index + 4;
        sampleDataDetailsArrayList = mainActivityViewModel.retrieveMoreData(mainActivityViewModel.index);
        assert (sampleDataDetailsArrayList.size() > 5);
    }


    @Test
    public void addNewItem() {

        String newDataTitle = "test data 1";
        String newDataUrl = "https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg";

        sampleDataDetailsArrayList = mainActivityViewModel.addDataToExisting(newDataTitle, newDataUrl);
        SampleDataDetails sampleDataDetails = sampleDataDetailsArrayList.get(0);
        assert ((sampleDataDetails.title.equals(newDataTitle) && sampleDataDetails.imageUrl.equals(newDataUrl)));
    }


    @Test
    public void editExisting() {

        String editExistingDataTitle = "edit data 1";
        String editExistingDataUrl = "https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg";
        int editIndex=2;
        SampleDataDetails sampleDataDetails = sampleDataDetailsArrayList.get(editIndex);
        ArrayList<SampleDataDetails> sampleDataDetails1= mainActivityViewModel.editExistingDataItem(sampleDataDetails, editExistingDataTitle, editExistingDataUrl);
        SampleDataDetails edittedSampleData  =sampleDataDetails1.get(editIndex);



        assert ((edittedSampleData.title.equals(editExistingDataTitle) && edittedSampleData.imageUrl.equals(editExistingDataUrl)));
    }

}