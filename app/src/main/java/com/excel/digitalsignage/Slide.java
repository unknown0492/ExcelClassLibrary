package com.excel.digitalsignage;

import org.json.JSONObject;

public class Slide {

    private String type;
    private String animation;
    private long duration;
    private long pauseDuration;
    private JSONObject meta;

    public Slide(){}

    public Slide( String type, String animation, long duration, long pauseDuration, JSONObject meta ){
        this.setType(type);
        this.setAnimation(animation);
        this.setDuration(duration);
        this.setPauseDuration(pauseDuration);
        this.setMeta(meta);
    }










    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getPauseDuration() {
        return pauseDuration;
    }

    public void setPauseDuration(long pauseDuration) {
        this.pauseDuration = pauseDuration;
    }

    public JSONObject getMeta() {
        return meta;
    }

    public void setMeta(JSONObject meta) {
        this.meta = meta;
    }


    // Getting information from Meta
    public String getImageName(){
        String imageName = null;
        try{
            imageName = getMeta().getString( "image_name" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return imageName;
    }

    public String getMD5(){
        String md5 = null;
        try{
            md5 = getMeta().getString( "md5" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return md5;
    }

    public long getSize(){
        long size = 0;
        try{
            size = Long.parseLong( getMeta().getString("size" ) );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return size;
    }

    public int getID(){
        int id = -1;
        try{
            id = Integer.parseInt( getMeta().getString("id" ) );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return id;
    }

    public String getVideoName(){
        String imageName = null;
        try{
            imageName = getMeta().getString( "video_name" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return imageName;
    }



    public String getHybrid1ImageRelativePath(){
        String imageRelativePath = null;
        try{
            imageRelativePath = getMeta().getString( "img_relative_path" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return imageRelativePath;
    }

    public String getHybrid1ImageMD5(){
        String md5 = null;
        try{
            md5 = getMeta().getString( "img_md5" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return md5;
    }

    public int getHybrid1ImageID(){
        int id = -1;
        try{
            id = Integer.parseInt( getMeta().getString( "img_id" ) );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return id;
    }

    public long getHybrid1ImageSize(){
        long size = 0;
        try{
            size = Long.parseLong( getMeta().getString( "img_size" ) );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return size;
    }

    public String getHybrid1VideoRelativePath(){
        String videoRelativePath = null;
        try{
            videoRelativePath = getMeta().getString( "video_relative_path" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return videoRelativePath;
    }

    public String getHybrid1VideoMD5(){
        String md5 = null;
        try{
            md5 = getMeta().getString( "video_md5" );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return md5;
    }

    public int getHybrid1VideoID(){
        int id = -1;
        try{
            id = Integer.parseInt( getMeta().getString( "video_id" ) );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return id;
    }

    public long getHybrid1VideoSize(){
        long size = 0;
        try{
            size = Long.parseLong( getMeta().getString( "video_size" ) );
        }
        catch ( Exception e ){
            e.printStackTrace();
        }
        return size;
    }

    // Getting information from Meta
}
