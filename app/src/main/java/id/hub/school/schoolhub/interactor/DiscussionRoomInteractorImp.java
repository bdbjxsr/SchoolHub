package id.hub.school.schoolhub.interactor;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.presenter.LoadDiscussionRoomListener;

@Singleton
public class DiscussionRoomInteractorImp implements DiscussionRoomInteractor {

    public static final int LIMIT = 10;
    public static final String CREATED_AT = "createdAt";

    @Inject
    public DiscussionRoomInteractorImp() {}

    @Override
    public void loadDiscussionRoom(final LoadDiscussionRoomListener listener) {
        ParseQuery<RuangDiskusiObject> query = new
                ParseQuery<RuangDiskusiObject>(RuangDiskusiObject.class);
        query.include("user");
        query.setLimit(LIMIT);
        query.orderByDescending(CREATED_AT);
        query.findInBackground(new FindCallback<RuangDiskusiObject>() {
            @Override
            public void done(List<RuangDiskusiObject> list, ParseException e) {
                if (e == null) {
                    listener.onLoadSuccess(list);
                } else {
                    listener.onLoadFailed(e.getMessage());
                }
            }
        });
    }

    @Override
    public void reloadDiscussionRoom(final LoadDiscussionRoomListener listener) {
        ParseQuery<RuangDiskusiObject> query = new
                ParseQuery<RuangDiskusiObject>(RuangDiskusiObject.class);
        query.include("user");
        query.setLimit(LIMIT);
        query.orderByDescending(CREATED_AT);
        query.findInBackground(new FindCallback<RuangDiskusiObject>() {
            @Override
            public void done(List<RuangDiskusiObject> list, ParseException e) {
                if (e == null) {
                    listener.onReloadSuccess(list);
                } else {
                    listener.onLoadFailed(e.getMessage());
                }
            }
        });
    }

    @Override
    public void loadMoreDiscussionRoom(int loadMore, final LoadDiscussionRoomListener listener) {
        ParseQuery<RuangDiskusiObject> query = new
                ParseQuery<RuangDiskusiObject>(RuangDiskusiObject.class);
        query.include("user");
        query.setLimit(LIMIT);
        query.setSkip(loadMore);
        query.orderByDescending(CREATED_AT);
        query.findInBackground(new FindCallback<RuangDiskusiObject>() {
            @Override
            public void done(List<RuangDiskusiObject> list, ParseException e) {
                if (e == null) {
                    listener.onLoadMoreSuccess(list);
                } else {
                    listener.onLoadFailed(e.getMessage());
                }
            }
        });
    }
}
