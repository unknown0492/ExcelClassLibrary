package com.excel.digitalsignage;

import org.json.JSONObject;

public class SlideUnit { // Determine whether its and image or video and their attributes, useful for Downloading content

    JSONObject meta;
    boolean isImage, isVideo;
    String imageName, videoName, relativePath, md5;
    long size;
    int id;

    public SlideUnit(){}

    public SlideUnit( JSONObject meta, boolean imageOrVideo ){      // image -> true, video -> false
        this.meta = meta;
        try{
            if( imageOrVideo ){
                this.isImage = true;
                this.imageName = meta.getString( "image_name" );
            }
            else{
                this.isVideo = true;
                this.videoName = meta.getString( "video_name" );
            }
            this.relativePath = meta.getString( "relative_path" );
            this.md5 = meta.getString( "md5" );
            this.id = Integer.parseInt( meta.getString( "id" ) );
            this.size = Long.parseLong( meta.getString( "size" ) );

        }
        catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public static SlideUnit[] generateSlideUnit( JSONObject meta, String type ){
        SlideUnit[] slideUnit = new SlideUnit[ 2 ];
        try{
            if( type.equals( "hybrid1" ) ){
                SlideUnit imageUnit = new SlideUnit();
                imageUnit.setImageName( meta.getString( "image_name" ) );
                imageUnit.setMd5( meta.getString( "img_md5" ) );
                imageUnit.setRelativePath( meta.getString( "img_relative_path" ) );
                imageUnit.setID( Integer.parseInt( meta.getString( "img_id" ) ) );
                imageUnit.setSize( Long.parseLong( meta.getString( "img_size" ) ) );
                imageUnit.setIsImage( true );

                SlideUnit videoUnit = new SlideUnit();
                videoUnit.setVideoName( meta.getString( "video_name" ) );
                videoUnit.setMd5( meta.getString( "video_md5" ) );
                videoUnit.setRelativePath( meta.getString( "video_relative_path" ) );
                videoUnit.setID( Integer.parseInt( meta.getString( "video_id" ) ) );
                videoUnit.setSize( Long.parseLong( meta.getString( "video_size" ) ) );
                videoUnit.setIsVideo( true );

                slideUnit[ 0 ] = imageUnit;
                slideUnit[ 1 ] = videoUnit;
            }

        }catch ( Exception e ){
            e.printStackTrace();
        }
        return slideUnit;
    }

    public void setIsImage( boolean isImage ){
        this.isImage = isImage;
    }

    public void setIsVideo( boolean isVideo ){
        this.isVideo = isVideo;
    }

    public boolean isImage(){
        return this.isImage;
    }

    public boolean isVideo(){
        return this.isVideo;
    }


    public void setImageName( String imageName ){
        this.imageName = imageName;
    }

    public String getImageName(){
        return imageName;
    }

    public void setMd5( String md5 ){
        this.md5 = md5;
    }

    public String getMD5(){
        return md5;
    }

    public void setRelativePath( String relativePath ){
        this.relativePath = relativePath;
    }

    public String getRelativePath(){
        return relativePath;
    }

    public void setSize( long size ){
        this.size = size;
    }

    public long getSize(){
        return size;
    }

    public void setID( int id ){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public void setVideoName( String videoName ){
        this.videoName = videoName;
    }

    public String getVideoName(){
        return videoName;
    }



    // Downloads related attributes
    long downloadReference = -1;
    int downloadStatus = -1;
    public static int DOWNLOAD_STARTED = 0;
    public static int DOWNLOAD_IN_PROGRESS = 1;
    public static int DOWNLOAD_COMPLETED = 2;

    public void setDownloadReference( long downloadReference ){
        this.downloadReference = downloadReference;
    }

    public long getDownloadReference(){
        return downloadReference;
    }

    public void setDownloadStatus( int downloadStatus ){
        this.downloadStatus = downloadStatus;
    }

    public int getDownloadStatus(){
        return downloadStatus;
    }
    // Downloads related attributes




    // Verification related attributes
    boolean isDownloadVerified = false;

    public void setDownloadVerified( boolean isDownloadVerified ){
        this.isDownloadVerified = isDownloadVerified;
    }

    public boolean isDownloadVerified(){
        return isDownloadVerified;
    }
    // Verification related attributes
}
