package com.excel.digitalsignage;

import org.json.JSONObject;

public class SlideUnit {
    public static int DOWNLOAD_COMPLETED = 2;
    public static int DOWNLOAD_IN_PROGRESS = 1;
    public static int DOWNLOAD_STARTED = 0;
    long downloadReference = -1;
    int downloadStatus = -1;

    /* renamed from: id */
    int f50id;
    String imageName;
    boolean isDownloadVerified = false;
    boolean isImage;
    boolean isVideo;
    String md5;
    JSONObject meta;
    String relativePath;
    long size;
    String videoName;

    public SlideUnit() {
    }

    public SlideUnit(JSONObject meta2, boolean imageOrVideo) throws Exception {
        this.meta = meta2;
        if (imageOrVideo) {
            try {
                this.isImage = true;
                this.imageName = meta2.getString("image_name");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
            this.isVideo = true;
            this.videoName = meta2.getString("video_name");
        }
        this.relativePath = meta2.getString("relative_path");
        this.md5 = meta2.getString("md5");
        this.f50id = Integer.parseInt(meta2.getString("id"));
        this.size = Long.parseLong(meta2.getString("size"));
    }

    public static SlideUnit[] generateSlideUnit(JSONObject meta2, String type) {
        SlideUnit[] slideUnit = new SlideUnit[2];
        try {
            if (type.equals("hybrid1")) {
                SlideUnit imageUnit = new SlideUnit();
                imageUnit.setImageName(meta2.getString("image_name"));
                imageUnit.setMd5(meta2.getString("img_md5"));
                imageUnit.setRelativePath(meta2.getString("img_relative_path"));
                imageUnit.setID(Integer.parseInt(meta2.getString("img_id")));
                imageUnit.setSize(Long.parseLong(meta2.getString("img_size")));
                imageUnit.setIsImage(true);
                SlideUnit videoUnit = new SlideUnit();
                videoUnit.setVideoName(meta2.getString("video_name"));
                videoUnit.setMd5(meta2.getString("video_md5"));
                videoUnit.setRelativePath(meta2.getString("video_relative_path"));
                videoUnit.setID(Integer.parseInt(meta2.getString("video_id")));
                videoUnit.setSize(Long.parseLong(meta2.getString("video_size")));
                videoUnit.setIsVideo(true);
                slideUnit[0] = imageUnit;
                slideUnit[1] = videoUnit;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return slideUnit;
    }

    public void setIsImage(boolean isImage2) {
        this.isImage = isImage2;
    }

    public void setIsVideo(boolean isVideo2) {
        this.isVideo = isVideo2;
    }

    public boolean isImage() {
        return this.isImage;
    }

    public boolean isVideo() {
        return this.isVideo;
    }

    public void setImageName(String imageName2) {
        this.imageName = imageName2;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setMd5(String md52) {
        this.md5 = md52;
    }

    public String getMD5() {
        return this.md5;
    }

    public void setRelativePath(String relativePath2) {
        this.relativePath = relativePath2;
    }

    public String getRelativePath() {
        return this.relativePath;
    }

    public void setSize(long size2) {
        this.size = size2;
    }

    public long getSize() {
        return this.size;
    }

    public void setID(int id) {
        this.f50id = id;
    }

    public int getID() {
        return this.f50id;
    }

    public void setVideoName(String videoName2) {
        this.videoName = videoName2;
    }

    public String getVideoName() {
        return this.videoName;
    }

    public void setDownloadReference(long downloadReference2) {
        this.downloadReference = downloadReference2;
    }

    public long getDownloadReference() {
        return this.downloadReference;
    }

    public void setDownloadStatus(int downloadStatus2) {
        this.downloadStatus = downloadStatus2;
    }

    public int getDownloadStatus() {
        return this.downloadStatus;
    }

    public void setDownloadVerified(boolean isDownloadVerified2) {
        this.isDownloadVerified = isDownloadVerified2;
    }

    public boolean isDownloadVerified() {
        return this.isDownloadVerified;
    }
}
