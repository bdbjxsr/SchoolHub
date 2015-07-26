package id.hub.school.schoolhub.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.model.data.OpenDiscussionObject;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.utils.TimeUtil;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {

    public static final int VIEW_TYPE_QUESTION = 0;
    public static final int VIEW_TYPE_NORMAL = 1;
    public static final int VIEW_TYPE_LOADING = 2;
    public static final int VIEW_TYPE_EMPTY = 3;

    private List<Object> items;

    private RuangDiskusiObject ruangDiskusiObject;
    private List<OpenDiscussionObject> list;

    @Inject public CommentAdapter(){ repopulateItems(); }

    public void setRuangDiskusiObject(RuangDiskusiObject ruangDiskusiObject) {
        this.ruangDiskusiObject = ruangDiskusiObject;
        repopulateItems();
    }

    public void setList(List<OpenDiscussionObject> list) {
        this.list = list;
        repopulateItems();
    }

    private void repopulateItems() {
        items = new ArrayList<>();

        if (ruangDiskusiObject != null) {
            items.add(ruangDiskusiObject);
        }

        if (list != null && list.size() > 0) {
            for(OpenDiscussionObject openDiscussionObject : list) {
                items.add(openDiscussionObject);
            }
        } else {
            items.add(null);
        }

    }

    @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);

        if (item == null) {
            return VIEW_TYPE_EMPTY;
        } else if (item instanceof RuangDiskusiObject) {
            return VIEW_TYPE_QUESTION;
        } else if (item instanceof OpenDiscussionObject) {
            return VIEW_TYPE_NORMAL;
        } else {
            throw new IllegalArgumentException("Invalid item type " + item.getClass());
        }
    }

    public Object getItem(int position) {
        return items == null ? null : items.get(position);
    }

    @Override
    public CommentAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_QUESTION:
                return new QuestionViewHolder(parent);
            case VIEW_TYPE_NORMAL:
                return new CommentViewHolder(parent);
            case VIEW_TYPE_LOADING:
                return new ProgressBarViewHolder(parent);
            case VIEW_TYPE_EMPTY:
                return new EmptyViewHolder(parent);
            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }

    @Override
    public void onBindViewHolder(CommentAdapter.Holder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case VIEW_TYPE_QUESTION:
                RuangDiskusiObject ruangDiskusiObject = (RuangDiskusiObject) getItem(position);
                QuestionViewHolder questionViewHolder = (QuestionViewHolder) holder;
                questionViewHolder.bind(ruangDiskusiObject);
                break;
            case VIEW_TYPE_NORMAL:
                OpenDiscussionObject openDiscussionObject = (OpenDiscussionObject)
                        getItem(position);
                CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
                commentViewHolder.bind(openDiscussionObject);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@LayoutRes int resId, ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        }
    }

    public class QuestionViewHolder extends Holder {

        @InjectView(R.id.name) TextView name;
        @InjectView(R.id.time) TextView time;
        @InjectView(R.id.title) TextView title;
        @InjectView(R.id.question) TextView question;

        public QuestionViewHolder(ViewGroup parent) {
            super(R.layout.view_question, parent);
            ButterKnife.inject(this, itemView);
        }

        public void bind(RuangDiskusiObject object) {
            if (object != null) {
                name.setText(object.getUser().getString("fullName"));

                //Date createdAt = object.getCreatedDate();
                //time.setText(TimeUtil.getTimeAgo(createdAt.getTime(), itemView.getContext()));

                title.setText(object.getJudul());
                question.setText(object.getQuestion());
            }
        }
    }


    public class CommentViewHolder extends Holder {

        @InjectView(R.id.answer)TextView answer;
        @InjectView(R.id.circleview) CircleImageView circleImageView;
        @InjectView(R.id.name) TextView name;
        @InjectView(R.id.school) TextView schoolName;
        @InjectView(R.id.time) TextView time;

        public CommentViewHolder(ViewGroup parent) {
            super(R.layout.card_view_open_discussion, parent);
            ButterKnife.inject(this, itemView);
        }

        public void bind(OpenDiscussionObject object) {
            if (object != null) {
                Picasso.with(itemView.getContext())
                        .load(R.drawable.male).into(circleImageView);

                answer.setText(object.getAnswer());

                name.setText(object.getUser().getString("fullName"));

                schoolName.setText(object.getUser().getString("schoolName"));

                time.setText(TimeUtil
                        .getTimeAgo(object.getCreatedAt().getTime(), itemView.getContext()));
            }
        }
    }

    public class ProgressBarViewHolder extends Holder {
        public ProgressBarViewHolder(ViewGroup parent) {
            super(R.layout.item_progress, parent);
            ButterKnife.inject(this, itemView);
        }
    }

    public class EmptyViewHolder extends Holder {
        public EmptyViewHolder(ViewGroup parent) {
            super(R.layout.view_text, parent);
        }
    }
}
