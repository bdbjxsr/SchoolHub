package id.hub.school.schoolhub.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;

public class DiscussionRoomAdapter extends RecyclerView.Adapter<DiscussionRoomAdapter.DiscussionRoomViewHolder> {

    private List<RuangDiskusiObject> list;

    public DiscussionRoomAdapter(List<RuangDiskusiObject> list) { this.list = list; }

    @Override
    public DiscussionRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_discussion_room, parent, false);
        return new DiscussionRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DiscussionRoomViewHolder holder, int position) {
        final RuangDiskusiObject object = list.get(position);
        ParseUser user = object.getUser();
        user.fetchIfNeededInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                holder.nameTextView.setText(parseObject.getString("fullName"));
                holder.tipeTextView.setText(object.getKategori());
                holder.judulTextView.setText(object.getJudul());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DiscussionRoomViewHolder extends RecyclerView.ViewHolder {

        TextView tipeTextView;
        TextView judulTextView;
        TextView nameTextView;

        public DiscussionRoomViewHolder(View itemView) {
            super(itemView);
            tipeTextView = ButterKnife.findById(itemView, R.id.tipe);
            judulTextView = ButterKnife.findById(itemView, R.id.judul);
            nameTextView = ButterKnife.findById(itemView, R.id.name);
        }
    }
}
