package com.tao.mychat.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.tao.mychat.R;
import com.tao.mychat.adapter.AddressBookAdapter;
import com.tao.mychat.view.ModifyDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AMOBBS on 2017/2/7.
 */

public class AddressBookFragment extends Fragment {
    private View view;
    private ExpandableListView expandableListView;
    public static AddressBookAdapter adapter;
    public static List<String> parentList,defaultParentList;
    public static Map<String,List<String>> map,defaultMap;
    private ModifyDialog dialog;
    private EditText edit_modify;
    private ImageView image_add;
    private int currentGroup,currentChild;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    public static String dataMap,dataParentList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_addressbook, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandablelistview);
        image_add = (ImageView) view.findViewById(R.id.image_add);
        image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertAddDialog(getActivity(), "新增组");
            }
        });

        initData();

        adapter = new AddressBookAdapter(getActivity().getApplicationContext(), parentList, map);
        expandableListView.setAdapter(adapter);

        //设置子项点击事件
        MyOnClickListener myListener = new MyOnClickListener();
        expandableListView.setOnChildClickListener(myListener);

        //设置长按点击事件
        MyOnLongClickListener myLongListener = new MyOnLongClickListener();
        expandableListView.setOnItemLongClickListener(myLongListener);

        return view;
    }
    public void initData(){
        map = new HashMap<String, List<String>>();
        defaultMap = new HashMap<String, List<String>>();
        parentList = new ArrayList<String>();
        defaultParentList = new ArrayList<String>();

        sp = getActivity().getApplicationContext().getSharedPreferences("spfile", getActivity().MODE_PRIVATE);
        dataMap = sp.getString("dataMap", null);
        dataParentList = sp.getString("dataParentList", null);

        if(dataMap == null || dataParentList == null){
            parentList = new ArrayList<String>();
            parentList.add("客厅");
            parentList.add("厨房");
            parentList.add("卧室");

            List<String> list1 = new ArrayList<String>();
            list1.add("客厅空调");
            list1.add("客厅电视");
            list1.add("客厅电灯");
            map.put("客厅", list1);

            List<String> list2 = new ArrayList<String>();
            list2.add("厨房油烟机");
            list2.add("厨房电灯");
            list2.add("厨房电器");
            map.put("厨房", list2);

            List<String> list3 = new ArrayList<String>();
            list3.add("卧室空调");
            list3.add("卧室灯光");
            list3.add("卧室电视");
            map.put("卧室", list3);
        }else{
            try {
                //初始化parentList
                JSONArray jsonArray = new JSONArray(dataParentList);
                for (int i = 0; i < jsonArray.length(); i++) {
                    parentList.add(jsonArray.get(i).toString());
                }

                //初始化map
                JSONObject jsonObject = new JSONObject(dataMap);
                for (int i = 0; i < jsonObject.length(); i++) {
                    String key = jsonObject.getString(parentList.get(i));
                    JSONArray array = new JSONArray(key);
                    List<String> list = new ArrayList<String>();
                    for (int j = 0; j < array.length(); j++) {
                        list.add(array.get(j).toString());
                    }
                    map.put(parentList.get(i), list);
                }

                Log.d("eric", "①："+map+"②："+parentList);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("eric","String转Map或List出错"+e);
            }
        }
        Log.e("eric", dataMap+"!&&!"+dataParentList);
        saveData();
    }

    //自定义点击监听事件
    public class MyOnClickListener implements ExpandableListView.OnChildClickListener{
        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            String str = "choose"+groupPosition+"-"+childPosition;
            Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //自定义长按监听事件
    public class MyOnLongClickListener implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                       int position, long id) {
            //长按子项
            if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD){
                long packedPos = ((ExpandableListView) parent).getExpandableListPosition(position);
                int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
                int childPosition = ExpandableListView.getPackedPositionChild(packedPos);

                currentGroup = groupPosition;
                currentChild = childPosition;

                String str = (String)adapter.getChild(groupPosition, childPosition);
                alertModifyDialog("修改此项名称",str);
                Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
                return true;
                //长按组
            }else if(ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_GROUP){
                long packedPos = ((ExpandableListView) parent).getExpandableListPosition(position);
                int groupPosition = ExpandableListView.getPackedPositionGroup(packedPos);
                int childPosition = ExpandableListView.getPackedPositionChild(packedPos);

                currentGroup = groupPosition;
                currentChild = childPosition;

                String group = parentList.get(groupPosition);
                alertModifyDialog("修改组名称", group);

                String str = (String)adapter.getGroup(groupPosition);
                Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    //新增组
    public static void addGroup(String newGroupName){
        parentList.add(newGroupName);
        List<String> list = new ArrayList<String>();
        map.put(newGroupName, list);
        adapter.notifyDataSetChanged();
        saveData();
    }

    //新增子项到指定组
    public static void addChild(int groupPosition, String newChildName){
        String groupName = parentList.get(groupPosition);
        List<String> list = map.get(groupName);
        list.add(newChildName);
        adapter.notifyDataSetChanged();
        saveData();
    }

    //删除指定组
    public static void deleteGroup(int groupPos){
        String groupName = parentList.get(groupPos);
        map.remove(groupName);
        parentList.remove(groupPos);
        adapter.notifyDataSetChanged();
        saveData();
    }
    //删除指定子项
    public static void deleteChild(int groupPos, int childPos){
        String groupName = parentList.get(groupPos);
        List<String> list = map.get(groupName);
        list.remove(childPos);
        adapter.notifyDataSetChanged();
        saveData();
    }

    //修改该项名称
    public void modifyName(int groupPosition, int childPosition, String modifyName){
        Toast.makeText(getActivity(), String.valueOf(groupPosition)+'-'+String.valueOf(childPosition), Toast.LENGTH_SHORT).show();
        if(childPosition<0){
            //修改组名称
            String groupName = parentList.get(groupPosition);
            if(!groupName.equals(modifyName)){
                map.put(modifyName, map.get(groupName));
                map.remove(groupName);
                parentList.set(groupPosition, modifyName);
            }

        }else{
            //修改子项名称
            String group = parentList.get(groupPosition);
            List<String> list =map.get(group);
            list.set(childPosition, modifyName);
            map.put(group, list);
        }
        adapter.notifyDataSetChanged();
        saveData();
    }

    //弹修改对话框
    public void alertModifyDialog(String title, String name){
        dialog = new ModifyDialog(getActivity(), title, name);
        edit_modify = dialog.getEditText();
        dialog.setOnClickCommitListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyName(currentGroup, currentChild, edit_modify.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //弹新增组对话框
    public void alertAddDialog(Context context, String title){
        dialog = new ModifyDialog(context, title, null);
        edit_modify = dialog.getEditText();
        dialog.setOnClickCommitListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroup(edit_modify.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //保存数据
    public static void saveData(){
        JSONObject jsonObject = new JSONObject(map);
        dataMap = jsonObject.toString();
        dataParentList = parentList.toString();

        editor = sp.edit();
        editor.putString("dataMap", dataMap);
        editor.putString("dataParentList", dataParentList);
        editor.commit();
    }
}
