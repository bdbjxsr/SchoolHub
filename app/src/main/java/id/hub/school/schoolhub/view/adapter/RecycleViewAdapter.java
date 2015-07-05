package id.hub.school.schoolhub.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hub.school.schoolhub.R;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String navTitle[];

    private String name;
    private int profile;
    private String email;

    public RecycleViewAdapter(String[] navTitle, String name, int profile, String email) {
        this.navTitle = navTitle;
        this.name = name;
        this.profile = profile;
        this.email = email;
    }

    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_row, viewGroup, false);

            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;
        } else {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_header, viewGroup, false);

            ViewHolder vhHeader = new ViewHolder(v, viewType);
            return vhHeader;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder.holderId == 1) {
            viewHolder.rowText.setText(navTitle[i]);
        } else {
            viewHolder.circleImageView.setImageResource(R.drawable.button_login);
            viewHolder.name.setText("Siswa Teladan");
            viewHolder.email.setText("SMA Negeri 14 Jakarta");
        }
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        int holderId;

        ImageView rowIcon;
        TextView rowText;

        CircleImageView circleImageView;
        TextView name;
        TextView email;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            if (viewType == TYPE_ITEM) {
                holderId = 1;
                injectItemView(itemView);
            } else {
                holderId = 0;
                injectHeaderView(itemView);
            }
        }

        private void injectItemView(View view) {
            rowIcon = ButterKnife.findById(view, R.id.row_icon);
            rowText = ButterKnife.findById(view, R.id.row_text);
        }

        private void injectHeaderView(View view) {
            circleImageView = ButterKnife.findById(view, R.id.circleview);
            name = ButterKnife.findById(view, R.id.name);
            email = ButterKnife.findById(view, R.id.email);
        }
    }
}
