package id.hub.school.schoolhub.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.model.data.OpenDiscussionObject;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    public static final String SCHOOL_NAME = "schoolName";
    public static final String FULL_NAME = "fullName";

    private List<OpenDiscussionObject> list;

    public CommentAdapter(List<OpenDiscussionObject> list) { this.list = list; }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_open_discussion, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        OpenDiscussionObject object = list.get(position);
        ParseUser user = object.getUser();
        holder.answer.setText(object.getAnswer());
        holder.schoolName.setText(user.getString(SCHOOL_NAME));
        holder.name.setText(user.getString(FULL_NAME));
        Picasso.with(holder.itemView.getContext())
                .load(R.drawable.male).into(holder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
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
}
