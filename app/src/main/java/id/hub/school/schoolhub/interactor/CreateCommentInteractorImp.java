package id.hub.school.schoolhub.interactor;

import android.text.TextUtils;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.model.data.OpenDiscussionObject;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.presenter.CreateCommentListener;

@Singleton
public class CreateCommentInteractorImp implements CreateCommentInteractor {

    @Inject public CreateCommentInteractorImp() {}

    @Override
    public void validateCreateComment(final String objectId, final String answer,
                                      final CreateCommentListener listener) {

        final RuangDiskusiObject ruangDiskusiObject =
                ParseObject.createWithoutData(RuangDiskusiObject.class, objectId);

        ParseQuery<RuangDiskusiObject> query = RuangDiskusiObject.getQuery();
        query.fromLocalDatastore();
        query.whereEqualTo("objectId", objectId);
        query.getFirstInBackground(new GetCallback<RuangDiskusiObject>() {
            @Override
            public void done(RuangDiskusiObject object, ParseException e) {
                if (e == null) {
                    object.setCommentCount(object.getCommentCount() + 1);
                    object.saveInBackground();
                }
            }
        });

        OpenDiscussionObject openDiscussionObject = new OpenDiscussionObject();
        openDiscussionObject.setAnswer(answer);
        openDiscussionObject.setRuangDiskusi(ruangDiskusiObject);
        openDiscussionObject.setUser(ParseUser.getCurrentUser());
        openDiscussionObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    listener.onSuccessCreateComment();
                } else {
                    listener.onFailedCreateComment(e.getMessage());
                }
            }
        });
    }
}
