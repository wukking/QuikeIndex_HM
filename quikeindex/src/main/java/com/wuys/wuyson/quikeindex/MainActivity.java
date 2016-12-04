package com.wuys.wuyson.quikeindex;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private QuikeIndexBar quikeIndexBar;
    private ListView listView;
    private List<Friend> friends = new ArrayList<Friend>();
    private TextView currentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quikeIndexBar = (QuikeIndexBar) findViewById(R.id.quikeIndexBar);
        listView = (ListView) findViewById(R.id.listView);
        currentWord = (TextView) findViewById(R.id.current_word);

        quikeIndexBar.setOnTouchLetterListener(new QuikeIndexBar.OnTouchLetterListener() {
            @Override
            public void OnTouchLetter(String letter) {
                for (int i=0;i<friends.size();i++){
                    String firstWord = friends.get(i).getPinyin().charAt(0)+"";
                    if(letter.equals(firstWord)){
                        listView.setSelection(i);
                        //只需找到第一个就行
                        break;
                    }
                }
                //显示当前触摸字母
                showCurrentWord(letter);
            }
        });

        fillList();
        Collections.sort(friends);
        listView.setAdapter(new MyAdaptor(this, (ArrayList<Friend>) friends));

      /*  Log.e("TAG",PinYinUtil.getPinyin("黑马"));
        Log.e("TAG",PinYinUtil.getPinyin("#黑** 马"));
        Log.e("TAG",PinYinUtil.getPinyin("O(∩_∩)O~黑。。，。马"));*/


    }


    private Handler handler = new Handler();

    protected void showCurrentWord(String letter){
        currentWord.setVisibility(View.VISIBLE);
        currentWord.setText(letter);
        //先移除之前的任务
        handler.removeCallbacksAndMessages(null);
        //延时隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentWord.setVisibility(View.INVISIBLE);
            }
        },1500);
    }

    private void fillList(){
        friends.add(new Friend("李伟"));
        friends.add(new Friend("张三"));
        friends.add(new Friend("阿三"));
        friends.add(new Friend("阿四"));
        friends.add(new Friend("段誉"));
        friends.add(new Friend("段正淳"));
        friends.add(new Friend("张三丰"));
        friends.add(new Friend("陈坤"));
        friends.add(new Friend("林俊杰1"));
        friends.add(new Friend("陈坤2"));
        friends.add(new Friend("王二a"));
        friends.add(new Friend("林俊杰a"));
        friends.add(new Friend("张四"));
        friends.add(new Friend("林俊杰"));
        friends.add(new Friend("王二"));
        friends.add(new Friend("王二b"));
        friends.add(new Friend("赵四"));
        friends.add(new Friend("杨坤"));
        friends.add(new Friend("赵子龙"));
        friends.add(new Friend("杨坤1"));
        friends.add(new Friend("李伟1"));
        friends.add(new Friend("宋江"));
        friends.add(new Friend("宋江1"));
        friends.add(new Friend("李伟3"));
    }

}
