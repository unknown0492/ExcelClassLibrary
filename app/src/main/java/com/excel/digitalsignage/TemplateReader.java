package com.excel.digitalsignage;

import android.content.Context;
import android.util.Log;

import com.excel.excelclasslibrary.UtilShell;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Vector;

public class TemplateReader {

    Context context;
    Slide[] slides;
    //SlideUnit[] slideUnits;
    JSONObject templateJSON;
    Vector<SlideUnit> vectorSlideUnits;
    SlideUnit[] slideUnits;
    final static String TAG = "TemplateReader";

    public TemplateReader(){}

    public TemplateReader( Context context ){
        this.context = context;
    }

    public TemplateReader( Context context, JSONObject templateJSON ){
        this.context = context;
        this.templateJSON = templateJSON;
        vectorSlideUnits = new Vector<SlideUnit>();

        try {
            int totalSlides  = getTotalSlides();
            slides = new Slide[ totalSlides ];
            JSONArray jsonArraySlides = templateJSON.getJSONArray( "slides" );
            for( int i = 0 ; i < totalSlides ; i++ ){

                JSONObject currentSlide = jsonArraySlides.getJSONObject( i );
                slides[ i ] = new Slide( currentSlide.getString( "type" ),
                                         currentSlide.getString( "animation" ),
                                         Long.parseLong( currentSlide.getString( "duration" ) ),
                                         Long.parseLong( currentSlide.getString( "pause_duration" ) ),
                                         currentSlide.getJSONObject( "meta" ) );

                // Log.i( TAG, currentSlide.getJSONObject( "meta" ).toString() );

                if( currentSlide.getString( "type" ).equals( "hybrid1" ) ){
                    SlideUnit[] slideUnit = SlideUnit.generateSlideUnit( currentSlide.getJSONObject( "meta" ), "hybrid1" );
                    vectorSlideUnits.add( slideUnit[ 0 ] ); // 0th position -> Image
                    vectorSlideUnits.add( slideUnit[ 1 ] ); // 1st position -> Video
                    //Log.e( TAG, vectorSlideUnits.get( i ).getRelativePath() );
                    //Log.e( TAG, i + "" );
                }
                else{
                    vectorSlideUnits.add( new SlideUnit( currentSlide.getJSONObject( "meta" ), currentSlide.getString( "type" ).equals( "image" )?true:false ) );
                    //Log.w( TAG, vectorSlideUnits.get( i ).getRelativePath() );
                    //Log.w( TAG, i + "" );
                }

            }

            for( int i = 0 ; i < vectorSlideUnits.size() ; i++ ){
                SlideUnit su = vectorSlideUnits.get( i );
                //Log.w( TAG, String.valueOf( su.isImage() ) + " : " + su.getRelativePath() );
            }

            int[] indexes_image_remove = new int[ vectorSlideUnits.size() ];
            int[] indexes_video_remove = new int[ vectorSlideUnits.size() ];
            int index_image_incr = 0;
            int index_video_incr = 0;

            // Remove Duplicate Image Downloads based on id
            for( int i = vectorSlideUnits.size() - 1 ; i >= 0 ; i-- ){
                SlideUnit slideUnit = vectorSlideUnits.get( i );
                //Log.d( TAG, "inside 1" );
                //Log.d( TAG, "matching : "+slideUnit.getRelativePath() );
                for( int j = i - 1 ; j > 0 ; j-- ){
                    SlideUnit slideUnitNext = vectorSlideUnits.get( j );
                    //Log.d( TAG, "inside 2" );
                    if( slideUnitNext.getID() == slideUnit.getID() ){
                        if( slideUnitNext.isImage() == slideUnit.isImage() ){
                            indexes_image_remove[ index_image_incr++ ] = j;
                            // Log.i( TAG, "found : "+slideUnitNext.getImageName() );
                        }
                    }
                }
            }

            // Remove from the vector
            for( int i = 0 ; indexes_image_remove[ i ] != 0 ; i++ ){
                vectorSlideUnits.remove( indexes_image_remove[ i ] );
            }

            // Remove Duplicate Video Downloads based on id
            for( int i = vectorSlideUnits.size() - 1 ; i >= 0 ; i-- ){
                SlideUnit slideUnit = vectorSlideUnits.get( i );
                for( int j = i - 1 ; j > 0 ; j-- ){
                    SlideUnit slideUnitNext = vectorSlideUnits.get( j );
                    if( slideUnitNext.getID() == slideUnit.getID() ){
                        if( slideUnitNext.isVideo() == slideUnit.isVideo() ){
                            indexes_video_remove[ index_video_incr++ ] = j;
                        }
                    }
                }
            }

            // Remove from the vector
            for( int i = 0 ; indexes_video_remove[ i ] != 0 ; i++ ){
                vectorSlideUnits.remove( indexes_video_remove[ i ] );
            }

            /*for( int i = 0 ; i < vectorSlideUnits.size() ; i++ ){
                SlideUnit su = vectorSlideUnits.get( i );
                Log.e( TAG, String.valueOf( su.isImage() ) + " : " + su.getRelativePath() );
            }*/

        }
        catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public JSONObject getTemplateJSON() {
        return templateJSON;
    }

    // Compare and return only those SlideUnit which are needed to be downloaded
    public static SlideUnit[] getDownloadableSlideUnits( JSONObject existingTemplate, JSONObject newTemplate ){

        TemplateReader existingTemplateReader = new TemplateReader( null, existingTemplate );
        TemplateReader newTemplateReader      = new TemplateReader( null, newTemplate );

        SlideUnit[] existingSlideUnits = existingTemplateReader.getSlideUnits();
        SlideUnit[] newSlideUnits      = newTemplateReader.getSlideUnits();

        Vector<SlideUnit> vectorFilteredSlideUnits = new Vector<SlideUnit>();
        Vector<SlideUnit> vectorToBeDeletedSlideUnits = new Vector<SlideUnit>();

        int j = 0;
        for( int i = 0 ; i < newSlideUnits.length ; i++ ){
            int newID = newSlideUnits[ i ].getID();
            String newMD5 = newSlideUnits[ i ].getMD5();

            for( j = 0 ; j < existingSlideUnits.length ; j++ ){
                int existingID = existingSlideUnits[ j ].getID();

                if( ( newSlideUnits[ i ].isImage() && existingSlideUnits[ j ].isImage() ) ||
                        ( newSlideUnits[ i ].isVideo() && existingSlideUnits[ j ].isVideo() ) ) {
                    // Log.d( TAG, "Comparing IDs : " + newID + " with " + existingID );
                    if (newID == existingID) {
                        String existingMD5 = existingSlideUnits[j].getMD5();
                        if (newMD5.equals(existingMD5)) {     // This means that the image/video is the same
                            // There is no need to download this content again
                            // Log.w( TAG, "Same content : " + existingSlideUnits[ j ].getRelativePath() );
                            break;

                        /*// Name of the image/video could be different
                        // Check if the name is the same or not
                        String existingContentName, newContentName;
                        if( existingSlideUnits[ j ].isImage() ){
                            existingContentName = existingSlideUnits[ j ].getImageName();
                            newContentName      = newSlideUnits[ j ].getImageName();
                        }
                        else{
                            existingContentName = existingSlideUnits[ j ].getVideoName();
                            newContentName      = newSlideUnits[ j ].getVideoName();
                        }*/

                            // Names can never be different for the same file, because the file with same ID, cannot have different name, because we do not allow user to rename the file
                            // The update queries will also not update the name of the file

                        /*// If the names are also the same, then do nothing
                        if( existingContentName.equals( newContentName )){
                            break;
                        }
                        else{
                            // Rename the
                        }*/

                        }
                    }
                }
            }
            if( j == existingSlideUnits.length ){
                Log.w( TAG, "New content : " + newSlideUnits[ i ].getRelativePath() );
                vectorFilteredSlideUnits.add( newSlideUnits[ i ] );
            }
        }

        SlideUnit[] filteredSlideUnits = new SlideUnit[ vectorFilteredSlideUnits.size() ];

        for( int i = 0 ; i < filteredSlideUnits.length ; i++ ){
            filteredSlideUnits[ i ] = vectorFilteredSlideUnits.get( i );
        }

        return filteredSlideUnits;
    }


    // Compare and return only those SlideUnit which are needed to be DELETED
    public static SlideUnit[] getToBeDeletedSlideUnits( JSONObject existingTemplate, JSONObject newTemplate ){

        TemplateReader existingTemplateReader = new TemplateReader( null, existingTemplate );
        TemplateReader newTemplateReader      = new TemplateReader( null, newTemplate );

        SlideUnit[] existingSlideUnits = existingTemplateReader.getSlideUnits();
        SlideUnit[] newSlideUnits      = newTemplateReader.getSlideUnits();

        Vector<SlideUnit> vectorToBeDeletedSlideUnits = new Vector<SlideUnit>();

        int j = 0;
        for( int i = 0 ; i < existingSlideUnits.length ; i++ ){
            int existingID = existingSlideUnits[ i ].getID();
            String existingMD5 = existingSlideUnits[ i ].getMD5();

            for( j = 0 ; j < newSlideUnits.length ; j++ ){
                int newID = newSlideUnits[ j ].getID();

                if( ( newSlideUnits[ j ].isImage() && existingSlideUnits[ i ].isImage() ) ||
                        ( newSlideUnits[ j ].isVideo() && existingSlideUnits[ i ].isVideo() ) ) {
                    //Log.d( TAG, "Comparing IDs : " + newID + " with " + existingID );
                    if ( existingID == newID ) {
                        //Log.d( TAG, "IDs are same for new : " + newSlideUnits[ j ].getRelativePath() + " and existing " + newSlideUnits[ i ].getRelativePath() );
                        String newMD5 = newSlideUnits[ j ].getMD5();
                        if ( !existingMD5.equals( newMD5 ) ) {     // This means that the image/video is NOT the same
                            // Delete the already present content with the existing ID
                            Log.w( TAG, "Delete this : " + newSlideUnits[ j ].getRelativePath() );
                            vectorToBeDeletedSlideUnits.add( existingSlideUnits[ i ] );
                            break;
                        }
                        break;
                    }

                }

            }
            // If after iterating all the existing units, it is not present in the new template, then it means that the unit is no more and should be deleted
            if( j == newSlideUnits.length ){
                vectorToBeDeletedSlideUnits.add( existingSlideUnits[ i ] );
                Log.w( TAG, "Delete this too : " + existingSlideUnits[ i ].getRelativePath() );
            }
        }

        SlideUnit[] filteredSlideUnits = new SlideUnit[ vectorToBeDeletedSlideUnits.size() ];

        for( int i = 0 ; i < filteredSlideUnits.length ; i++ ){
            filteredSlideUnits[ i ] = vectorToBeDeletedSlideUnits.get( i );
        }

        return filteredSlideUnits;
    }



























    public Slide[] getSlides(){
        return slides;
    }

    public SlideUnit[] getSlideUnits(){
        SlideUnit[] slideUnits = new SlideUnit[ vectorSlideUnits.size() ];
        for( int i = 0 ; i < vectorSlideUnits.size() ; i++ ){
            slideUnits[ i ] = vectorSlideUnits.get( i );
            //Log.e( TAG, String.valueOf( slideUnits[ i ].isImage() ) + " : " + slideUnits[ i ].getRelativePath() );
        }
        return slideUnits;
    }

    public int getTotalSlides(){
        int totalSlides = 0;
        try {
            totalSlides = templateJSON.getInt( "total_slides" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return totalSlides;
    }

    public String getTemplateMD5(){
        String md5 = null;
        try {
            md5 = templateJSON.getString( "template_md5" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return md5;
    }

    public int getTemplateID(){
        int id = -1;
        try {
            id = templateJSON.getInt( "template_id" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return id;
    }

    public long getTemplateSize(){
        long templateSize = 0;
        try {
            templateSize = templateJSON.getLong( "template_size" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return templateSize;
    }


}
