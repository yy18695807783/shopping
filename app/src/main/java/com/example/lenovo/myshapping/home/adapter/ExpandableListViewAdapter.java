package com.example.lenovo.myshapping.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.myshapping.R;
import com.example.lenovo.myshapping.home.actyvity.GoodsListActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 颜银 on 2016/11/17.
 * QQ:443098360
 * 微信：y443098360
 * 作用：类别分组   其中child数组为真实数据，保存有4组。每组有组数据   child[[], [古风, 和风, lolita, 日常], [日常, 泳衣, 汉风, lolita, 创意T恤], [汉风, 古风, lolita, 胖次, 南瓜裤, 日常]]
 *                    group数组group[全部, 上衣, 下装, 外套]
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> group;
    private List<List<String>> child;
    private GoodsListActivity goodsListActivity;
    private int childP;
    private int groupP;

    public ExpandableListViewAdapter(GoodsListActivity context, ArrayList<String> group, ArrayList<List<String>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
        goodsListActivity = new GoodsListActivity();
        Log.e("TAG", "group" + group.toString());
        Log.e("TAG", "child" + child.toString());
    }

    //组总数
    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return group.size();
    }

    //每组孩子的总数
    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int groupPosition) {
        return child.size();
    }

    //获得groupPosition位置的族元素
    //  获得某个父项
    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    //获得groupPosition组的childPosition孩子元素数据
    //  获得某个父项的某个子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
//        return child.get(childPosition).get(childPosition);//老师的写法
        return child.get(groupPosition).get(childPosition);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //  获得父项显示的view
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_list_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(group.get(groupPosition));
        //  holder.textView.setTextSize(20);
        holder.textView.setPadding(0, 10, 0, 10);
        if (isExpanded) {//判断是否被点击扩展---展示不同的标记图片
            holder.imageView.setImageResource(R.drawable.filter_list_selected);
        } else {
            holder.imageView.setImageResource(R.drawable.filter_list_unselected);
        }
        return convertView;

    }


    //  获得子项显示的view
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_list_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.childImageView = (ImageView) convertView.findViewById(R.id.childImageView);
            holder.ll_child_root = (LinearLayout) convertView.findViewById(R.id.ll_child_root);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (groupPosition != 0) {
            holder.textView.setText(child.get(groupPosition).get(childPosition));
        }

        //  Toast.makeText(context, "childP" + childP + " " + groupP, Toast.LENGTH_SHORT).show();
        if (childP == childPosition && groupP == groupPosition) {
            holder.childImageView.setVisibility(View.VISIBLE);
            notifyDataSetChanged();
        } else {
            holder.childImageView.setVisibility(View.GONE);
            notifyDataSetChanged();
        }

        return convertView;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        childP = childPosition;
        groupP = groupPosition;
        return true;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
        ImageView childImageView;
        LinearLayout ll_child_root;
    }
}
