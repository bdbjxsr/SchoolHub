package id.hub.school.schoolhub.interactor;

import android.text.TextUtils;

import com.parse.FindCallback;
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
        if (TextUtils.isEmpty(answer)) {
            listener.onAnswerError("Answer cannot be empty");
        } else {
            RuangDiskusiObject ruangDiskusiObject =
                    ParseObject.createWithoutData(RuangDiskusiObject.class, objectId);

            OpenDiscussionObject object = new OpenDiscussionObject();
            object.setAnswer(answer);
            object.setRuangDiskusi(ruangDiskusiObject);
            object.setUser(ParseUser.getCurrentUser());
            object.saveInBackground(new SaveCallback() {
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
}
