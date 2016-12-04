package com.wuys.wuyson.quikeindex;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Wuyson on 2016/12/2.
 */

public class MyAdaptor extends BaseAdapter{
    private Context context;
    private ArrayList<Friend> list;

    public MyAdaptor(Context context,ArrayList<Friend> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null) {
            view = View.inflate(context, R.layout.adaptor_friend, null);
        }
        ViewHolder viewHolder = ViewHolder.getHolder(view);
        Friend friends = list.get(i);
        viewHolder.name.setText(friends.getName());
        String currentWord = friends.getPinyin().charAt(0)+"";
        if (i>0){
            String lastWord = list.get(i-1).getPinyin().charAt(0)+"";
            if (currentWord.equals(lastWord)){
                viewHolder.first_word.setVisibility(View.GONE);
            }else {
                viewHolder.first_word.setVisibility(View.VISIBLE);
                viewHolder.first_word.setText(currentWord);
            }
        }else {
            viewHolder.first_word.setVisibility(View.VISIBLE);
            viewHolder.first_word.setText(currentWord);
        }


        return view;
    }

    static class ViewHolder{
        TextView name,first_word;

        public  ViewHolder(View view){
            first_word = (TextView) view.findViewById(R.id.first_word);
            name = (TextView) view.findViewById(R.id.name);
        }

        public static ViewHolder getHolder(View view){
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if(viewHolder == null){
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }
            return viewHolder;
        }
    }
}
