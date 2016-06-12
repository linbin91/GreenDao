package com.felink.linbinframe.module.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.felink.linbinframe.R;
import com.felink.linbinframe.bean.User;
import com.felink.linbinframe.bean.UserDao;
import com.felink.linbinframe.widget.BaseApplication;

import java.util.List;

import de.greenrobot.dao.query.Query;


/**
 * Created by Administrator on 2016/6/12.
 */
public class TestActivity extends Activity implements View.OnClickListener {

    private Button mBtnAdd;
    private Button mBtmSearch;
    private TextView mTvContent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initListener();
    }

    private void initListener() {
        mBtmSearch.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
    }

    private void initView() {
        mBtmSearch = (Button) findViewById(R.id.buttonQuery);
        mBtnAdd = (Button) findViewById(R.id.buttonAdd);
        mTvContent = (TextView) findViewById(R.id.content);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.buttonAdd:
                addUser();
                break;
            case R.id.buttonQuery:
                searchUser();
                break;
        }
    }

    private void searchUser() {
        Query query = getUserDao().queryBuilder().build();
        List<User> lists = query.list();
        Toast.makeText(TestActivity.this,lists.size() + "",Toast.LENGTH_SHORT).show();
        for (User user : lists){
            mTvContent.append(user.getName() + "==" + user.getPhone());
        }
    }

    private void addUser() {
        User user = new User(null,"linbin","18959298002");
        getUserDao().insert(user);
    }

    private UserDao getUserDao(){
        return  ((BaseApplication) this.getApplicationContext()).getDaoSession().getUserDao();
    }

}
