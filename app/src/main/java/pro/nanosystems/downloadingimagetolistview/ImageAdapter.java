package pro.nanosystems.downloadingimagetolistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pro.nanosystems.downloadingimagetolistview.model.ListData;

/**
 * Created by sayyid on 01/04/2018.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList arrayList;
    private LayoutInflater layoutInflater;

    public ImageAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row, null);
            viewHolder = new ViewHolder();
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.reporter = convertView.findViewById(R.id.reporter);
            viewHolder.thumbImage = convertView.findViewById(R.id.thumbImage);
            viewHolder.date = convertView.findViewById(R.id.date);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ListData newData = (ListData) arrayList.get(position);
        viewHolder.title.setText(newData.getHeadline());
        viewHolder.reporter.setText(newData.getReporterName());
        viewHolder.date.setText(newData.getDate());
        if (viewHolder.thumbImage != null) {
            new DownLoadImage(viewHolder.thumbImage).execute(newData.getUrl());
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView thumbImage;
        TextView title;
        TextView reporter;
        TextView date;
    }
}
