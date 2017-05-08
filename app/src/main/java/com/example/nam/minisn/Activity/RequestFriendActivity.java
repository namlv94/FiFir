package com.example.nam.minisn.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nam.minisn.Adapter.ListviewRequestFriendAdapter;
import com.example.nam.minisn.ItemListview.Friend;
import com.example.nam.minisn.R;
import com.example.nam.minisn.Util.Const;
import com.example.nam.minisn.Util.SQLiteDataController;
import com.example.nam.minisn.Util.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RequestFriendActivity extends AppCompatActivity {
    private ListView lvRequestFriend;
    private static ListviewRequestFriendAdapter adapter;

    private static ArrayList<Friend> friends = new ArrayList<Friend>();
    private LinearLayout btnBack;
    private SQLiteDataController database;
    private int useId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_request_friend);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        friends.clear();
        database.openDataBase();
        friends.addAll(database.getListRequestFriend(useId));
        database.close();
        adapter.notifyDataSetChanged();
    }

    public void init(){
        useId = SharedPrefManager.getInstance(getApplicationContext()).getInt(Const.ID);
        database = new SQLiteDataController(getApplicationContext());
        btnBack = (LinearLayout) findViewById(R.id.chat_btn_back);
        lvRequestFriend = (ListView)findViewById(R.id.request_friend_lv);
        adapter = new ListviewRequestFriendAdapter(this,R.layout.item_listview_request_friend,friends);
        lvRequestFriend.setAdapter(adapter);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static ArrayList<Friend> getFriends() {
        return friends;
    }
    public static ListviewRequestFriendAdapter getAdapter() {
        return adapter;
    }
}
