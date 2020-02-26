package com.excel.digitalsignage;

import android.content.Context;
import android.util.Log;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

public class TemplateReader {
    static final String TAG = "TemplateReader";
    Context context;
    SlideUnit[] slideUnits;
    Slide[] slides;
    JSONObject templateJSON;
    Vector<SlideUnit> vectorSlideUnits;

    public TemplateReader() {
    }

    public TemplateReader(Context context2) {
        this.context = context2;
    }

    public TemplateReader(Context context2, JSONObject templateJSON2) {
        boolean z;
        JSONObject jSONObject = templateJSON2;
        String str = "hybrid1";
        String str2 = "meta";
        String str3 = "type";
        this.context = context2;
        this.templateJSON = jSONObject;
        this.vectorSlideUnits = new Vector<>();
        try {
            int totalSlides = getTotalSlides();
            this.slides = new Slide[totalSlides];
            JSONArray jsonArraySlides = jSONObject.getJSONArray("slides");
            int i = 0;
            while (true) {
                z = false;
                if (i >= totalSlides) {
                    break;
                }
                JSONObject currentSlide = jsonArraySlides.getJSONObject(i);
                Slide[] slideArr = this.slides;
                Slide slide = new Slide(currentSlide.getString(str3), currentSlide.getString("animation"), Long.parseLong(currentSlide.getString("duration")), Long.parseLong(currentSlide.getString("pause_duration")), currentSlide.getJSONObject(str2));
                slideArr[i] = slide;
                if (currentSlide.getString(str3).equals(str)) {
                    SlideUnit[] slideUnit = SlideUnit.generateSlideUnit(currentSlide.getJSONObject(str2), str);
                    this.vectorSlideUnits.add(slideUnit[0]);
                    this.vectorSlideUnits.add(slideUnit[1]);
                } else {
                    Vector<SlideUnit> vector = this.vectorSlideUnits;
                    JSONObject jSONObject2 = currentSlide.getJSONObject(str2);
                    if (currentSlide.getString(str3).equals("image")) {
                        z = true;
                    }
                    vector.add(new SlideUnit(jSONObject2, z));
                }
                i++;
            }
            for (int i2 = 0; i2 < this.vectorSlideUnits.size(); i2++) {
                SlideUnit slideUnit2 = (SlideUnit) this.vectorSlideUnits.get(i2);
            }
            int[] indexes_image_remove = new int[this.vectorSlideUnits.size()];
            int[] indexes_video_remove = new int[this.vectorSlideUnits.size()];
            int index_image_incr = 0;
            int index_video_incr = 0;
            for (int i3 = this.vectorSlideUnits.size() - 1; i3 >= 0; i3--) {
                SlideUnit slideUnit3 = (SlideUnit) this.vectorSlideUnits.get(i3);
                for (int j = i3 - 1; j > 0; j--) {
                    SlideUnit slideUnitNext = (SlideUnit) this.vectorSlideUnits.get(j);
                    if (slideUnitNext.getID() == slideUnit3.getID() && slideUnitNext.isImage() == slideUnit3.isImage()) {
                        int index_image_incr2 = index_image_incr + 1;
                        indexes_image_remove[index_image_incr] = j;
                        index_image_incr = index_image_incr2;
                    }
                }
            }
            for (int i4 = 0; indexes_image_remove[i4] != 0; i4++) {
                this.vectorSlideUnits.remove(indexes_image_remove[i4]);
            }
            for (int i5 = this.vectorSlideUnits.size() - 1; i5 >= 0; i5--) {
                SlideUnit slideUnit4 = (SlideUnit) this.vectorSlideUnits.get(i5);
                for (int j2 = i5 - 1; j2 > 0; j2--) {
                    SlideUnit slideUnitNext2 = (SlideUnit) this.vectorSlideUnits.get(j2);
                    if (slideUnitNext2.getID() == slideUnit4.getID() && slideUnitNext2.isVideo() == slideUnit4.isVideo()) {
                        int index_video_incr2 = index_video_incr + 1;
                        indexes_video_remove[index_video_incr] = j2;
                        index_video_incr = index_video_incr2;
                    }
                }
            }
            /*for (int i6 = 0; indexes_video_remove[i6] != 0; i6++) {
                this.vectorSlideUnits.remove(indexes_video_remove[i6]);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getTemplateJSON() {
        return this.templateJSON;
    }

    public static SlideUnit[] getDownloadableSlideUnits(JSONObject existingTemplate, JSONObject newTemplate) {
        TemplateReader existingTemplateReader = new TemplateReader(null, existingTemplate);
        TemplateReader newTemplateReader = new TemplateReader(null, newTemplate);
        SlideUnit[] existingSlideUnits = existingTemplateReader.getSlideUnits();
        SlideUnit[] newSlideUnits = newTemplateReader.getSlideUnits();
        Vector<SlideUnit> vectorFilteredSlideUnits = new Vector<>();
        new Vector();
        for (int i = 0; i < newSlideUnits.length; i++) {
            int newID = newSlideUnits[i].getID();
            String newMD5 = newSlideUnits[i].getMD5();
            int j = 0;
            while (j < existingSlideUnits.length) {
                int existingID = existingSlideUnits[j].getID();
                if (((newSlideUnits[i].isImage() && existingSlideUnits[j].isImage()) || (newSlideUnits[i].isVideo() && existingSlideUnits[j].isVideo())) && newID == existingID && newMD5.equals(existingSlideUnits[j].getMD5())) {
                    break;
                }
                j++;
            }
            if (j == existingSlideUnits.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("New content : ");
                sb.append(newSlideUnits[i].getRelativePath());
                Log.w(TAG, sb.toString());
                vectorFilteredSlideUnits.add(newSlideUnits[i]);
            }
        }
        SlideUnit[] filteredSlideUnits = new SlideUnit[vectorFilteredSlideUnits.size()];
        for (int i2 = 0; i2 < filteredSlideUnits.length; i2++) {
            filteredSlideUnits[i2] = (SlideUnit) vectorFilteredSlideUnits.get(i2);
        }
        return filteredSlideUnits;
    }

    public static SlideUnit[] getToBeDeletedSlideUnits(JSONObject existingTemplate, JSONObject newTemplate) {
        String str;
        TemplateReader existingTemplateReader = new TemplateReader(null, existingTemplate);
        TemplateReader newTemplateReader = new TemplateReader(null, newTemplate);
        SlideUnit[] existingSlideUnits = existingTemplateReader.getSlideUnits();
        SlideUnit[] newSlideUnits = newTemplateReader.getSlideUnits();
        Vector<SlideUnit> vectorToBeDeletedSlideUnits = new Vector<>();
        for (int i = 0; i < existingSlideUnits.length; i++) {
            int existingID = existingSlideUnits[i].getID();
            String existingMD5 = existingSlideUnits[i].getMD5();
            int j = 0;
            while (true) {
                int length = newSlideUnits.length;
                str = TAG;
                if (j >= length) {
                    break;
                }
                int newID = newSlideUnits[j].getID();
                if (((!newSlideUnits[j].isImage() || !existingSlideUnits[i].isImage()) && (!newSlideUnits[j].isVideo() || !existingSlideUnits[i].isVideo())) || existingID != newID) {
                    j++;
                } else if (!existingMD5.equals(newSlideUnits[j].getMD5())) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Delete this : ");
                    sb.append(newSlideUnits[j].getRelativePath());
                    Log.w(str, sb.toString());
                    vectorToBeDeletedSlideUnits.add(existingSlideUnits[i]);
                }
            }
            if (j == newSlideUnits.length) {
                vectorToBeDeletedSlideUnits.add(existingSlideUnits[i]);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Delete this too : ");
                sb2.append(existingSlideUnits[i].getRelativePath());
                Log.w(str, sb2.toString());
            }
        }
        SlideUnit[] filteredSlideUnits = new SlideUnit[vectorToBeDeletedSlideUnits.size()];
        for (int i2 = 0; i2 < filteredSlideUnits.length; i2++) {
            filteredSlideUnits[i2] = (SlideUnit) vectorToBeDeletedSlideUnits.get(i2);
        }
        return filteredSlideUnits;
    }

    public Slide[] getSlides() {
        return this.slides;
    }

    public SlideUnit[] getSlideUnits() {
        SlideUnit[] slideUnits2 = new SlideUnit[this.vectorSlideUnits.size()];
        for (int i = 0; i < this.vectorSlideUnits.size(); i++) {
            slideUnits2[i] = (SlideUnit) this.vectorSlideUnits.get(i);
        }
        return slideUnits2;
    }

    public int getTotalSlides() {
        try {
            return this.templateJSON.getInt("total_slides");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getTemplateMD5() {
        try {
            return this.templateJSON.getString("template_md5");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTemplateID() {
        try {
            return this.templateJSON.getInt("template_id");
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long getTemplateSize() {
        try {
            return this.templateJSON.getLong("template_size");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
