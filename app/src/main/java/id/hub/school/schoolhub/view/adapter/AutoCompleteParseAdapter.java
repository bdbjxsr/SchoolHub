package id.hub.school.schoolhub.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.model.data.Sekolah;
import timber.log.Timber;

import static id.hub.school.schoolhub.model.data.Sekolah.Results;

public class AutoCompleteParseAdapter extends ArrayAdapter<Sekolah.Results> implements Filterable {
    private Context context;
    private List<Results> items;
    private ArrayList<Results> listResult;
    private int viewResourceId;

    public AutoCompleteParseAdapter(Context context, int resource, List<Results> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
        this.listResult = new ArrayList<>();
        this.viewResourceId = resource;
    }

    @Override
    public int getCount() { return listResult.size(); }

    @Override
    public Results getItem(int position) { return listResult.get(position); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(viewResourceId, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (listResult.size() > 0) {
            Results results = getItem(position);

            viewHolder.name.setText(results.Sekolah);
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    listResult.clear();
                    for(Results results : items) {
                        if (results.Sekolah.toLowerCase()
                                .contains(constraint.toString().toLowerCase())) {
                            listResult.add(results);
                        }
                    }
                    // Assign the data to the FilterResults
                    filterResults.values = listResult;
                    filterResults.count = listResult.size();
                }
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    public class ViewHolder {

        @InjectView(R.id.textview) TextView name;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
