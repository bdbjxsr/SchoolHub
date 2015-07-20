package id.hub.school.schoolhub.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;

import butterknife.ButterKnife;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.model.data.ScheduleObject;

public class ScheduleAdapter extends ParseQueryAdapter<ScheduleObject> {

    private Context context;

    public ScheduleAdapter(Context context, QueryFactory<ScheduleObject> queryFactory) {
        super(context, queryFactory);
        this.context = context;
    }

    @Override
    public View getItemView(ScheduleObject object, View v, ViewGroup parent) {
        ViewHolder holder;

        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_schedule_row, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        TextView title = holder.title;
        title.setText(object.getTITLE());

        TextView time = holder.time;
        time.setText(object.getTIME());

        return v;
    }

    public class ViewHolder {
        TextView title;
        TextView time;

        public ViewHolder(View v) {
            title = ButterKnife.findById(v, R.id.title);
            time = ButterKnife.findById(v, R.id.time);
        }
    }
}
