package com.example.customgridview.model;

import java.util.ArrayList;

public class SampleDataManger {
   ArrayList<SampleDataDetails> sampleDataDetails;

   public ArrayList<SampleDataDetails> retrieveInitialData()
    {
        sampleDataDetails=new ArrayList<>();
        sampleDataDetails.add(new SampleDataDetails("title 1","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        sampleDataDetails.add(new SampleDataDetails("title 2","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        sampleDataDetails.add(new SampleDataDetails("title 3","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        sampleDataDetails.add(new SampleDataDetails("title 4","https://static.remove.bg/sample-gallery/graphics/bird-thumbnail.jpg"));
        return sampleDataDetails;
    }
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
