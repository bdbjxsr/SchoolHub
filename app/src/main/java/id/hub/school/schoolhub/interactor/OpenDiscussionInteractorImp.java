package id.hub.school.schoolhub.interactor;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.model.data.OpenDiscussionObject;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.presenter.OpenDiscussionListener;

@Singleton
public class OpenDiscussionInteractorImp implements OpenDiscussionInteractor {

    public static final int LIMIT = 10;

    @Inject
    public OpenDiscussionInteractorImp() {
    }

    @Override
    public void loadCommentDiscussion(String objectId, final OpenDiscussionListener listener) {
        ParseQuery<RuangDiskusiObject> ruangDiskusiObjectParseQuery =
                new ParseQuery<>(RuangDiskusiObject.class);
        ruangDiskusiObjectParseQuery.whereEqualTo("objectId", objectId);

        ParseQuery<OpenDiscussionObject> query =
                new ParseQuery<>(OpenDiscussionObject.class);
        query.include("user");
        query.include("ruangDiskusi");
        query.setLimit(LIMIT);
        query.whereMatchesQuery("ruangDiskusi", ruangDiskusiObjectParseQuery);
        query.findInBackground(new FindCallback<OpenDiscussionObject>() {
            @Override
            public void done(List<OpenDiscussionObject> list, ParseException e) {
                if (e == null) {
                    listener.onLoadSuccess(list);
                } else {
                    listener.onLoadFailed(e.getMessage());
                }
            }
        });
    }

    @Override
    public void loadMoreCommentDiscussion(String objectId, int current_page,
                                          final OpenDiscussionListener listener) {
        ParseQuery<RuangDiskusiObject> ruangDiskusiObjectParseQuery =
                new ParseQuery<>(RuangDiskusiObject.class);
        ruangDiskusiObjectParseQuery.whereEqualTo("objectId", objectId);

        ParseQuery<OpenDiscussionObject> query =
                new ParseQuery<>(OpenDiscussionObject.class);
        query.include("user");
        query.include("ruangDiskusi");
        query.setLimit(LIMIT);
        query.setSkip(current_page * LIMIT);
        query.whereMatchesQuery("ruangDiskusi", ruangDiskusiObjectParseQuery);
        query.findInBackground(new FindCallback<OpenDiscussionObject>() {
            @Override
            public void done(List<OpenDiscussionObject> list, ParseException e) {
                if (e == null) {
                    listener.onLoadMoreSuccess(list);
                } else {
                    listener.onLoadFailed(e.getMessage());
                }
            }
        });
    }
}
