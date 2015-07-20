package id.hub.school.schoolhub.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.List;

import butterknife.ButterKnife;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;

public class DiscussionRoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private List<RuangDiskusiObject> list;
    private ClickListener clickListener;

    public DiscussionRoomAdapter() {}

    public void setList(List<RuangDiskusiObject> list) { this.list = list; }

    public RuangDiskusiObject getItem(int position) {
        return list.get(position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_discussion_room, parent, false);
            vh = new DiscussionRoomViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_progress, parent, false);
            vh = new ProgressBarViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DiscussionRoomViewHolder) {
            RuangDiskusiObject object = list.get(position);
            ParseUser user = object.getUser();
            DiscussionRoomViewHolder viewHolder = (DiscussionRoomViewHolder) holder;
            viewHolder.nameTextView.setText(user.getString("fullName"));
            viewHolder.tipeTextView.setText(object.getKategori());
            viewHolder.judulTextView.setText(object.getJudul());
            viewHolder.commentCountTextView.setText(String.valueOf(object.getCommentCount()));
        } else {
            ProgressBarViewHolder progressHolder = (ProgressBarViewHolder) holder;
            progressHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addMoreList(List<RuangDiskusiObject> list) {
        this.list.remove(this.list.size() - 1);
        notifyItemRemoved(this.list.size() - 1);
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class DiscussionRoomViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        TextView tipeTextView;
        TextView judulTextView;
        TextView nameTextView;
        TextView commentCountTextView;

        public DiscussionRoomViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            tipeTextView = ButterKnife.findById(itemView, R.id.tipe);
            judulTextView = ButterKnife.findById(itemView, R.id.judul);
            nameTextView = ButterKnife.findById(itemView, R.id.name);
            commentCountTextView = ButterKnife.findById(itemView, R.id.comment_count);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClickListener(v, getAdapterPosition());
            }
        }
    }

    public class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public ProgressBarViewHolder(View itemView) {
            super(itemView);
            progressBar = ButterKnife.findById(itemView, R.id.progress_bar);
        }
    }

    public interface ClickListener {
        void onItemClickListener(View view, int position);
    }
}
