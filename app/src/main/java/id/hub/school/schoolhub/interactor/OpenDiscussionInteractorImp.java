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
}
