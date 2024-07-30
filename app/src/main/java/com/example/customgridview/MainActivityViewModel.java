package com.example.customgridview;

import androidx.lifecycle.ViewModel;

import com.example.customgridview.model.SampleDataDetails;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    ArrayList<SampleDataDetails> sampleDataDetails;

    /**
     * retrieve sample data
     * @return ArrayList<SampleDataDetails>
     */
    public ArrayList<SampleDataDetails> retrieveInitialData()
    {
        sampleDataDetails=new ArrayList<>();
        sampleDataDetails.add(new SampleDataDetails("title 1","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        sampleDataDetails.add(new SampleDataDetails("title 2","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        sampleDataDetails.add(new SampleDataDetails("title 3","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        sampleDataDetails.add(new SampleDataDetails("title 4","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        return sampleDataDetails;
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
        sampleDataDetails.add(new SampleDataDetails("title "+index,"https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        ++index;
        sampleDataDetails.add(new SampleDataDetails("title "+index,"https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        ++index;
        sampleDataDetails.add(new SampleDataDetails("title "+index,"https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        ++index;
        sampleDataDetails.add(new SampleDataDetails("title "+index,"https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        return sampleDataDetails;
    }
}
