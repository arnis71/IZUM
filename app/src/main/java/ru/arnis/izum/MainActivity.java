package ru.arnis.izum;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Image> data;
    private JSONObject srcJSON;
    private ViewAnimator animator;
    private ImageDownloader downloader;
    private GestureDetector gd;
    private SettingsData settingsData;
    private boolean autoScrolling;

    public static final String FAVOURITES = "favourites";
    public static final String ISFAV = "fav";
    public static final String COMMENTS = "comments";
    public static final String COMMENT = "comment";
    private ImageView fav;
    private EditText commentEntry;
    private Button postComment;
    private boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animator = (ViewAnimator)findViewById(R.id.image_animator);
        commentEntry = (EditText)findViewById(R.id.comment_entry);
        postComment = (Button)findViewById(R.id.post_comment);
        fav = (ImageView)findViewById(R.id.image_favourite);

        downloader = new ImageDownloader();

        //парсим JSON (за пример взята стена паблика вконтакте)
        //json захардкожен в целях совместимости
        try {
            srcJSON = new JSONObject("{\"response\":{\"count\":14874,\"items\":[{\"id\":25938,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1463724578,\"post_type\":\"post\",\"text\":\"Присоединяйтесь к ➡ [club56159619|Удивительное | Невероятное] и вы увидите впечатляющие фото! [club56159619|Подпишись и смотри]:\",\"is_pinned\":1,\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":413171887,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c60c\\/5ziAHSX7vd8.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c60d\\/OsHmBsl-PXU.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c60e\\/etCjRsig2fc.jpg\",\"photo_807\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c60f\\/gVflhz3az2Q.jpg\",\"width\":640,\"height\":423,\"text\":\"\",\"date\":1463724576,\"access_key\":\"182bdd067a326d88af\"}},{\"type\":\"photo\",\"photo\":{\"id\":413171882,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5e4\\/71o-4UeKdQA.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5e5\\/2w-I7bj_1LM.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5e6\\/T0lrtNIhPk4.jpg\",\"photo_807\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5e7\\/LcokbCVIlqE.jpg\",\"width\":640,\"height\":423,\"text\":\"\",\"date\":1463724576,\"access_key\":\"44cc92b43beac6d49f\"}},{\"type\":\"photo\",\"photo\":{\"id\":413171883,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5ec\\/V09mLhp4buk.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5ed\\/XMCC89pdAhY.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5ee\\/w1cYCJQIAtk.jpg\",\"photo_807\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5ef\\/SUsJVlECaBI.jpg\",\"width\":640,\"height\":423,\"text\":\"\",\"date\":1463724576,\"access_key\":\"bec7c13f3b3722a721\"}},{\"type\":\"photo\",\"photo\":{\"id\":413171885,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5f4\\/eKWau_FWAww.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5f5\\/9lpwg9ih1ck.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5f6\\/2q7angysAyM.jpg\",\"photo_807\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c5f7\\/bG_C30hduKI.jpg\",\"width\":640,\"height\":423,\"text\":\"\",\"date\":1463724576,\"access_key\":\"560892771e5d6d37c5\"}},{\"type\":\"photo\",\"photo\":{\"id\":413171886,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c604\\/dhbtuTpqCic.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c605\\/TQ6xFbLP0sY.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c606\\/qS2pdlFO96Q.jpg\",\"photo_807\":\"https:\\/\\/pp.vk.me\\/c543105\\/v543105481\\/2c607\\/oSVee7ujScM.jpg\",\"width\":640,\"height\":423,\"text\":\"\",\"date\":1463724576,\"access_key\":\"e756eae9c70107e0ab\"}}],\"comments\":{\"count\":3},\"likes\":{\"count\":415},\"reposts\":{\"count\":34}},{\"id\":29037,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1472049916,\"post_type\":\"post\",\"text\":\"Резьба по арбузу\\nНравится? Ставь лайк!\",\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":426207219,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/285ee\\/5FsGH8jjwbc.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/285ef\\/9muDgjXV9SM.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/285f0\\/VCNm8xMVDZw.jpg\",\"width\":604,\"height\":453,\"text\":\"\",\"date\":1472049915,\"post_id\":29037,\"access_key\":\"7ade7ef20a14240771\"}}],\"comments\":{\"count\":0},\"likes\":{\"count\":74},\"reposts\":{\"count\":3}},{\"id\":29036,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1472043652,\"post_type\":\"post\",\"text\":\"Счастье не приходит, приходит умение его видеть.\",\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":426192003,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/285ba\\/LLqAHu2bwR8.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/285bb\\/rvceVS25lU4.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/285bc\\/VPMdrl_TqyA.jpg\",\"width\":604,\"height\":424,\"text\":\"\",\"date\":1472043652,\"post_id\":29036,\"access_key\":\"e9635cfaa9b9ca0be3\"}}],\"comments\":{\"count\":1},\"likes\":{\"count\":52},\"reposts\":{\"count\":8}},{\"id\":29035,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1472037314,\"post_type\":\"post\",\"text\":\"\\\"Мечтатель\\\"\",\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":426177284,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/2859e\\/NRlRfiN1ArI.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/2859f\\/ccBxOXV2WuI.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/285a0\\/OmSyjaLNm68.jpg\",\"width\":604,\"height\":403,\"text\":\"\",\"date\":1472037314,\"post_id\":29035,\"access_key\":\"a3ba1d8d7ecdf1a3a8\"}}],\"comments\":{\"count\":0},\"likes\":{\"count\":40},\"reposts\":{\"count\":4}},{\"id\":29033,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1472031032,\"post_type\":\"post\",\"text\":\"Самоеды. Они прекрасны\",\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":426165060,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28574\\/EEJagLWjDv0.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28575\\/-kCLTKx3NtQ.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28576\\/rv5ZX28eyE4.jpg\",\"width\":604,\"height\":604,\"text\":\"\",\"date\":1472031032,\"access_key\":\"2a5f6588085edf9615\"}},{\"type\":\"photo\",\"photo\":{\"id\":426165061,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/2857b\\/Opy68VqtU54.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/2857c\\/leSzG1NB_w0.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/2857d\\/e2DeGey0xpQ.jpg\",\"width\":604,\"height\":564,\"text\":\"\",\"date\":1472031032,\"access_key\":\"c652c05798f98a3cad\"}},{\"type\":\"photo\",\"photo\":{\"id\":426165062,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28582\\/oS9s3Neci5I.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28583\\/c05wSUOdCiE.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28584\\/EJHkQ6Z5y3c.jpg\",\"width\":604,\"height\":557,\"text\":\"\",\"date\":1472031032,\"access_key\":\"e3d6b87a0baf882725\"}},{\"type\":\"photo\",\"photo\":{\"id\":426165063,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28589\\/vSYDJMqV-sk.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/2858a\\/XfHMgmW1Ys8.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/2858b\\/Ma8WvDLoUCM.jpg\",\"width\":604,\"height\":560,\"text\":\"\",\"date\":1472031032,\"access_key\":\"76373eff7dd290ee6d\"}},{\"type\":\"photo\",\"photo\":{\"id\":426165064,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28590\\/S5Nuo5UDo1c.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28591\\/obUbOu77xyY.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28592\\/QoS_RHupVek.jpg\",\"width\":604,\"height\":560,\"text\":\"\",\"date\":1472031032,\"access_key\":\"be8e5ad0a52dabbb11\"}}],\"comments\":{\"count\":0},\"likes\":{\"count\":57},\"reposts\":{\"count\":3}},{\"id\":29031,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1472024710,\"post_type\":\"post\",\"text\":\"Буддийский храм в Мьянме\",\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":426152937,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28564\\/QZknDMdJXOU.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28565\\/3g_EGNFU6EQ.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28566\\/dODB4oxCyic.jpg\",\"width\":604,\"height\":403,\"text\":\"\",\"date\":1472024709,\"post_id\":29031,\"access_key\":\"3b95432be2800fbb38\"}}],\"comments\":{\"count\":0},\"likes\":{\"count\":47},\"reposts\":{\"count\":7}},{\"id\":29027,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1472018434,\"post_type\":\"post\",\"text\":\"Потрясающий дизайн одной из станций метро в Стокгольме (Швеция)\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4D\",\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":426143066,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28544\\/Baa6E79LSFI.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28545\\/MWbPPWWcQJc.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28546\\/62KuRDGh_uU.jpg\",\"photo_807\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28547\\/C_qT6m9Tc_E.jpg\",\"photo_1280\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28548\\/XTfeitbYrq0.jpg\",\"width\":1024,\"height\":678,\"text\":\"\",\"date\":1472018434,\"post_id\":29027,\"access_key\":\"99230ddbe9f40c40e4\"}}],\"comments\":{\"count\":0},\"likes\":{\"count\":58},\"reposts\":{\"count\":1}},{\"id\":29022,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1471982500,\"post_type\":\"post\",\"text\":\"Оригинальная свадьба\",\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":426114405,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28515\\/Dv8vq8Lm1Lc.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28516\\/S9G5ETTzidw.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28517\\/f5PHiCjzK_s.jpg\",\"width\":604,\"height\":403,\"text\":\"\",\"date\":1471982503,\"post_id\":29022,\"access_key\":\"d6716a2ff90053b170\"}}],\"comments\":{\"count\":0},\"likes\":{\"count\":54},\"reposts\":{\"count\":2}},{\"id\":29020,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1471976126,\"post_type\":\"post\",\"text\":\"Мама — самая дорогая роскошь в мире! Если согласен, жми лайк\",\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":426096961,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28484\\/NBB8k6nXKuA.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28485\\/Kr0Kg5ZUstQ.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28486\\/fVx48TfOCvg.jpg\",\"width\":604,\"height\":402,\"text\":\"\",\"date\":1471976126,\"post_id\":29020,\"access_key\":\"766d8510e6f12ff14b\"}}],\"comments\":{\"count\":0},\"likes\":{\"count\":349},\"reposts\":{\"count\":5}},{\"id\":29018,\"from_id\":-56159619,\"owner_id\":-56159619,\"date\":1471969837,\"post_type\":\"post\",\"text\":\"Императорская резиденция — Петергоф с высоты птичьего полета.\",\"attachments\":[{\"type\":\"photo\",\"photo\":{\"id\":426080976,\"album_id\":-7,\"owner_id\":-56159619,\"user_id\":100,\"photo_75\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/2845f\\/gv0IApr_mbA.jpg\",\"photo_130\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28460\\/PTLHYjgEenk.jpg\",\"photo_604\":\"https:\\/\\/pp.vk.me\\/c543106\\/v543106143\\/28461\\/Ue7KrbtrfHM.jpg\",\"width\":604,\"height\":420,\"text\":\"\",\"date\":1471969838,\"post_id\":29018,\"access_key\":\"064b148b19b203ac3f\"}}],\"comments\":{\"count\":0},\"likes\":{\"count\":66},\"reposts\":{\"count\":5}}]}}");
            data = retreiveDataFromJSON(srcJSON);
        } catch (JSONException e) {
            Toast.makeText(this,"Произошла ошибка",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //регистрируем свайп
        gd.onTouchEvent(event);
        updateToolBar();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // загружаем и применяем настройки
        getSettingsData();
        loadFavs();
        loadComments();
        applySettings();

        // проверяем подключение к интернету и загружаем фото в кэш
        if (data !=null){
            if (checkInternet()){
                downloader.clearCache();
                downloader.downloadImages(data);
            } else Toast.makeText(this,"Нет подключения к интернету",Toast.LENGTH_SHORT).show();
        }

        //добавляем вьюшки в аниматор
        populateAnimator(animator);

        //проверяем если включен случайный подбор
        Integer random=null;
        if (settingsData.isShowRandom())
            random = Image.currNum;

        //инициализируем детектор жестов
        SwipeDirection swipe = new SwipeDirection(this, animator,random,settingsData.getAnimation().equals("default"));
        gd = new GestureDetector(this,swipe);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // останваливаем авто скроллинг
        stopScrolling();
        // сохраняем данные
        saveFavs();
        saveComments();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //обнуляем счетчик изображений
        Image.currNum=0;
    }


    //проверка подключения----------------------
    private boolean checkInternet() {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo()!=null;
    }

    //сохранение и загрузка комментариев----------------------
    private void saveComments() {
        SharedPreferences.Editor editor = getSharedPreferences(COMMENTS,MODE_PRIVATE).edit();
        for (Image image: data){
            if (image.getComment()!=null)
                editor.putString(COMMENT+Integer.toString(image.getNum()),image.getComment());
        }
        editor.apply();
    }

    private void loadComments(){
        SharedPreferences prefs = getSharedPreferences(COMMENTS,MODE_PRIVATE);
        for (int i = 0; i < Image.currNum; i++) {
            String comment = prefs.getString(COMMENT+Integer.toString(i),null);
            if (comment!=null)
                data.get(i).setComment(comment);
        }
    }

    //сохранение и загрузка понравившихся фото--------------------
    private void saveFavs(){
        String favs="";
        for (int i = 0; i< data.size(); i++){
            if (data.get(i).isFavourite())
                favs+=Integer.toString(data.get(i).getNum());
        }
        getSharedPreferences(FAVOURITES,MODE_PRIVATE).edit().putString(ISFAV,favs).apply();
    }

    private void loadFavs() {
        SharedPreferences prefs = getSharedPreferences(FAVOURITES,MODE_PRIVATE);
        String favs = prefs.getString(ISFAV,"");
        for (int i = 0; i < Image.currNum; i++) {
            if (favs.contains(Integer.toString(i)))
                data.get(i).setFavourite(true);
        }
    }

    //загрузка и применение настроек----------------------
    private void getSettingsData() {
        SharedPreferences prefs = getSharedPreferences(Settings.SETTINGS_DATA,MODE_PRIVATE);
        settingsData = new SettingsData();
        String[] animations = getResources().getStringArray(R.array.animations);
        settingsData.setAnimation(animations[prefs.getInt(Settings.SETTINGS_ANIM,Settings.DEFAULT_ANIM)]);
        settingsData.setAutoScroll(prefs.getBoolean(Settings.SETTINGS_AUTOSCROLL,Settings.DEFAULT_AUTOSCROLL));
        settingsData.setAutoScrollInterval(prefs.getInt(Settings.SETTINGS_AUTOSCROLL_INTERVAL,Settings.DEFAULT_AUTOSCROLL_INTERVAL));
        settingsData.setShowFavourites(prefs.getBoolean(Settings.SETTINGS_SHOW_FAVOURITES,Settings.DEFAULT_SHOW_FAVOURITES));
        settingsData.setShowRandom(prefs.getBoolean(Settings.SETTINGS_SHOW_RANDOM,Settings.DEFAULT_SHOW_RANDOM));
    }

    private void  applySettings() {

        switch (settingsData.getAnimation()){
            case "default":
                animator.setInAnimation(this,R.anim.translate_in_left);
                animator.setOutAnimation(this,R.anim.translate_out_left);break;
            case "fade":
                animator.setInAnimation(this,R.anim.fade_in);
                animator.setOutAnimation(this,R.anim.fade_out);break;
            case "scale":
                animator.setInAnimation(this,R.anim.scale_in);
                animator.setOutAnimation(this,R.anim.scale_out);break;
        }

        if (settingsData.isAutoScroll()){
            startScrolling(animator,settingsData.getAutoScrollInterval());
        } else {
            stopScrolling();
        }

        if (settingsData.isShowFavourites()){
            for (int i=0;i<Image.currNum;i++){
                if (!data.get(i).isFavourite()){
                    data.remove(i);
                    Image.currNum--;
                    i--;
                }
            }
        } else { // заново подгружаем данные после выхода из режима просмотра только избранных фото
            Image.currNum=0;
            data.clear();
            data = retreiveDataFromJSON(srcJSON);
            loadFavs();
            loadComments();
        }
    }

    //начало и остановка автоскроллинга--------------------------
    private void startScrolling(final ViewAnimator imageAnimator, final int autoScrollInterval) {
        autoScrolling=true;
        new Thread(new Runnable() {
            Random rnd = new Random();
            @Override
            public void run() {
                    while(autoScrolling) {
                        try {
                            Thread.sleep(autoScrollInterval);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (!settingsData.isShowRandom()) {
                                        imageAnimator.showNext();
                                    } else
                                        imageAnimator.setDisplayedChild(rnd.nextInt(Image.currNum));
                                    updateToolBar();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }).start();
    }

    private void stopScrolling() {
        autoScrolling=false;
    }

    //загрузка вьюшек в аниматор--------------------------
    private void populateAnimator(ViewAnimator imageAnimator) {
        imageAnimator.removeAllViews();
        for(int i=0;i<Image.currNum;i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.image_layout, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            TextView commentText = (TextView)view.findViewById(R.id.image_comment);

            if (checkInternet())
                downloader.requestImageFor(image,i);

            String comment = data.get(i).getComment();
            if (comment!=null) {
                commentText.setText(comment);
                commentText.setVisibility(View.VISIBLE);
            }
            imageAnimator.addView(view);
        }
    }

    //парсинг исходного JSON
    private ArrayList<Image> retreiveDataFromJSON(JSONObject jsonObject){
        ArrayList<Image> imagesTemp = new ArrayList<>();
        Image image;
        try {
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONArray items = (JSONArray)response.get("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject post = (JSONObject) items.get(i);
                if (post.has("attachments")){
                    JSONArray attachments = (JSONArray) post.get("attachments");
                    JSONObject photo = null;
                    if (((JSONObject)(attachments.get(0))).has("photo")) {
                        photo = (JSONObject) ((JSONObject) (attachments.get(0))).get("photo");
                        image = new Image(photo.getLong("id"), photo.getString("photo_604"));
                        imagesTemp.add(image);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imagesTemp;

    }


    //обновляем тулбар (после свайпа или автоскроллинга)------------------
    private void updateToolBar() {
        if (data.get(animator.getDisplayedChild()).isFavourite())
            fav.setImageResource(R.drawable.ic_favorite);
        else
            fav.setImageResource(R.drawable.ic_favorite_border);

        //комментарий открыт но не отправлен (убираем)
        if (!flag){
            postComment.setText("Коммент");
            commentEntry.setText("");
            commentEntry.setVisibility(View.INVISIBLE);
            flag=true;
        }

    }

    //кнопка мне нравиться----------------------
    public void favouriteImage(View view) {
        if (!data.get(animator.getDisplayedChild()).isFavourite()){
            ((ImageView)view).setImageResource(R.drawable.ic_favorite);
            data.get(animator.getDisplayedChild()).setFavourite(true);
        }else {
            ((ImageView)view).setImageResource(R.drawable.ic_favorite_border);
            data.get(animator.getDisplayedChild()).setFavourite(false);
        }
    }

    //кнопка комментария----------------------
    public void postComment(View view) {
            if (flag){
                postComment.setText("Отправить");
                commentEntry.setVisibility(View.VISIBLE);
                flag=false;
                return;
            }
            TextView commentText = (TextView) animator.getChildAt(animator.getDisplayedChild()).findViewById(R.id.image_comment);;
            String comment = commentEntry.getText().toString();
            if (!comment.equals("")) {
                data.get(animator.getDisplayedChild()).setComment(comment);
                commentText.setText(comment);
                commentText.setVisibility(View.VISIBLE);
            } else {//пустое поле (удаляем комментарий)
                commentText.setText("");
                commentText.setVisibility(View.INVISIBLE);
            }
            commentEntry.setText("");
            commentEntry.setVisibility(View.INVISIBLE);
            postComment.setText("Коммент");
            flag=true;

    }

    //переход к настройкам---------------------
    public void openSettings(View view) {
        Intent intent = new Intent(this,Settings.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            Bundle translateBundle = ActivityOptions.makeCustomAnimation(this,R.anim.slide_in_left,R.anim.slide_out_left).toBundle();
            startActivity(intent,translateBundle);
            return;
        }
        startActivity(intent);
    }
}
