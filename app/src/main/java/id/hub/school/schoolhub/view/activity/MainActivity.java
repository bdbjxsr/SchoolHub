package id.hub.school.schoolhub.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;

public final class MainActivity extends BaseActivity {

    @InjectView(R.id.textview) TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_text);

        ButterKnife.inject(this);

        content.setText("Main");
    }
}
