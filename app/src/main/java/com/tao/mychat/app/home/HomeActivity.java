package com.tao.mychat.app.home;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.tao.mychat.R;
import com.tao.mychat.fragment.AddressBookFragment;
import com.tao.mychat.fragment.ChatRecordFragment;
import com.tao.mychat.fragment.MeFragment;
import com.tao.mychat.view.MCToast;
import com.tao.mychat.widget.swipelistview.Bind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMOBBS on 2017/2/6.
 */

public class HomeActivity extends AppCompatActivity {
    private RadioGroup  rg_group;
    private RadioButton rb_message,rb_addressbook,rb_setting;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    private MenuFragment menuFragment;
    private HomeFragment homeFragment;
    @Bind(R.id.containerMenu)
    FrameLayout containerMenu;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    //布局管理器
    private FragmentManager fManager;
    //通讯录
    public static AddressBookFragment addressBookFragment;
    //消息记录
    private ChatRecordFragment chatRecordFragment;
    //设置
    private MeFragment meFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //使actionbar隐藏
        getSupportActionBar().hide();
        initUI();
        clickMenu(rb_addressbook);
        FrameLayout containerMenu=(FrameLayout)findViewById(R.id.containerMenu);


        fManager = getSupportFragmentManager();
         String settTag = String.valueOf(R.id.containerMenu);
        if (savedInstanceState != null) {
            menuFragment = (MenuFragment) fManager.findFragmentByTag(settTag);
        }
        if (homeFragment == null) {
            menuFragment = new MenuFragment();
            fManager.beginTransaction()
                    .add(R.id.containerMenu, menuFragment, settTag)
                    .commit();
        }
    }

    private void initUI() {
        //底部导航栏
        fManager=getSupportFragmentManager();
        rb_message=(RadioButton)findViewById(R.id.rb_message);
        rb_addressbook=(RadioButton)findViewById(R.id.rb_addressbook);
        rb_setting=(RadioButton)findViewById(R.id.rb_setting);
        //侧边菜单栏
        //设置抽屉DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

    }

    /**
     * 点击底部菜单事件
     * @param v
     */
    public void clickMenu(View v){
        FragmentTransaction trans = fManager.beginTransaction();
        int vID = v.getId();
        // 隐藏所有的fragment
        hideFrament(trans);
        // 设置Fragment
        setFragment(vID,trans);
        trans.commit();
    }
    /**
     * 隐藏所有的fragment(编程初始化状态)
     * @param trans
     */
    private void hideFrament(FragmentTransaction trans) {
        if(chatRecordFragment!=null){
            trans.hide(chatRecordFragment);
        }
        if(addressBookFragment!=null){
            trans.hide(addressBookFragment);
        }
        if(meFragment!=null){
            trans.hide(meFragment);
        }
    }
    /**
     * 设置Fragment
     * @param vID
     * @param trans
     */
    private void setFragment(int vID, FragmentTransaction trans) {
        switch (vID){
            case R.id.rb_message:
                if (chatRecordFragment==null){
                    chatRecordFragment=new ChatRecordFragment();
                    trans.add(R.id.content,chatRecordFragment);
                }
                else {
                    trans.show(chatRecordFragment);
                }
                break;
            case R.id.rb_addressbook:
                if (addressBookFragment==null){
                    addressBookFragment=new AddressBookFragment();
                    trans.add(R.id.content,addressBookFragment);
                }
                else {
                    trans.show(addressBookFragment);
                }
                break;
            case R.id.rb_setting:
                if (meFragment==null){
                    meFragment=new MeFragment();
                    trans.add(R.id.content,meFragment);
                }
                else {
                    trans.show(meFragment);
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            exitBy2Click();
        }
    }
    private long lastClickTime;

    //按返回键退出二次确认提示
    private void exitBy2Click() {
        long now = System.currentTimeMillis();
        if (now - lastClickTime > 2_000) {
            lastClickTime = now;
            MCToast.show(R.string.common_exit_app, this);
        } else {
            super.onBackPressed();
        }
    }
}
