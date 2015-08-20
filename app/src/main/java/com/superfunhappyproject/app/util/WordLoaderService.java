package com.superfunhappyproject.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by marty on 8/5/15.
 */
public final class WordLoaderService {
    final static String TAG = "WordLoaderService";
    final static String WORD_LIST_KEY = "wordList";

    /**
     * loads list of words from words.csv into a Set of Strings to be saved to shared prefs
     */
    public static Boolean loadWords(Context context) {
        BufferedReader reader = null;
        Set<String> wordList = new HashSet<>();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("words.csv")));

            String mLine = reader.readLine();
            while (mLine != null) {
                //process line
                wordList.add(mLine);
                mLine = reader.readLine();
            }
        } catch (IOException e) {
            Log.e(TAG, "error loading words file");
            return false;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "error closing file reader");
                    return false;
                }
            }
            Log.d(TAG,"words successfully loaded from file");
            saveToSharedPreferences(context, wordList);
            return true;
        }
    }

    /**
     * saves set of words to shared prefs if they have not already been saved
     * @param context
     * @param wordList
     * @return
     */
    public static Boolean saveToSharedPreferences(Context context, Set<String> wordList) {
        SharedPreferences prefs = context.getSharedPreferences(
                "com.superfunhappyproject.app", Context.MODE_PRIVATE);
        return prefs.edit().putStringSet(WORD_LIST_KEY, wordList).commit();

    }

    /**
     * loads word list from shared prefs
     * @param context
     * @return returns empty Set as defualt case to prevent NPE
     */
    public static Set<String> loadWordsFromSharedPrefs(Context context){
        SharedPreferences prefs = context.getSharedPreferences(
                "com.superfunhappyproject.app", Context.MODE_PRIVATE);
        return prefs.getStringSet(WORD_LIST_KEY, new HashSet<String>());
    }
}
