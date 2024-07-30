package com.example.customgridview;

import androidx.lifecycle.ViewModel;

import com.example.customgridview.model.SampleDataDetails;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    ArrayList<SampleDataDetails>  sampleDataDetailsArrayLists=new ArrayList<>();
    int index = 0;

    /**
     * retrieve sample data
     * @return ArrayList<SampleDataDetails>
     */
    public ArrayList<SampleDataDetails> retrieveInitialData()
    {
       if (index!=0)
           return sampleDataDetailsArrayLists;
        sampleDataDetailsArrayLists.add(new SampleDataDetails("title 1","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        sampleDataDetailsArrayLists.add(new SampleDataDetails("title 2","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        sampleDataDetailsArrayLists.add(new SampleDataDetails("title 3","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        sampleDataDetailsArrayLists.add(new SampleDataDetails("title 4","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        return sampleDataDetailsArrayLists;
    }

    /**
     * items for pagination purpose
     * @param count till particular value to illustrate pagination and avoid infinite calls
     * @return ArrayList<SampleDataDetails>
     */
    public ArrayList<SampleDataDetails> retrieveMoreData(int count)
    {
        int index=count;
        ++index;
        sampleDataDetailsArrayLists.add(new SampleDataDetails("title "+index,"https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        ++index;
        sampleDataDetailsArrayLists.add(new SampleDataDetails("title "+index,"https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        ++index;
        sampleDataDetailsArrayLists.add(new SampleDataDetails("title "+index,"https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        ++index;
        sampleDataDetailsArrayLists.add(new SampleDataDetails("title "+index,"https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        return sampleDataDetailsArrayLists;
    }

    public ArrayList<SampleDataDetails> editExistingDataItem(SampleDataDetails sampleDataDetails, String titleValue, String imageUrl) {
        int index = sampleDataDetailsArrayLists.indexOf(sampleDataDetails);

        sampleDataDetails.title = titleValue;
        sampleDataDetails.imageUrl = imageUrl;
        sampleDataDetailsArrayLists.remove(index);
        sampleDataDetailsArrayLists.add(index, sampleDataDetails);
        return sampleDataDetailsArrayLists;

    }

    public ArrayList<SampleDataDetails> addDataToExisting(String titleValue, String titleUrl) {
        sampleDataDetailsArrayLists.add(0, new SampleDataDetails(titleValue, titleUrl));
        return sampleDataDetailsArrayLists;
    }
}
