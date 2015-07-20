package id.hub.school.schoolhub.interactor;

import android.text.TextUtils;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.presenter.DiscussionFormCreateListener;

@Singleton
public class DiscussionFormInteractorImp implements DiscussionFormInteractor {

    @Inject public DiscussionFormInteractorImp() {}

    @Override
    public void validateCreateDiscussionForm(String category, String title, String question,
                                             final DiscussionFormCreateListener listener) {
        if (TextUtils.isEmpty(title)) {
            listener.onTitleError("Title cannot be empty");
        } else if (TextUtils.isEmpty(question)) {
            listener.onQuestionError("Question cannot be empty");
        } else {
            RuangDiskusiObject object = new RuangDiskusiObject();
            object.setKategori(category);
            object.setJudul(title);
            object.setQuestion(question);
            object.setUser(ParseUser.getCurrentUser());
            object.setCommentCount(0);
            object.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        listener.onSuccess();
                    } else {
                        listener.onFailed(e.getMessage());
                    }
                }
            });
        }
    }
}
