package id.hub.school.schoolhub.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.model.data.OpenDiscussionObject;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String SCHOOL_NAME = "schoolName";
    public static final String FULL_NAME = "fullName";

    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private List<OpenDiscussionObject> list;

    public CommentAdapter(List<OpenDiscussionObject> list) { this.list = list; }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_open_discussion, parent, false);
            vh = new CommentViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_progress, parent, false);
            vh = new ProgressBarViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentViewHolder) {
            OpenDiscussionObject object = list.get(position);
            ParseUser user = object.getUser();

            CommentViewHolder viewHolder = (CommentViewHolder) holder;
            viewHolder.answer.setText(object.getAnswer());
            viewHolder.schoolName.setText(user.getString(SCHOOL_NAME));
            viewHolder.name.setText(user.getString(FULL_NAME));
            Picasso.with(holder.itemView.getContext())
                    .load(R.drawable.male).into(viewHolder.circleImageView);
        } else {
            ProgressBarViewHolder progressHolder = (ProgressBarViewHolder) holder;
            progressHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addMoreList(List<OpenDiscussionObject> list) {
        this.list.remove(this.list.size() - 1);
        notifyItemRemoved(this.list.size());
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView answer;
        CircleImageView circleImageView;
        TextView name;
        TextView schoolName;

        public CommentViewHolder(View itemView) {
            super(itemView);

            answer = ButterKnife.findById(itemView, R.id.answer);
            circleImageView = ButterKnife.findById(itemView, R.id.circleview);
            name = ButterKnife.findById(itemView, R.id.name);
            schoolName = ButterKnife.findById(itemView, R.id.school);
        }
    }

    public class ProgressBarViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public ProgressBarViewHolder(View itemView) {
            super(itemView);
            progressBar = ButterKnife.findById(itemView, R.id.progress_bar);
        }
    }
}
