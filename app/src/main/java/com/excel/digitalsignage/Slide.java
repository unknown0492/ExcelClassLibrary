package com.excel.digitalsignage;

import org.json.JSONObject;

public class Slide {
    private String animation;
    private long duration;
    private JSONObject meta;
    private long pauseDuration;
    private String type;

    public Slide() {
    }

    public Slide(String type2, String animation2, long duration2, long pauseDuration2, JSONObject meta2) {
        setType(type2);
        setAnimation(animation2);
        setDuration(duration2);
        setPauseDuration(pauseDuration2);
        setMeta(meta2);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type2) {
        this.type = type2;
    }

    public String getAnimation() {
        return this.animation;
    }

    public void setAnimation(String animation2) {
        this.animation = animation2;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration2) {
        this.duration = duration2;
    }

    public long getPauseDuration() {
        return this.pauseDuration;
    }

    public void setPauseDuration(long pauseDuration2) {
        this.pauseDuration = pauseDuration2;
    }

    public JSONObject getMeta() {
        return this.meta;
    }

    public void setMeta(JSONObject meta2) {
        this.meta = meta2;
    }

    public String getImageName() {
        try {
            return getMeta().getString("image_name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMD5() {
        try {
            return getMeta().getString("md5");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long getSize() {
        try {
            return Long.parseLong(getMeta().getString("size"));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getID() {
        try {
            return Integer.parseInt(getMeta().getString("id"));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getVideoName() {
        try {
            return getMeta().getString("video_name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getHybrid1ImageRelativePath() {
        try {
            return getMeta().getString("img_relative_path");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getHybrid1ImageMD5() {
        try {
            return getMeta().getString("img_md5");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getHybrid1ImageID() {
        try {
            return Integer.parseInt(getMeta().getString("img_id"));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long getHybrid1ImageSize() {
        try {
            return Long.parseLong(getMeta().getString("img_size"));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getHybrid1VideoRelativePath() {
        try {
            return getMeta().getString("video_relative_path");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getHybrid1VideoMD5() {
        try {
            return getMeta().getString("video_md5");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getHybrid1VideoID() {
        try {
            return Integer.parseInt(getMeta().getString("video_id"));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long getHybrid1VideoSize() {
        try {
            return Long.parseLong(getMeta().getString("video_size"));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
