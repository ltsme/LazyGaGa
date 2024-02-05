package com.aoslec.haezzo.ActivityDocument;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aoslec.haezzo.ActivityOnDealList.OnDealListActivity;
import com.aoslec.haezzo.ActivityUserHelper.MypageActivity;
import com.aoslec.haezzo.Adapter.HaezulgaeListAdapter;
import com.aoslec.haezzo.Bean.HaezulgaeListBean;
import com.aoslec.haezzo.MainActivity;
import com.aoslec.haezzo.NetworkTask.HaezulgaeListNetworkTask;
import com.aoslec.haezzo.R;
import com.aoslec.haezzo.ShareVar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HaezulgaeListActivity extends AppCompatActivity {

    String urlAddr = null;
    ArrayList<HaezulgaeListBean> haezulgaeListBeans;
    HaezulgaeListAdapter haezulgaeListAdapter;

    String macIP = ShareVar.macIP;

    RecyclerView recyclerView = null;
    RecyclerView.LayoutManager layoutManager = null;

    Menu menu;
    BottomNavigationView main_bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haezulgae_list);

        urlAddr = "http://" + macIP + ":8080/Haezzo/haezulgaeDocumentSelectList.jsp?";

        recyclerView = findViewById(R.id.lv_haezulgaeList);
        layoutManager = new LinearLayoutManager(HaezulgaeListActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        //바텀 네비게이션 뷰 눌렀을때
        main_bottomNavigationView = (BottomNavigationView)findViewById(R.id.main_bottom_navigation);
        main_bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = main_bottomNavigationView.getMenu();

        // 바텀 네비게이션 버튼
        menu.findItem(R.id.navigation_list).setIcon(R.drawable.ic_home_yellow_24dp);
        menu.findItem(R.id.navigation_list).setIcon(R.drawable.ic_baseline_list_alt_24);
        menu.findItem(R.id.navigation_mypage).setIcon(R.drawable.ic_mypage_black_24dp);

    } // onCreate

    //화면이 뜰때: resume, 다시 와도 resume 부터 실행.
    @Override
    protected void onResume() {
        super.onResume();
        //수정된 정보를 부르기 위해
        connectGetData();
    }

    private void connectGetData() {
        try {
            HaezulgaeListNetworkTask haezulgaeListNetworkTask = new HaezulgaeListNetworkTask(HaezulgaeListActivity.this, urlAddr, "select");
            Object obj = haezulgaeListNetworkTask.execute().get();
            haezulgaeListBeans = (ArrayList<HaezulgaeListBean>) obj;

            haezulgaeListAdapter = new HaezulgaeListAdapter(HaezulgaeListActivity.this, R.layout.haezulgae_custom_layout, haezulgaeListBeans);
            haezulgaeListAdapter.setOnItemClickListener(adapterClick);
            recyclerView.setAdapter(haezulgaeListAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //adapter이용-> recyclerview 클릭시 (리스트에서 하나 클릭시)
    HaezulgaeListAdapter.OnItemClickListener adapterClick = new HaezulgaeListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Intent intent = new Intent(HaezulgaeListActivity.this, HaezulgaeDocumentDetailsActivity.class);
            String dnumber = haezulgaeListBeans.get(position).getDnumber();
            intent.putExtra("dnumber", dnumber);
            Log.v(ShareVar.TAG + "해줄게리스트", "dnumber = " + dnumber);
            startActivity(intent);
        }
    };

    // 바텀 네비게이션
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_list:

                    startActivity(new Intent(getApplicationContext(), OnDealListActivity.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;

                case R.id.navigation_mypage:

                    Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };

}//-------