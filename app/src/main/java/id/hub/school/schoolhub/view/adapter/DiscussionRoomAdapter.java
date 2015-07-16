package id.hub.school.schoolhub.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
    private ClickListener clickListener;

    public DiscussionRoomAdapter(List<RuangDiskusiObject> list) { this.list = list; }

    public RuangDiskusiObject getItem(int position) {
        return list.get(position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

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
        holder.nameTextView.setText(user.getString("fullName"));
        holder.tipeTextView.setText(object.getKategori());
        holder.judulTextView.setText(object.getJudul());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DiscussionRoomViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        TextView tipeTextView;
        TextView judulTextView;
        TextView nameTextView;

        public DiscussionRoomViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            tipeTextView = ButterKnife.findById(itemView, R.id.tipe);
            judulTextView = ButterKnife.findById(itemView, R.id.judul);
            nameTextView = ButterKnife.findById(itemView, R.id.name);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClickListener(v, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        void onItemClickListener(View view, int position);
    }
}
