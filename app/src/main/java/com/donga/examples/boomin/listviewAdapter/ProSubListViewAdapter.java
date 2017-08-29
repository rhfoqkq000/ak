package com.donga.examples.boomin.listviewAdapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.boomin.listviewItem.ProSubListViewItem;
import com.donga.examples.boomin.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by nature on 16. 7. 19.
 */
public class ProSubListViewAdapter extends BaseAdapter {

    public ArrayList<ProSubListViewItem> mlistData = new ArrayList<ProSubListViewItem>();

    public ProSubListViewAdapter() {
    }

    @Override
    public int getCount() {
        return mlistData.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_pro_sub, parent, false);
        }

        TextView pro_sub_name = convertView.findViewById(R.id.pro_sub_name);
        TextView pro_sub_major = convertView.findViewById(R.id.pro_sub_major);
        final ImageView pro_sub_call = convertView.findViewById(R.id.pro_sub_call);
        ImageView pro_sub_mail = convertView.findViewById(R.id.pro_sub_mail);
        final TextView pro_call_gone = convertView.findViewById(R.id.pro_call_gone);
        final TextView pro_mail_gone = convertView.findViewById(R.id.pro_mail_gone);

        ProSubListViewItem listData = mlistData.get(position);

        pro_sub_name.setText(listData.getPro_sub_name());
        pro_sub_major.setText(listData.getPro_sub_major());
        pro_sub_call.setImageDrawable(listData.getPro_sub_call());
        pro_sub_mail.setImageDrawable(listData.getPro_sub_mail());
        pro_call_gone.setText(listData.getPro_call_gone());
        pro_mail_gone.setText(listData.getPro_mail_gone());

        pro_sub_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pro_call_gone.getText().toString().equals(".")) {
                    Toasty.error(context, "등록된 전화번호가 없습니다.", Toast.LENGTH_SHORT).show();
                } else {

                    String pro_tel = pro_call_gone.getText().toString();
                    Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse(String.format("tel:%s", pro_tel)));
                    context.startActivity(in);

                }
            }
        });

        pro_sub_call.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (pro_call_gone.getText().toString().equals(".")) {
                    Toasty.error(context, "등록된 전화번호가 없습니다.", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("label", pro_call_gone.getText().toString());
                    clipboardManager.setPrimaryClip(clipData);
                    return true;
                }
            }
        });

        pro_sub_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pro_mail_gone.getText().toString().equals(".")) {
                    Toasty.error(context, "등록된 이메일이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent it = new Intent(Intent.ACTION_SEND);
                    String[] mailaddr = {pro_mail_gone.getText().toString()};
                    it.setType("plaine/text");
                    it.putExtra(Intent.EXTRA_EMAIL, mailaddr);
                    context.startActivity(it);
                }
            }
        });

        pro_sub_mail.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (pro_mail_gone.getText().toString().equals(".")) {
                    Toasty.error(context, "등록된 이메일이 없습니다.", Toast.LENGTH_SHORT).show();
                    return false;
                } else {

                    ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("label", pro_mail_gone.getText().toString());
                    clipboardManager.setPrimaryClip(clipData);
                    return true;
                }
            }
        });

        return convertView;
    }

    public void addItem(String name, String major, Drawable call, Drawable mail, String call2, String mail2) {
        ProSubListViewItem addInfo = new ProSubListViewItem();
        addInfo.setPro_sub_name(name);
        addInfo.setPro_sub_major(major);
        addInfo.setPro_sub_call(call);
        addInfo.setPro_sub_mail(mail);
        addInfo.setPro_call_gone(call2);
        addInfo.setPro_mail_gone(mail2);

        mlistData.add(addInfo);
    }
}