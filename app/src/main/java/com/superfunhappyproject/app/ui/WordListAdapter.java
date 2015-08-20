package com.superfunhappyproject.app.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import sfh.com.superfunhappyproject.R;

public class WordListAdapter extends BaseAdapter {
    final List<String> wordList;
    final Context context;
    final Random rand = new Random();
    private static LayoutInflater inflater = null;
    private final int numLines = 1000;

    public WordListAdapter(Context context, List<String> wordList) {
        this.wordList = wordList;
        this.context = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return numLines;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {

            rowView = inflater.inflate(R.layout.word_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.textHolder);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.text.setText(generateLine());

        return rowView;
    }

    /**
     * generates a line of text with 4 unique words from the word list
     *
     * @return
     */
    public String generateLine() {
        Set<String> uniqueStringsForLine = new TreeSet<>();

        while (uniqueStringsForLine.size() < 4) {
            uniqueStringsForLine.add(wordList.get(randomInt(0, wordList.size() - 1)));
        }

        String returnLine = "";
        for (String s : uniqueStringsForLine)
            returnLine += s + " ";

        return returnLine;
    }

    public int randomInt(int min, int max) {

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    static class ViewHolder {
        TextView text;
    }

}