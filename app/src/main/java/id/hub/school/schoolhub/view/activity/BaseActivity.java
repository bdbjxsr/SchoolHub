package id.hub.school.schoolhub.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import id.hub.school.schoolhub.view.BaseView;

public abstract class BaseActivity extends AppCompatActivity {
    public abstract void setupToolbar(Toolbar toolbar);
}
