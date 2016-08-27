package ru.arnis.izum;

/**
 * Created by arnis on 23/08/16.
 */
//Класс для хранения данных изображения
public class Image {

    private long id;
    private int num;
    private String url;
    private String comment;
    private boolean favourite;
    public static int currNum=0;

    public Image(long id, String url) {
        this.id = id;
        this.num = currNum++;
        this.url = url;
        favourite=false;
        comment=null;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getUrl() {
        return url;
    }

}
